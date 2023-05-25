package com.org.sample.service.eth.Service.Implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.org.sample.service.eth.Core.EthNetwork;
import com.org.sample.service.eth.Service.NetworkService;

/**
 * Service class for NetworkService interface
 */
@Service
public class NetworkServiceImplementation implements NetworkService {

    @Value(value = "${eth.api.url}")
    private String ethApiUrl;

    /**
     * 
     * @return JsonObject containing chain id
     * @throws Exception
     */
    @Override
    public JsonObject getChainId() throws Exception {
        return EthNetwork.getChainId(ethApiUrl);
    }
}
