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
import com.org.sample.service.eth.DTO.FeesRequestDTO;
import com.org.sample.service.eth.Service.FeesService;

/**
 * Test class for testing fees APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
public class FeesControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	FeesService feesService;

	/**
	 * Test function to estimate fees of a transaction
	 * 
	 * @throws Exception
	 */
	@Test
	public void estimateFees() throws Exception {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", true);
		JsonObject jsonObject2 = new JsonObject();
		jsonObject2.addProperty("gas_limit", "gas_limit");
		jsonObject2.addProperty("gas_price_low", "gas_price_low");
		jsonObject2.addProperty("gas_price_average", "gas_price_average");
		jsonObject2.addProperty("gas_price_high", "gas_price_high");
		jsonObject.add("data", jsonObject2);
		jsonObject.addProperty("error", "");
		given(feesService.estimateFees(new FeesRequestDTO())).willReturn(jsonObject);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getPostBuilder("/fees/eth", new FeesRequestDTO());
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.gas_limit", is("gas_limit")))
				.andExpect(jsonPath("data.gas_price_low", is("gas_price_low")))
				.andExpect(jsonPath("data.gas_price_average", is("gas_price_average")))
				.andExpect(jsonPath("data.gas_price_high", is("gas_price_high")));

	}
}
