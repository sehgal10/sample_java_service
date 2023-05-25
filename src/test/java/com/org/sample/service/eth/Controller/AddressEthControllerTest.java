package com.org.sample.service.eth.Controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.org.sample.service.eth.DTO.AddressBalanceResponseDTO;
import com.org.sample.service.eth.DTO.AddressNonceResponseDTO;
import com.org.sample.service.eth.Model.AddressEth;
import com.org.sample.service.eth.Service.AddressEthService;

/**
 * Test class for testing address APIs
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AddressEthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AddressEthService addressEthService;

    /**
     * Get an address object with all the values being same string for testing
     * 
     * @param string
     * @return
     */
    public AddressEth getAddressObject(String string) {
        AddressEth addressEth = new AddressEth();
        addressEth.setAddress(string);
        addressEth.setBalance(string);
        addressEth.setNonce(string);
        return addressEth;
    }

    /**
     * Test function for get balance for address API
     * 
     * @throws Exception
     */
    @Test
    public void getBalanceByAddress() throws Exception {
        String string = RandomStringUtils.randomAlphanumeric(8);
        AddressBalanceResponseDTO addressBalanceResponseDTO = new AddressBalanceResponseDTO(getAddressObject(string));
        given(addressEthService.getBalanceByAddress(string)).willReturn(addressBalanceResponseDTO);
        MockHttpServletRequestBuilder builder = new TestHelper()
                .getGetBuilder("/address/balance/eth?address=" + string);
        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("status", is(true)))
                .andExpect(jsonPath("data.balance", is(addressBalanceResponseDTO.getBalance())));

    }

    /**
     * Test function for get nonce by address API
     * 
     * @throws Exception
     */
    @Test
    public void getNonceByAddress() throws Exception {
        String string = RandomStringUtils.randomAlphanumeric(8);
        AddressNonceResponseDTO addressNonceResponseDTO = new AddressNonceResponseDTO(getAddressObject(string));
        given(addressEthService.getNonceByAddress(string)).willReturn(addressNonceResponseDTO);
        MockHttpServletRequestBuilder builder = new TestHelper().getGetBuilder("/address/nonce/eth?address=" + string);
        mockMvc.perform(builder).andExpect(status().isOk())
                .andExpect(jsonPath("status", is(true)))
                .andExpect(jsonPath("data.nonce", is(addressNonceResponseDTO.getNonce())));

    }

}
