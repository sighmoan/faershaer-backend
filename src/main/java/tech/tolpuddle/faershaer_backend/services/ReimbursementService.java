package tech.tolpuddle.faershaer_backend.services;

import tech.tolpuddle.faershaer_backend.exceptions.NoSuchPersonException;
import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.Reimbursement;
import tech.tolpuddle.faershaer_backend.domain.Transaction;
import tech.tolpuddle.faershaer_backend.exceptions.NoTransactionsException;

import java.util.*;

public class ReimbursementService {
    TxService txService;
    PersonService personService;

    public ReimbursementService(TxService txService, PersonService personService) {
        this.txService = txService;
        this.personService = personService;
    }

    public List<Reimbursement> getReimbursements() {
        Double totalTransactionValue = txService.getAllTransactions().stream()
                .map(Transaction::getSum)
                .reduce(0.0, Double::sum);
        int numberOfPeople = personService.getAllPersons().size();
        Double fairSharePerPerson = totalTransactionValue / numberOfPeople;

        Map<Person, Double> personsWithBalances = new HashMap<>();
        personService.getAllPersons().forEach((person) ->
                personsWithBalances.put(person, fairSharePerPerson - txService.getBalance(person)));


        ArrayList<Reimbursement> reimbursements = new ArrayList<>();
        if(personsWithBalances.isEmpty()) {
            throw new NoTransactionsException();
        }
        computeReimbursements(reimbursements, personsWithBalances);

        return reimbursements;
    }

    private static void computeReimbursements(ArrayList<Reimbursement> reimbursements, Map<Person, Double> personsWithBalances) {
        Map.Entry<Person, Double> minCreditor = null, maxDebtor = null;

        for(Map.Entry<Person, Double> entry : personsWithBalances.entrySet()) {
            if(minCreditor == null || entry.getValue() < minCreditor.getValue()) {
                minCreditor = entry;
            }
            if(maxDebtor == null || entry.getValue() > maxDebtor.getValue()) {
                maxDebtor = entry;
            }
        }

        Double rbValue = Math.min(Math.abs(minCreditor.getValue()), Math.abs(maxDebtor.getValue()));
        if(rbValue < 1) return;

        Reimbursement rb = new Reimbursement();
        rb.setCreditor(minCreditor.getKey());
        rb.setDebtor(maxDebtor.getKey());
        rb.setSum(rbValue);

        reimbursements.add(rb);
        minCreditor.setValue(minCreditor.getValue() + rb.getSum());
        maxDebtor.setValue(maxDebtor.getValue() - rb.getSum());

        computeReimbursements(reimbursements, personsWithBalances);
    }
}
