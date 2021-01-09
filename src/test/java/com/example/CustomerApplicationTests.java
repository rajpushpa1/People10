package com.example;

import com.example.controller.CustomerController;
import com.example.pojo.Customer;
import com.example.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;



@RunWith(SpringRunner.class)
@WebMvcTest(value= CustomerController.class)
public class CustomerApplicationTests {

	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private CustomerService customerService;


	@Test
	public void saveCustomerTest() throws Exception {

		final Customer customer = new Customer();
		customer.setUserName("test");
		customer.setFirstName("sam");
		customer.setLastName("ryan");
		customer.setEmail("bc@gmail.com");
		customer.setPassword("12345");
		String json = this.mapToJson(customer);
		String URI = "/api/customer";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();
		System.out.println(outputInJson);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}


	@Test
	public void getCustomer() throws Exception {

		final Customer customer = new Customer();
		String json = this.mapToJson(customer);
		String URI = "/api/customer/1";
		Mockito.when(customerService.getEmployeeById("1")).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = result.getResponse().getContentAsString();
		System.out.println(outputInJson);
		assertEquals(200, response.getStatus());
	}


	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
