package com.org.sample.service.eth.Service.Implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.org.sample.service.eth.Core.EthSend;
import com.org.sample.service.eth.Service.SendService;

/**
 * Service class for SendService interface
 */
@Service
public class SendServiceImplementation implements SendService {

    @Value(value = "${eth.api.url}")
    private String ethApiUrl;

    /**
     * Sends raw transaction to blockchain
     * 
     * @param txnRaw
     * @return JsonObject containing transaction hash
     * @throws Exception
     */
    @Override
    public JsonObject sendRawTransaction(String txnRaw) throws Exception {
        return EthSend.sendRawTransaction(txnRaw, ethApiUrl);
    }
}
