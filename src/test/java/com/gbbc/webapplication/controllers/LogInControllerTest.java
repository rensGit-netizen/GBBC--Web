package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;

@WebMvcTest(LogInController.class)
public class LogInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;


    public LogInControllerTest() {
        super();
    }


    @Test
    public void loginFormHandlerTest() throws Exception {
        //Arrange
        Customer checkedCustomer = new Customer();
        Customer fieldCustomer = new Customer();
        checkedCustomer.setUserName("Jan@gmail2.com");
        checkedCustomer.setPassword("der3");
        fieldCustomer.setUserName("Jan@gmail.com");
        fieldCustomer.setPassword("der3");
       // String failedString = "Controleer je wachtwoord en gebruikersnaam.";
        Mockito.when(customerRepository.searchByUserName(anyString())).thenReturn(checkedCustomer);

        MockHttpServletRequestBuilder postRequest =
                MockMvcRequestBuilders.post("/loginControle");
        postRequest.param("user_name", fieldCustomer.getUserName());
        postRequest.param("password", fieldCustomer.getPassword());
        //Act
        ResultActions result = mockMvc.perform(postRequest);
        //Assert
        result.andExpect(status().isOk());
        MvcResult returnedResponse = result.andReturn();
        assertThat(returnedResponse.getResponse().getContentAsString()).contains("Overzicht");

        result.andDo(print());
    }
}
