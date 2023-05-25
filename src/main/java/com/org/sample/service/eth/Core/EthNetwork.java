package com.org.sample.service.eth.Core;

import java.io.IOException;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthChainId;
import org.web3j.protocol.http.HttpService;

import com.google.gson.JsonObject;

public class EthNetwork {

    /**
     * Get chain ID for Geth.
     * @return API response for chain id
     * @throws IOException Rest API Exception
     */
    public static JsonObject getChainId(String ethApiUrl) throws IOException {
        Web3j web3 = Web3j.build(new HttpService(ethApiUrl));
        EthChainId ethChainId = web3.ethChainId().send();

        if (ethChainId.hasError()) {
            return ApiResponseUtils.error(ethChainId.getError().getMessage());
        } else {
            JsonObject data = new JsonObject();
            data.addProperty("chain_id", ethChainId.getChainId());
            return ApiResponseUtils.success(data);
        }
    }
}
