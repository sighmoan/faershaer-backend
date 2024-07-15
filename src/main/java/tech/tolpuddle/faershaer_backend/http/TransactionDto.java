package tech.tolpuddle.faershaer_backend.http;

import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.Transaction;

public record TransactionDto(String payer, String expense, Double sum) {
    public Transaction getTransaction() {
        Transaction t = new Transaction();
        t.setExpense(expense);
        Person p = new Person();
        p.setName(payer);
        t.setPayer(p);
        t.setSum(sum);
        return t;
    }
}
