package com.oauth.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.jwt.dto.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private MockHttpServletRequest request;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }




    @Test
    public void testUserLogin()  throws Exception
    {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("willandherg@gmail.com");
        loginRequest.setPassword("Will22");
        String body = objectMapper.writeValueAsString(loginRequest);
        MockHttpServletRequest request = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(redirectedUrl("/user/home"))
                .andReturn()
                .getRequest();

    }


    @Test
    public void testFailLoginByNotFound()  throws Exception
    {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("willandhergg@gmail.com");
        loginRequest.setPassword("Will22");
        String body = objectMapper.writeValueAsString(loginRequest);
        MockHttpServletRequest request = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                //.andExpect(redirectedUrl("/user/home"))
                .andReturn()
                .getRequest();

    }


    @Test
    public void testFailLoginByFormatoEmail()  throws Exception
    {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("willandhergggmail.com");
        loginRequest.setPassword("Will22");
        String body = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }


    @Test
    public void testFailLoginByFormatoPassword()  throws Exception
    {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("willandher@gmail.com");
        loginRequest.setPassword("will234");
        String body = objectMapper.writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }



}
