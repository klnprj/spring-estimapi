package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
import com.estima.app.DictionaryItemSelection;
import com.estima.interfaces.rest.representation.BuildingRepresentation;
import com.estima.interfaces.rest.representation.DictionaryItemRepresentation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SqlGroup({
        @Sql("/testdata/users-records.sql"),
        @Sql("/testdata/authorities-records.sql"),
        @Sql("/testdata/clients-records.sql"),
        @Sql("/testdata/dictionary-records.sql"),
        @Sql("/testdata/dictionary-item-records.sql")
})
public class DictionaryItemManagementResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DictionaryItemSelection dictionaryItemSelection;

    private String accessToken;

    @Before
    public void setup() throws Exception {
        this.accessToken = TestAuthentication.obtainAccessToken(mockMvc, "admin@mail.ru", "password");
    }

    @Test
    public void givenDictionaryItemExists_whenGettingById_thenReturned() throws Exception {

        mockMvc.perform(get("/api/dictionaries/{dictionaryId}/items/{id}", 1, 10)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(allOf(
                        hasKey(is("id")),
                        hasKey(is("title")),
                        hasKey(is("contactName")),
                        hasKey(is("contactPosition")),
                        hasKey(is("phone")),
                        hasKey(is("name")),
                        hasKey(is("dictionary"))
                )));

        DictionaryItemRepresentation itemRepresentation = new DictionaryItemRepresentation(dictionaryItemSelection.get(1L, 10L));

        assertThat(itemRepresentation, allOf(
                hasProperty("id", is(10L)),
                hasProperty("title", is("Company 1")),
                hasProperty("contactName", is("User 1")),
                hasProperty("contactPosition", is("Manager")),
                hasProperty("phone", is("111111")),
                hasProperty("name", is("Company 1")),
                hasProperty("dictionary", allOf(
                        hasProperty("key", is("DL")),
                        hasProperty("name", is("Dealers"))
                ))
        ));
    }
}
