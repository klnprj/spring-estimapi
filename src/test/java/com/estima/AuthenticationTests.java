package com.estima;

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

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@SqlGroup({
        @Sql("/testdata/users-records.sql"),
        @Sql("/testdata/authorities-records.sql"),
        @Sql("/testdata/clients-records.sql")
})
public class AuthenticationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenAccessToken_whenGetSecureRequest_thenOk() throws Exception {
        String accessToken = TestAuthentication.obtainAccessToken(mockMvc, "admin@mail.ru", "password");

        assertNotNull(accessToken);

        mockMvc.perform(get("/api/buildings")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/buildings")).andExpect(status().isUnauthorized());
    }

//    @Test
//    public void givenInvalidRole_whenGetSecureRequest_thenForbidden() throws Exception {
//        String accessToken = obtainAccessToken("user", "user");
//        mockMvc.perform(get("/products")
//                .header("Authorization", "Bearer " + accessToken))
//                .andExpect(status().isForbidden());
//    }

//    @Test
//    public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
//        final String accessToken = obtainAccessToken("admin", "nimda");
//
//        String employeeString = "{\"email\":\"" + EMAIL + "\",\"name\":\"" + NAME + "\",\"age\":30}";
//
//        // @formatter:off
//
//        mockMvc.perform(post("/employee")
//                .header("Authorization", "Bearer " + accessToken)
//                .contentType(CONTENT_TYPE)
//                .content(employeeString)
//                .accept(CONTENT_TYPE))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(get("/employee")
//                .param("email", EMAIL)
//                .header("Authorization", "Bearer " + accessToken)
//                .accept(CONTENT_TYPE))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(CONTENT_TYPE))
//                .andExpect(jsonPath("$.name", is(NAME)));
//
//        // @formatter:on
//    }
}
