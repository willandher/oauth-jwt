package com.oauth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.jwt.dto.LoginRequest;
import com.oauth.jwt.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterUserTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private MockHttpServletRequest request;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }


    @Test
    public void testunauthorizedRegister() throws Exception{

        String body = objectMapper.writeValueAsString(user());
        mockMvc.perform(post("/api/auth/register-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                //.andExpect(redirectedUrl("/user/home"))
                .andReturn()
                .getRequest();

    }


    @Test
    public void testRegister() throws Exception{

        String body = objectMapper.writeValueAsString(user());
        mockMvc.perform(post("/api/auth/register-user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer "+userLogin())
                .content(body)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(redirectedUrl("/user/home"))
                .andReturn()
                .getRequest();

    }




    public UserDto user(){
        UserDto user = new UserDto();
        user.setName("Will Mendoza");
        user.setEmail("willg@gmail.com");
        user.setPassword("Will34");
        user.setPhones(new HashSet<>());
        return user;
    }

    public String userLogin()  throws Exception
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
        MvcResult a =  (MvcResult)request.getAttribute("org.springframework.test.web.servlet.MockMvc.MVC_RESULT_ATTRIBUTE");
        return objectMapper.readTree(a.getResponse().getContentAsString()).get("token").textValue();

    }
}
