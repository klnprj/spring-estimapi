package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class UserManagementResourceTests {

//    @Autowired
//    private WebApplicationContext wac;
//
//    @Autowired
//    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private MockMvc mockMvc;

    private String accessToken;

    @Before
    public void setup() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        this.accessToken = TestAuthentication.obtainAccessToken(mockMvc, "admin@mail.ru", "password");
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql")
    })
    public void givenUserExists_whenGettingProfile_thenReturned() throws Exception {
//        {"class":"com.estima.User","id":3,"accountExpired":false,"accountLocked":false,"email":"admin@mail.ru","enabled":true,"name":"admin","password":"$2a$10$60mMZ0uJq7Ygpp0kaWcNoOwEXZRQVEdIVX5aYvyiC7cw5fyreOf3C","passwordExpired":false}
        mockMvc.perform(get("/api/users/profile")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(is("admin@mail.ru")))
                .andExpect(jsonPath("email").value(is("admin@mail.ru")))
                .andExpect(jsonPath("name").value(is("admin@mail.ru")))
                .andExpect(jsonPath("enabled").value(is(true)));
//                        hasKey("password"),
//                        hasKey("accountExpired"),
//                        hasKey("accountLocked"),
//                        hasKey("passwordExpired"),
//                        hasKey("class")
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql")
    })
    public void givenUsersExist_whenGettingList_thenReturned() throws Exception {
        mockMvc.perform(get("/api/users")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == 'admin@mail.ru')]").exists());
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql")
    })
    public void givenUserNotExists_whenPosting_thenCreated() throws Exception {
        //fixme: email instead of name in previous api
        mockMvc.perform(post("/api/users")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test@mail.ru\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(is("test@mail.ru")))
                .andExpect(jsonPath("email").value("test@mail.ru"))
                .andExpect(jsonPath("name").value("test@mail.ru"))
                .andExpect(jsonPath("enabled").value(true));
    }

    @Test
    @SqlGroup({
            @Sql("/testdata/users-records.sql"),
            @Sql("/testdata/authorities-records.sql"),
            @Sql("/testdata/clients-records.sql")
    })
    public void givenUserExists_whenGettingById_thenReturned() throws Exception {
        mockMvc.perform(get("/api/users/{id}", "admin@mail.ru")
                .header("Authorization", "Bearer " + accessToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(is("admin@mail.ru")));
    }
}
