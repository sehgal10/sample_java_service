package com.org.sample.service.eth.Service;

import com.google.gson.JsonObject;

/**
 * Interface for send service
 */
public interface SendService {

    /**
     * Sends raw transaction to blockchain
     * 
     * @param txnRaw
     * @return JsonObject containing transaction hash
     * @throws Exception
     */
    JsonObject sendRawTransaction(String txnRaw) throws Exception;

}