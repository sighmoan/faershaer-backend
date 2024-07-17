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
    @Mock
    PersonService personService;
    @InjectMocks
    ReimbursementService rbService;

    Transaction createTransaction(Person payer, Double sum, String expense) {
        Transaction t = new Transaction();
        t.setSum(sum);
        t.setPayer(payer);
        t.setExpense(expense);
        return t;
    }

    @Test
    void shouldGenerateSimpleReimbursement() {
        Person p1 = new Person();
        p1.setName("Heinrich");

        Person p2 = new Person();
        p2.setName("Julius");

        Transaction t1 = createTransaction(p1, 500.0, "Currywurst");

        Mockito.when(personService.getAllPersons()).thenReturn(List.of(p1, p2));
        Mockito.when(txService.getAllTransactions()).thenReturn(List.of(t1));
        Mockito.when(txService.getBalance(p1)).thenReturn(500.0);
        Mockito.when(txService.getBalance(p2)).thenReturn(0.0);


        // Act
        List<Reimbursement> listRbs = rbService.getReimbursements();

        //Assert
        assertEquals(1, listRbs.size());
        assertEquals("Heinrich", listRbs.getFirst().getCreditor().getName());
        assertEquals("Julius", listRbs.getFirst().getDebtor().getName());
        assertEquals(250.0, listRbs.getFirst().getSum());
    }

    @Test
    void shouldGenerateThreeWayReimbursement() {
        Person p1 = new Person();
        p1.setName("Heinrich");
        Person p2 = new Person();
        p2.setName("Julius");
        Person p3 = new Person();
        p3.setName("Heinz");

        Transaction t1 = createTransaction(p1, 300.0, "Bier");
        Transaction t2 = createTransaction(p2, 240.0, "Gewurze");
        Transaction t3 = createTransaction(p3, 60.0, "Limonade");

        Mockito.when(personService.getAllPersons()).thenReturn(List.of(p1, p2, p3));
        Mockito.when(txService.getAllTransactions()).thenReturn(List.of(t1, t2, t3));
        Mockito.when(txService.getBalance(p1)).thenReturn(300.0);
        Mockito.when(txService.getBalance(p2)).thenReturn(240.0);
        Mockito.when(txService.getBalance(p3)).thenReturn(60.0);
        /*
            Fair share is 200 each
            Heinz should pay Heinrich 100 to bring Heinrich to 200
            Then, Heinz should pay Julius 40 to bring Julius to 200
            This will add 140 to Heinz' bill, which brings it to 200.
            The total reimbursement is 140.
         */

        //Act
        List<Reimbursement> listRbs = rbService.getReimbursements();

        //Assert
        assertEquals(2, listRbs.size());
        assertEquals("Heinrich", listRbs.getFirst().getCreditor().getName());
        assertEquals("Heinz", listRbs.getFirst().getDebtor().getName());
        assertEquals(100, listRbs.getFirst().getSum());

        assertEquals("Julius", listRbs.getLast().getCreditor().getName());
        assertEquals("Heinz", listRbs.getLast().getDebtor().getName());
        assertEquals(40, listRbs.getLast().getSum());

        assertEquals(140, listRbs.stream().map(Reimbursement::getSum).reduce(0.0, Double::sum));

    }

}