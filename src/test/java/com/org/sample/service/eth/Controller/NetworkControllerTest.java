package com.org.sample.service.eth.Controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.google.gson.JsonObject;
import com.org.sample.service.eth.Service.NetworkService;

/**
 * Test class for testing network APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
public class NetworkControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	NetworkService networkService;

	/**
	 * Test function to get chain id
	 * 
	 * @throws Exception
	 */
	@Test
	public void getChainId() throws Exception {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", true);
		JsonObject jsonObject2 = new JsonObject();
		jsonObject2.addProperty("chain_id", "some id");
		jsonObject.add("data", jsonObject2);
		jsonObject.addProperty("error", "");
		given(networkService.getChainId()).willReturn(jsonObject);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getGetBuilder("/network/eth");
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.chain_id", is("some id")));

	}
}
