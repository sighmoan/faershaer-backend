package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.Reimbursement;

public record ReimbursementDto (String creditor, String creditorPortraitUrl, String debtor, String debtorPortraitUrl, Double amount) {
    static public ReimbursementDto getDto(Reimbursement rb) {
        return new ReimbursementDto(
                rb.getCreditor().getName(),
                rb.getCreditor().getPortraitUrl(),
                rb.getDebtor().getName(),
                rb.getDebtor().getPortraitUrl(),
                rb.getSum());
    }
}
