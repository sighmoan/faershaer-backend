package tech.tolpuddle.faershaer_backend.http;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.tolpuddle.faershaer_backend.http.dtos.ReimbursementDto;
import tech.tolpuddle.faershaer_backend.services.ReimbursementService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reimbursements")
public class ReimbursementController {

    ReimbursementService service;

    public ReimbursementController(ReimbursementService service) {
        this.service = service;
    }

    @GetMapping
    private List<ReimbursementDto> getReimbursements() {
        return service.getReimbursements().stream().map(ReimbursementDto::getDto).toList();
    }
}
