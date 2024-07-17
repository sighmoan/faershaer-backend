package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.Reimbursement;

public record ReimbursementDto (String creditor, String debtor, Double balance) {
    static public ReimbursementDto getDto(Reimbursement rb) {
        return new ReimbursementDto(rb.getCreditor().getName(), rb.getDebtor().getName(), rb.getSum());
    }
}
