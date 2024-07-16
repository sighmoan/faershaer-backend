package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.Transaction;

public record AddTransactionDto(String payerId, String expense, Double sum) {
    public Transaction getTransaction(Person p) {
        Transaction t = new Transaction();
        t.setExpense(expense);
        t.setPayer(p);
        t.setSum(sum);
        return t;
    }
}
