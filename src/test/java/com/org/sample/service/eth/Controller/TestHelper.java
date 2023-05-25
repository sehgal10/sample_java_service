package com.org.sample.service.eth.Controller;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelper {

    /**
     * Helper function to get POST builder for test cases
     * 
     * @param path
     * @param object
     * @return
     * @throws Exception
     */
    public MockHttpServletRequestBuilder getPostBuilder(String path, Object object) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(path)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(object));
        return builder;
    }

    /**
     * Helper function to get GET builder for test cases
     * 
     * @param path
     * @return
     * @throws Exception
     */
    public MockHttpServletRequestBuilder getGetBuilder(String path) throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(path);
        return builder;
    }

}
