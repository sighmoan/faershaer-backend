package tech.tolpuddle.faershaer_backend.http;

import tech.tolpuddle.faershaer_backend.domain.Transaction;

public record TransactionDto(String payer, String expense, Double sum) {
    public Transaction getTransaction() {
        Transaction t = new Transaction();
        t.setExpense(expense);
        t.setPayer(payer);
        t.setSum(sum);
        return t;
    }
}
