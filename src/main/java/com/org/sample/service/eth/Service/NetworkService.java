package com.org.sample.service.eth.Service;

import com.google.gson.JsonObject;

/**
 * Interface for network service
 */
public interface NetworkService {

    /**
     * 
     * @return JsonObject containing chain id
     * @throws Exception
     */
    JsonObject getChainId() throws Exception;

}