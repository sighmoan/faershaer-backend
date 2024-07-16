package tech.tolpuddle.faershaer_backend.services;

import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.Reimbursement;
import tech.tolpuddle.faershaer_backend.domain.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

        Reimbursement theRb = getReimbursement(personsWithBalances);

        return List.of(theRb);
    }

    private static Reimbursement getReimbursement(Map<Person, Double> personsWithBalances) {
        Map.Entry<Person, Double> greatestCreditor = null, greatestDebtor = null;

        for(Map.Entry<Person, Double> entry : personsWithBalances.entrySet()) {
            if(greatestCreditor == null || entry.getValue() < greatestCreditor.getValue()) {
                greatestCreditor = entry;
            }
            if(greatestDebtor == null || entry.getValue() > greatestDebtor.getValue()) {
                greatestDebtor = entry;
            }
        }

        Reimbursement theRb = new Reimbursement();
        theRb.setCreditor(greatestCreditor.getKey());
        theRb.setDebtor(greatestDebtor.getKey());
        if(greatestCreditor.getValue() < greatestDebtor.getValue()) {
            theRb.setSum(greatestDebtor.getValue());
        } else {
            theRb.setSum(greatestCreditor.getValue());
        }
        return theRb;
    }
}
