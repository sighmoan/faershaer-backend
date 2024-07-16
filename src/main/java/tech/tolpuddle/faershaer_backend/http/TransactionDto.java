package tech.tolpuddle.faershaer_backend.http;

import tech.tolpuddle.faershaer_backend.domain.Transaction;

public record TransactionDto (String id, String payer, String expense, Double sum) {
    public static TransactionDto getDto(Transaction t) {
        return new TransactionDto(t.getId(), t.getPayer().getName(), t.getExpense(), t.getSum());
    }
}
