package tech.tolpuddle.faershaer_backend.services;

import tech.tolpuddle.faershaer_backend.domain.Reimbursement;

import java.util.List;

public class ReimbursementService {
    TxService txService;

    public ReimbursementService(TxService txService) {
        this.txService = txService;
    }

    public List<Reimbursement> getReimbursements() {
        return List.of();
    }
}
