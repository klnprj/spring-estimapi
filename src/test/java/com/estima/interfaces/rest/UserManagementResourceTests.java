package com.estima.interfaces.rest;

import com.estima.ServiceLauncher;
import com.estima.TestAuthentication;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@Transactional
@SqlGroup({
        @Sql("/testdata/users-records.sql"),
        @Sql("/testdata/authorities-records.sql"),
        @Sql("/testdata/clients-records.sql")
})
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
    public void givenUserExists_whenGettingProfile_thenReturned() throws Exception {
//        {"class":"com.estima.User","id":3,"accountExpired":false,"accountLocked":false,"email":"admin@mail.ru","enabled":true,"name":"admin","password":"$2a$10$60mMZ0uJq7Ygpp0kaWcNoOwEXZRQVEdIVX5aYvyiC7cw5fyreOf3C","passwordExpired":false}
        mockMvc.perform(get("/api/users/profile")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(allOf(
                        hasProperty("id"),
                        hasProperty("email"),
                        hasProperty("name"),
                        hasProperty("enabled")
//                        hasProperty("password"),
//                        hasProperty("accountExpired"),
//                        hasProperty("accountLocked"),
//                        hasProperty("passwordExpired"),
//                        hasProperty("class")
                )));
    }

    @Test
    public void givenUserExists_whenGettingList_thenReturned() throws Exception {
        mockMvc.perform(get("/api/users")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(containsInAnyOrder((allOf(
                        hasProperty("id"),
                        hasProperty("email"),
                        hasProperty("name"),
                        hasProperty("enabled")
                )))));
    }
}
