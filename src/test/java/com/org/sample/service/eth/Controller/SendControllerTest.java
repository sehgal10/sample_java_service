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
import com.org.sample.service.eth.DTO.SendRequestDTO;
import com.org.sample.service.eth.Service.SendService;

/**
 * Test class for testing send APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SendControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SendService sendService;

	/**
	 * Test function to send transaction to blockchain
	 * 
	 * @throws Exception
	 */
	@Test
	public void sendRawTransaction() throws Exception {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", true);
		JsonObject jsonObject2 = new JsonObject();
		jsonObject2.addProperty("txn_hash", "some hash");
		jsonObject.add("data", jsonObject2);
		jsonObject.addProperty("error", "");
		given(sendService.sendRawTransaction(null)).willReturn(jsonObject);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getPostBuilder("/send/eth", new SendRequestDTO());
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.txn_hash", is("some hash")));

	}
}
