package tech.tolpuddle.faershaer_backend;

import org.apache.coyote.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {

    final String PREFIX_FIRST_EVENT = "/events/1";
    final String PREFIX_SECOND_EVENT = "/events/2";

    final String TRANSACTIONS_ENDPOINT = "/transactions";
    final String PERSONS_ENDPOINT = "/persons";
    final String REIMBURSEMENTS_ENDPOINT = "/reimbursements";

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void shouldListTransactions() {
        RequestBuilder req = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + TRANSACTIONS_ENDPOINT);
        try {
            mockMvc.perform(req)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(8)))
                    .andExpect(jsonPath("$.[0].id", is("1")))
                    .andExpect(jsonPath("$.[0].payer", is("Jean")))
                    .andExpect(jsonPath("$.[0].expense", is("Stationnement")))
                    .andExpect(jsonPath("$.[0].sum", is(10.0)));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    @DirtiesContext
    void shouldCreateTransaction() {
        String txToAdd = "{\n" +
                "\t\"payerId\": \"1\",\n" +
                "\t\"expense\": \"Chicago Sunroof\",\n" +
                "\t\"sum\": 4.20\n" +
                "}\n";
        RequestBuilder post = MockMvcRequestBuilders.post(PREFIX_FIRST_EVENT + TRANSACTIONS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(txToAdd);

        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + TRANSACTIONS_ENDPOINT);
        try {
            mockMvc.perform(post)
                    .andExpect(status().isOk());
            mockMvc.perform(get)
                    .andExpect(jsonPath("$.length()", is(9)))
                    .andExpect(jsonPath("$.[8].payer", is("Jean")))
                    .andExpect(jsonPath("$.[8].expense", is("Chicago Sunroof")))
                    .andExpect(jsonPath("$.[8].sum", is(4.20)));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    @DirtiesContext
    void shouldDeleteTransaction() {
        RequestBuilder delete = MockMvcRequestBuilders.delete(PREFIX_FIRST_EVENT + TRANSACTIONS_ENDPOINT + "/1");
        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + TRANSACTIONS_ENDPOINT);
        try {
            mockMvc.perform(delete)
                    .andExpect(status().isNoContent());

            mockMvc.perform(get)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(7)))
                    .andExpect(jsonPath("$.[0].payer", not(is("Jean"))));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void shouldGetPersons() {
        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + PERSONS_ENDPOINT);

        try {
            mockMvc.perform(get)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(5)))
                    .andExpect(jsonPath("$.[0].name", is("Jean")));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void shouldProvideTotalsWhenGettingPeople() {
        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + PERSONS_ENDPOINT);

        try {
            mockMvc.perform(get)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(5)))
                    .andExpect(jsonPath("$.[0].name", is("Jean")))
                    .andExpect(jsonPath("$.[0].balance", is(18.0)));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    @DirtiesContext
    void shouldAddPerson() {
        String nameToAdd = "Sylvain";

        RequestBuilder post = MockMvcRequestBuilders.post(PREFIX_FIRST_EVENT + PERSONS_ENDPOINT)
                .contentType(MediaType.TEXT_PLAIN)
                .content(nameToAdd);

        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + PERSONS_ENDPOINT);

        try {
            mockMvc.perform(post)
                    .andExpect(status().isCreated());

            mockMvc.perform(get)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(6)))
                    .andExpect(jsonPath("$.[5].name", is("Sylvain")));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    @Disabled
    void shouldNotAddPersonWithConflictingName() {
        String nameToAdd = "Jean";

        RequestBuilder post = MockMvcRequestBuilders.post(PREFIX_FIRST_EVENT + PERSONS_ENDPOINT)
                .contentType(MediaType.TEXT_PLAIN)
                .content(nameToAdd);

        try {
            mockMvc.perform(post)
                    .andExpect(status().isConflict());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    @DirtiesContext
    void shouldRemovePerson() {
        RequestBuilder delete = MockMvcRequestBuilders.delete(PREFIX_FIRST_EVENT + PERSONS_ENDPOINT + "/1");

        try {
            mockMvc.perform(delete)
                    .andExpect(status().isNoContent());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void shouldReturnReimbursements() {
        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_FIRST_EVENT + REIMBURSEMENTS_ENDPOINT);

        try {
            mockMvc.perform(get)
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void shouldGetTransactionsForSecondEvent() {
        RequestBuilder get = MockMvcRequestBuilders.get(PREFIX_SECOND_EVENT+TRANSACTIONS_ENDPOINT);

        try {
            mockMvc.perform(get)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(5)));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void shouldReturnErrorWhenAskingForNonexistentEvent() throws Exception{
        RequestBuilder get = MockMvcRequestBuilders.get("/events/BLAH123"+TRANSACTIONS_ENDPOINT);

        mockMvc.perform(get)
                    .andExpect(status().isNotFound());
    }

    @Test
    @DirtiesContext
    @Disabled
    void shouldCreateNewEvent() throws Exception {
        RequestBuilder post = MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Skifahrt");

        mockMvc.perform(post)
                .andExpect(status().isCreated());
    }

    @Test
    @Disabled
    void shouldGetEvents() throws Exception {
        RequestBuilder get = MockMvcRequestBuilders.get("/events");

        mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    void shouldGetThisEvent() throws Exception {
        RequestBuilder get = MockMvcRequestBuilders.get("/events/2");

        mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label", is("Dîner au restaurant")))
                .andExpect(jsonPath("$.id", is("2")));
    }
}
