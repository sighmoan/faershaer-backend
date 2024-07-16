package tech.tolpuddle.faershaer_backend;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {

    final String TRANSACTIONS_ENDPOINT = "/transactions";
    final String PERSONS_ENDPOINT = "/persons";

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void shouldListTransactions() {
        RequestBuilder req = MockMvcRequestBuilders.get(TRANSACTIONS_ENDPOINT);
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
                "\t\"payer\": \"Slippin' Jimmy\",\n" +
                "\t\"expense\": \"Chicago Sunroof\",\n" +
                "\t\"sum\": 4.20\n" +
                "}\n";
        RequestBuilder post = MockMvcRequestBuilders.post(TRANSACTIONS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(txToAdd);

        RequestBuilder get = MockMvcRequestBuilders.get(TRANSACTIONS_ENDPOINT);
        try {
            mockMvc.perform(post)
                    .andExpect(status().isOk());
            mockMvc.perform(get)
                    .andExpect(jsonPath("$.length()", is(9)))
                    .andExpect(jsonPath("$.[8].payer", is("Slippin' Jimmy")))
                    .andExpect(jsonPath("$.[8].expense", is("Chicago Sunroof")))
                    .andExpect(jsonPath("$.[8].sum", is(4.20)));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    @DirtiesContext
    void shouldDeleteTransaction() {
        RequestBuilder delete = MockMvcRequestBuilders.delete(TRANSACTIONS_ENDPOINT + "/1");
        RequestBuilder get = MockMvcRequestBuilders.get(TRANSACTIONS_ENDPOINT);
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
        RequestBuilder get = MockMvcRequestBuilders.get(PERSONS_ENDPOINT);

        try {
            mockMvc.perform(get)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(5)))
                    .andExpect(jsonPath("$.[0].name", is("Jean")));
        } catch(Exception ex) {
            fail(ex.getMessage());
        }
    }
}
