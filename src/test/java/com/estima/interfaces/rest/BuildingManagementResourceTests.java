package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
import com.estima.app.ManageBuilding;
import com.estima.interfaces.rest.representation.BuildingRepresentation;
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
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        @Sql("/testdata/dictionary-records.sql"),
        @Sql("/testdata/dictionary-item-records.sql"),
        @Sql("/testdata/building-records.sql")
})
public class BuildingManagementResourceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ManageBuilding manageBuilding;

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

        Long buildingId = 1L;
        BuildingRepresentation building = new BuildingRepresentation(manageBuilding.get(buildingId));

        assertThat(building, allOf(
                hasProperty("id", is(1L)),
                hasProperty("name", is("Building 1")),
                hasProperty("address", is("Address_1")),
                hasProperty("location", is("POINT(40.0 40.0)")),
                hasProperty("description", is("Description 1")),
                hasProperty("author", allOf(
                        hasProperty("id", is("admin@mail.ru")),
                        hasProperty("name", is("admin@mail.ru")),
                        hasProperty("email", is("admin@mail.ru")),
                        hasProperty("enabled", is(true))
                )),
                hasProperty("client", allOf(
                        hasProperty("id", is(10L)),
                        hasProperty("title", is("Company 1")),
                        hasProperty("contactName", is("User 1")),
                        hasProperty("contactPosition", is("Manager 1")),
                        hasProperty("phone", is("111111"))
                ))
        ));
    }

    @Test
    public void givenBuildingsExist_whenGettingList_thenReturned() throws Exception {
        mockMvc.perform(get("/api/buildings")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingList").value(hasSize(3)))
                .andExpect(jsonPath("$.buildingList[*].name").value(containsInAnyOrder("Building 1", "Building 2", "Building 3")));
    }

    @Test
    public void givenBuildingsExist_whenSearching_thenFound() throws Exception {
        mockMvc.perform(get("/api/buildings?q=_3")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingList").value(hasSize(1)))
                .andExpect(jsonPath("$.buildingList[0].name").value(is("Building 3")));
    }

    @Test
    public void givenBuildingsExist_whenFilteringByAuthor_thenFound() throws Exception {
        mockMvc.perform(get("/api/buildings")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingList").value(hasSize(3)))
                .andExpect(jsonPath("$.buildingList[*].name").value(containsInAnyOrder("Building 1", "Building 2", "Building 3")));
    }

    @Test
    public void givenBuildingsExist_whenFilteringByStatus_thenFound() throws Exception {
        mockMvc.perform(get("/api/buildings")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingList").value(hasSize(3)))
                .andExpect(jsonPath("$.buildingList[*].name").value(containsInAnyOrder("Building 1", "Building 2", "Building 3")));
    }

    @Test
    public void givenBuildingsExist_whenFilteringByLastUpdatedFrom_thenFound() throws Exception {
        mockMvc.perform(get("/api/buildings")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingList").value(hasSize(3)))
                .andExpect(jsonPath("$.buildingList[*].name").value(containsInAnyOrder("Building 1", "Building 2", "Building 3")));
    }

    @Test
    public void givenBuildingsExist_whenFilteringByDealer_thenFound() throws Exception {
        mockMvc.perform(get("/api/buildings")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingList").value(hasSize(3)))
                .andExpect(jsonPath("$.buildingList[*].name").value(containsInAnyOrder("Building 1", "Building 2", "Building 3")));
    }

    @Test
    public void givenBuildingsExist_whenGettingLocations_thenReturned() throws Exception {
        //todo:
    }

    @Test
    public void givenBuildingNotExists_whenPosting_thenCreated() throws Exception {
        mockMvc.perform(post("/api/buildings")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Building 10\", \"address\": \"Address 10\", \"location\": \"POINT(100, 100)\", \"description\": \"Description 10\", \"status\": \"unused?\", \"client\": {\"id\": 10}}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andExpect(jsonPath("$.name").value(is("Building 10")))
                .andExpect(jsonPath("$.author.id").value(is("admin@mail.ru")));
    }

    @Test
    public void givenBuildingExists_whenPutting_thenUpdated() throws Exception {
        mockMvc.perform(put("/api/buildings/{id}", 1L)
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Building 10\", \"address\": \"Address 10\", \"location\": \"POINT(100, 100)\", \"description\": \"Description 10\", \"status\": \"unused?\", \"client\": {\"id\": 20}}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is(1)))
                .andExpect(jsonPath("$.author.id").value(is("admin@mail.ru")))
                .andExpect(jsonPath("$.name").value(is("Building 10")))
                .andExpect(jsonPath("$.address").value(is("Address 10")))
                .andExpect(jsonPath("$.location").value(is("POINT(100, 100)")))
                .andExpect(jsonPath("$.description").value(is("Description 10")))
                .andExpect(jsonPath("$.client.id").value(is(20)));

    }
}
