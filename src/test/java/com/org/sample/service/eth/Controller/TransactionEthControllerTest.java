package com.org.sample.service.eth.Controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.sample.service.eth.DTO.SingleTransactionResponseDTO;
import com.org.sample.service.eth.DTO.TransactionResponseDTO;
import com.org.sample.service.eth.Model.TransactionEth;
import com.org.sample.service.eth.Model.TransactionHashEth;
import com.org.sample.service.eth.Service.TransactionHashEthService;

/**
 * Test class for testing transaction APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionEthControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TransactionHashEthService transactionEthService;

	/**
	 * Get an Transaction object with all the values being same string for testing
	 * 
	 * @param string
	 * @return
	 */
	public TransactionEth getTransactionObject(String string, Map<String, String> data) {
		TransactionEth transactionEth = new TransactionEth();
		transactionEth.setTransactionHash(string);
		transactionEth.setTransactionData(data);
		return transactionEth;
	}

	/**
	 * Get an TransactionHash object with all the values being same string for
	 * testing
	 * 
	 * @param string
	 * @return
	 */
	public List<TransactionHashEth> getTransactionHashObject(String string) {
		TransactionHashEth transactionHashEth = new TransactionHashEth();
		transactionHashEth.setTransactionHash(string);
		transactionHashEth.setFrom(string);
		transactionHashEth.setTo(string);
		return Arrays.asList(transactionHashEth);
	}

	/**
	 * Test function for get transaction hash for a given hash
	 * 
	 * @throws Exception
	 */
	@Test
	public void getTransactionByHash() throws Exception {
		String string = RandomStringUtils.randomAlphanumeric(8);
		Map<String, String> data = new HashMap<>();
		data.put("key", "value");
		SingleTransactionResponseDTO singleTransactionResponseDTO = new SingleTransactionResponseDTO(
				getTransactionObject(string, data));
		given(transactionEthService.getTransactionByHash(string)).willReturn(singleTransactionResponseDTO);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getGetBuilder("/txn/eth?txn_hash=" + string);
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.transaction", is(new ObjectMapper().convertValue(data, Object.class))));
	}

	/**
	 * Test function for get all transactions by address
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllTransactionByAddress() throws Exception {
		String string = RandomStringUtils.randomAlphanumeric(8);
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(string,
				getTransactionHashObject(string));
		given(transactionEthService.getAllTransactionByAddress(string)).willReturn(transactionResponseDTO);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getGetBuilder("/txn/eth/all?address=" + string);
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.transactions", is(transactionResponseDTO.getTransactions())));

	}

	/**
	 * Test function for get all transactions by sender address
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllTransactionBySenderAddress() throws Exception {
		String string = RandomStringUtils.randomAlphanumeric(8);
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(string,
				getTransactionHashObject(string));
		given(transactionEthService.getAllTransactionBySenderAddress(string))
				.willReturn(transactionResponseDTO);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getGetBuilder("/txn/eth/send/?address=" + string);
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.transactions", is(transactionResponseDTO.getTransactions())));

	}

	/**
	 * Test function for get all transactions by receiver address
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllTransactionByReceiverAddress() throws Exception {
		String string = RandomStringUtils.randomAlphanumeric(8);
		TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO(string,
				getTransactionHashObject(string));
		given(transactionEthService.getAllTransactionByReceiverAddress(string))
				.willReturn(transactionResponseDTO);
		MockHttpServletRequestBuilder builder = new TestHelper()
				.getGetBuilder("/txn/eth/receive?address=" + string);
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("status", is(true)))
				.andExpect(jsonPath("data.transactions", is(transactionResponseDTO.getTransactions())));

	}

}
