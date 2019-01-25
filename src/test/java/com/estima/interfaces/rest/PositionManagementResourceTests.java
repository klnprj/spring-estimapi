package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
import com.estima.TestResource;
import com.estima.domain.BuildingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        @Sql("/testdata/building-records.sql"),
        @Sql("/testdata/dictionary-records.sql"),
        @Sql("/testdata/dictionary-item-records.sql")
})
public class PositionManagementResourceTests {

    private String accessToken;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuildingRepository buildingRepository;

    @Before
    public void setup() throws Exception {
        this.accessToken = TestAuthentication.obtainAccessToken(mockMvc, "admin@mail.ru", "password");
    }

    @Test
    public void givenPositionNotExists_whenPosting_thenCreated() throws Exception {
        assertEquals(0, buildingRepository.get(1L).get().getPositions().size());

        mockMvc.perform(post("/api/positions")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new TestResource("/testdata/position-create.json").asString()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("contactName").value("test-contact"))
                .andExpect(jsonPath("$").value(allOf(
                        hasKey("id"),
                        hasKey("building"),
                        hasKey("dateCreated"),
                        hasKey("dateShipped"),
                        hasKey("dealer"),
                        hasKey("contactName"),
                        hasKey("type"),
                        hasKey("spec"),
                        hasKey("grossPrice"),
                        hasKey("total"),
                        hasKey("status"),
                        hasKey("dealerPrice"),
                        hasKey("quantity")
                )));

        assertEquals(1, buildingRepository.get(1L).get().getPositions().size());
    }

    @Test
    public void givenPositionsExist_whenGettingList_thenReturned() throws Exception {
        //todo:
    }

    @Test
    public void givenPositionExists_whenGettingById_thenReturned() throws Exception {
        //todo:
    }

    @Test
    public void givenPositionExists_whenDeleting_thenRemoved() throws Exception {
        //todo:
    }

    @Test
    public void givenPositionExists_whenPutting_thenUpdated() throws Exception {
        //todo:
    }
}
