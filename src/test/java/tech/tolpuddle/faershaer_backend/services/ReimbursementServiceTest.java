package tech.tolpuddle.faershaer_backend.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.Reimbursement;
import tech.tolpuddle.faershaer_backend.domain.Transaction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReimbursementServiceTest {

    @Mock
    TxService txService;
    @InjectMocks
    ReimbursementService rbService;

    @Test
    void shouldGenerateSimpleReimbursement() {
        Person p1 = new Person();
        p1.setName("Heinrich");

        Person p2 = new Person();
        p2.setName("Julius");

        Transaction t1 = new Transaction();
        t1.setPayer(p1);
        t1.setSum(500.0);
        t1.setExpense("Currywurst");

        Mockito.when(txService.getAllTransactions()).thenReturn(List.of(t1));

        // Act
        List<Reimbursement> listRbs = rbService.getReimbursements();

        //Assert
        assertEquals(1, listRbs.size());
        assertEquals("Heinrich", listRbs.getFirst().getCreditor().getName());
        assertEquals("Julius", listRbs.getFirst().getDebtor().getName());
        assertEquals(500.0, listRbs.getFirst().getSum());
    }

}