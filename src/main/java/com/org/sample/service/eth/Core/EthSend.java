package com.org.sample.service.eth.Core;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;

import com.google.gson.JsonObject;

public class EthSend {

    /**
     * Sends the raw transaction.
     * @return API response with transaction hash
     * @throws IOException Rest API Exception
     */
    public static JsonObject sendRawTransaction(final String signature, String ethApiUrl) throws IOException {
        Web3j web3 = Web3j.build(new HttpService(ethApiUrl));
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(signature).send();

        if(ethSendTransaction.getError() != null) {
            return ApiResponseUtils.error(ethSendTransaction.getError().getMessage());
        } else {
            JsonObject data = new JsonObject();
            data.addProperty("tx_hash", ethSendTransaction.getTransactionHash());
            return ApiResponseUtils.success(data);
        }
    }
}
