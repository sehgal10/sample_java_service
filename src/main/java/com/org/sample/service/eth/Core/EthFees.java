package com.org.sample.service.eth.Core;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthMaxPriorityFeePerGas;
import org.web3j.protocol.http.HttpService;

import com.google.gson.JsonObject;

public class EthFees {

    /**
     * Estimates the gas required for transferring ETH
     * @return API response for gas estimation
     * @throws IOException Rest API Exception
     */
    public static JsonObject estimateGasLimit(String from, String to, String value, String data, String ethApiUrl) throws IOException {

        // Validate from field
        JsonObject fromValidation = EthValidation.validateAddress(from);
        if (!fromValidation.get("status").getAsBoolean()) {
            return ApiResponseUtils.error("Error in From field: " + fromValidation.get("error").getAsString());
        }

        // If to is blank, and data is not blank, then assume contract transaction
        // if both to and data are blank, then throw error
        if (data.equals("")) {
            if (to == null) {
                return ApiResponseUtils.error("Both 'to' and 'data' fields cannot be blank");
            } else if (to.length() == 0) {
                return ApiResponseUtils.error("Both 'to' and 'data' fields cannot be blank");
            }
        }

        // If value is blank, then make it as 0
        if (value.equals("")) {
            value = "0";
        }

        // Validate amount field : must be all integers
        JsonObject valueValidation = EthValidation.validateAmount(value);
        if (!valueValidation.get("status").getAsBoolean()) {
            return ApiResponseUtils.error("Error in value field: " + valueValidation.get("error").getAsString());
        }

        if (to != null) {
            // Validate to field
            if (to.length() > 0) {
                JsonObject toValidation = EthValidation.validateAddress(to);
                if (!toValidation.get("status").getAsBoolean()) {
                    return ApiResponseUtils.error("Error in To field: " + toValidation.get("error").getAsString());
                }

                to = to.startsWith("0x") ? to : "0x" + to;
            }
        }

        // Send the request to estimate gas

        Web3j web3 = Web3j.build(new HttpService(ethApiUrl));

        Transaction transaction = Transaction.createFunctionCallTransaction(
                from.startsWith("0x")? from : "0x" + from,
                null,
                null,
                null,
                to,
                new BigInteger(value, 10),
                data.startsWith("0x")? data : "0x" + data);
        EthEstimateGas ethEstimateGas = web3.ethEstimateGas(transaction).send();

        if (ethEstimateGas.hasError()) {
            return ApiResponseUtils.error(ethEstimateGas.getError().getMessage());
        } else {
            JsonObject result = new JsonObject();
            result.addProperty("gas_limit", ethEstimateGas.getAmountUsed());
            return ApiResponseUtils.success(result);
        }
    }

    /**
     * Gets gas price estimation.
     * @return API response for gas price estimation
     * @throws IOException Rest API Exception
     */
    public static JsonObject estimateGasPrice(String ethApiUrl) throws IOException {

        // Return if error in getting base fee
        JsonObject gasPriceJson = getGasPrice(ethApiUrl);
        if (!gasPriceJson.get("status").getAsBoolean()) {
            return gasPriceJson;
        }

        // Return if error in getting max priority fee
        JsonObject maxPriorityGasJson = getMaxPriorityFeePerGas(ethApiUrl);
        if (!maxPriorityGasJson.get("status").getAsBoolean()) {
            return maxPriorityGasJson;
        }

        BigInteger gasPrice = gasPriceJson.get("data").getAsJsonObject().get("gas_price").getAsBigInteger();
        BigInteger maxPriorityGas = maxPriorityGasJson.get("data").getAsJsonObject().get("max_priority_fee").getAsBigInteger();

        // Calculate low estimation for gas price
        BigInteger gasPriceLow = gasPrice;

        // Get the base fees from gas price
        BigInteger baseFees = gasPrice.subtract(maxPriorityGas);

        // Calculate average estimation for gas price -> base_fee + (base_fee * 0.25) + max_priority_fee
        BigInteger maxFeeIncreasePerBlock = ((new BigDecimal(baseFees)).multiply(new BigDecimal("0.25"))).toBigInteger();
        BigInteger gasPriceAverage = baseFees.add(maxFeeIncreasePerBlock).add(maxPriorityGas);

        // Calculate high estimation for gas price -> base_fee + (base_fee * 0.5) + 2 * max_priority_fee
        BigInteger gasPriceHigh = baseFees.add(maxFeeIncreasePerBlock.multiply(new BigInteger("2"))).add(maxPriorityGas.multiply(new BigInteger("2")));

        JsonObject data = new JsonObject();
        data.addProperty("gas_price_low", gasPriceLow.toString(10));
        data.addProperty("gas_price_average", gasPriceAverage.toString(10));
        data.addProperty("gas_price_high", gasPriceHigh.toString(10));

        return ApiResponseUtils.success(data);
    }


    /**
     * Get base gas price.
     * @return API response for base fees
     * @throws IOException Rest API Exception
     */
    private static JsonObject getGasPrice(String ethApiUrl) throws IOException {
        Web3j web3 = Web3j.build(new HttpService(ethApiUrl));
        EthGasPrice ethGasPrice = web3.ethGasPrice().send();

        if (ethGasPrice.hasError()) {
            return ApiResponseUtils.error(ethGasPrice.getError().getMessage());
        } else {
            JsonObject data = new JsonObject();
            data.addProperty("gas_price", ethGasPrice.getGasPrice());
            return ApiResponseUtils.success(data);
        }
    }

    /**
     * Get max priority fee per gas.
     * @return API response for max priority fee
     * @throws IOException Rest API Exception
     */
    private static JsonObject getMaxPriorityFeePerGas(String ethApiUrl) throws IOException {
        Web3j web3 = Web3j.build(new HttpService(ethApiUrl));
        EthMaxPriorityFeePerGas ethMaxPriorityFeePerGas = web3.ethMaxPriorityFeePerGas().send();

        if (ethMaxPriorityFeePerGas.hasError()) {
            return ApiResponseUtils.error(ethMaxPriorityFeePerGas.getError().getMessage());
        } else {
            JsonObject data = new JsonObject();
            data.addProperty("max_priority_fee", ethMaxPriorityFeePerGas.getMaxPriorityFeePerGas());
            return ApiResponseUtils.success(data);
        }
    }

}
