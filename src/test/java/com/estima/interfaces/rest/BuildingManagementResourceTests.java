package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
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
        @Sql("/testdata/building-records.sql")
})
public class BuildingManagementResourceTests {

    @Autowired
    private MockMvc mockMvc;

    private String accessToken;

    @Before
    public void setup() throws Exception {
        this.accessToken = TestAuthentication.obtainAccessToken(mockMvc, "admin@mail.ru", "password");
    }

    @Test
    public void givenBuildingExists_whenGettingById_thenReturned() throws Exception {

        mockMvc.perform(get("/api/buildings/{id}", 1)
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(allOf(
                        hasKey(is("id")),
                        hasKey(is("name")),
                        hasKey(is("address")),
                        hasKey(is("location")),
                        hasKey(is("description")),
                        hasKey(is("client")),
                        hasKey(is("project")),
                        hasKey(is("author")),
                        hasKey(is("dateCreated")),
                        hasKey(is("lastUpdated")),
                        hasKey(is("dealers")),
                        hasKey(is("contacts")),
                        hasKey(is("status"))
                )));
    }
}
