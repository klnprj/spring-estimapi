package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
import com.estima.TestResource;
import com.estima.domain.BuildingRepository;
import com.estima.domain.Position;
import org.junit.Assert;
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

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
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
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql"),
            @Sql("/testdata/building-records.sql"),
            @Sql("/testdata/dictionary-records.sql"),
            @Sql("/testdata/dictionary-item-records.sql")
    })
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
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql"),
            @Sql("/testdata/building-records.sql"),
            @Sql("/testdata/dictionary-records.sql"),
            @Sql("/testdata/dictionary-item-records.sql"),
            @Sql("/testdata/position-records.sql")
    })
    public void givenPositionsExist_whenGettingList_thenReturned() throws Exception {
        assertEquals(2, buildingRepository.get(1L).get().getPositions().size());

        mockMvc.perform(get("/api/positions?buildingId={buildingId}", 1L)
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("positionList").value(hasSize(2)))
                .andExpect(jsonPath("positionList[0]").value(allOf(
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
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql"),
            @Sql("/testdata/building-records.sql"),
            @Sql("/testdata/dictionary-records.sql"),
            @Sql("/testdata/dictionary-item-records.sql"),
            @Sql("/testdata/position-records.sql")
    })
    public void givenPositionExists_whenGettingById_thenReturned() throws Exception {
        assertNotNull(buildingRepository.get(1L).get().position(1L));

        mockMvc.perform(get("/api/positions/{positionId}", 1L)
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(is(1)))
                .andExpect(jsonPath("building.id").value(is(1)))
                .andExpect(jsonPath("dateCreated").value(notNullValue()))
                .andExpect(jsonPath("dateShipped").value(nullValue()))
                .andExpect(jsonPath("dealer.id").value(is(100)))
                .andExpect(jsonPath("contactName").value(is("Manager 1")))
                .andExpect(jsonPath("type").value(nullValue()))
                .andExpect(jsonPath("spec").value(nullValue()))
                .andExpect(jsonPath("grossPrice").value(nullValue()))
                .andExpect(jsonPath("total").value(nullValue()))
                .andExpect(jsonPath("status").value(nullValue()))
                .andExpect(jsonPath("dealerPrice").value(nullValue()))
                .andExpect(jsonPath("quantity").value(nullValue()));
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql"),
            @Sql("/testdata/building-records.sql"),
            @Sql("/testdata/dictionary-records.sql"),
            @Sql("/testdata/dictionary-item-records.sql"),
            @Sql("/testdata/position-records.sql")
    })
    public void givenPositionExists_whenDeleting_thenRemoved() throws Exception {
        assertEquals(2, buildingRepository.get(1L).get().getPositions().size());

        mockMvc.perform(delete("/api/positions/{positionId}", 1L)
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(1, buildingRepository.get(1L).get().getPositions().size());
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql"),
            @Sql("/testdata/building-records.sql"),
            @Sql("/testdata/dictionary-records.sql"),
            @Sql("/testdata/dictionary-item-records.sql"),
            @Sql("/testdata/position-records.sql")
    })
    public void givenPositionExists_whenPutting_thenUpdated() throws Exception {
        mockMvc.perform(put("/api/positions/{positionId}", 1L)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new TestResource("/testdata/position-update.json").asString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(is(1)))
                .andExpect(jsonPath("building.id").value(is(1)))
                .andExpect(jsonPath("dateCreated").value(notNullValue()))
                .andExpect(jsonPath("dateShipped").value(is("2018-12-30")))
                .andExpect(jsonPath("dealer.id").value(is(100)))
                .andExpect(jsonPath("contactName").value(is("test-contact2")))
                .andExpect(jsonPath("type").value(is("contact-type2")))
                .andExpect(jsonPath("spec").value(is("spec-2")))
                .andExpect(jsonPath("grossPrice").value(is("1000")))
                .andExpect(jsonPath("total").value(is("2000")))
                .andExpect(jsonPath("status").value(is("shipped")))
                .andExpect(jsonPath("dealerPrice").value(is(150)))
                .andExpect(jsonPath("quantity").value(is(6)));

//        Position position = buildingRepository.getPositionBuilding(1L).get().position(1L);
//
//        assertThat(position, allOf(
//                hasProperty("contactName", is("test-contact2")),
//                hasProperty("type", is("contact-type2")),
//                hasProperty("spec", is("spec-2")),
//                hasProperty("grossPrice", is("1000")),
//                hasProperty("total", is("2000")),
//                hasProperty("status", is("shipped")),
//                hasProperty("dateShipped", is("2018-12-30")),
//                hasProperty("dealerPrice", is(150)),
//                hasProperty("quantity", is(6))
//        ));
    }
}
