package com.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class UserControllerTest {

	private MockMvc mockMvc;
	@Autowired
	   WebApplicationContext webApplicationContext;

	

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) webApplicationContext).build();

	}

	
	@Test
	public void getProductsList() throws Exception {
	   String uri = "/all";
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   
	}
}
