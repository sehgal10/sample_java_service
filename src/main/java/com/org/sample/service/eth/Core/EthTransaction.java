package com.org.sample.service.eth.Core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class EthTransaction {

    /**
     * Returns transaction data for an address from ethereum blockchain.
     *
     * @param addr address
     * @return transaction data
     * @throws IOException I/O exceptions from API
     */
    public static JsonObject getTransactions(final String addr, final String ethTransactionApiUrl, final String ethTransactionApiToken) throws IOException, URISyntaxException {

        String address = addr.startsWith("0x") ? addr : "0x" + addr;

        JsonObject txnRequest = new JsonObject();
        txnRequest.addProperty("module", "account");
        txnRequest.addProperty("action", "txlist");
        txnRequest.addProperty("address", address);
        txnRequest.addProperty("apikey", ethTransactionApiToken);

        JsonObject txnResponse = sendAPIGetRequest(txnRequest, ethTransactionApiUrl);

        if (txnResponse.get("status").getAsString().equals("1")) {
            return ApiResponseUtils.success(txnResponse.get("result").getAsJsonArray());
        } else {
            return ApiResponseUtils.error(txnResponse.get("message").getAsString());
        }
    }

    /**
     * Standard HTTP GET request with query parameters
     * @param requestParams query parameters
     * @param ethTransactionApiUrl ETH API URL
     * @return Response from HTTP GET
     * @throws URISyntaxException URI Exception
     * @throws IOException I/O exceptions from API
     */
    private static JsonObject sendAPIGetRequest(final JsonObject requestParams, final String ethTransactionApiUrl) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(ethTransactionApiUrl);
        for (String key: requestParams.keySet()) {
            builder.setParameter(key, requestParams.get(key).getAsString());
        }
        HttpGet get = new HttpGet(builder.build());
        HttpResponse response = HttpClientBuilder.create().build().execute(get);
        String jsonStr = EntityUtils.toString(response.getEntity());

        return new Gson().fromJson(jsonStr, JsonObject.class);
    }
}
