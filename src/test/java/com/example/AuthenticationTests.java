package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceLauncher.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SqlGroup({
        @Sql("/testdata/users-records.sql"),
        @Sql("/testdata/authorities-records.sql")
//        @Sql("/testdata/clients-records.sql")
})
public class AuthenticationTests {

    private static final String CLIENT_ID = "client";
    private static final String CLIENT_SECRET = "clientpassword";

    @Autowired
    private MockMvc mockMvc;

    private String obtainAccessToken(String username, String password) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void givenAccessToken_whenGetSecureRequest_thenOk() throws Exception {
        String accessToken = obtainAccessToken("admin@mail.ru", "$2a$10$60mMZ0uJq7Ygpp0kaWcNoOwEXZRQVEdIVX5aYvyiC7cw5fyreOf3C");

        assertNotNull(accessToken);

        mockMvc.perform(get("/products")
                .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/products")).andExpect(status().isUnauthorized());
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
