package com.org.sample.service.eth.Core;

import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EthValidation {

    public static JsonObject validateAddress(final String address) {
        if (address == null) {
            return ApiResponseUtils.error("Address cannot be null");
        }

        if (address.length() == 0) {
            return ApiResponseUtils.error("Address cannot be blank");
        }

        Pattern pattern = Pattern.compile("^(0x)?[0-9A-Fa-f]{40}$");
        Matcher matcher = pattern.matcher(address);
        if (!matcher.matches()) {
            return ApiResponseUtils.error("Address is invalid");
        }

        return ApiResponseUtils.success(new JsonObject());
    }

    public static JsonObject validateAmount(final String amount) {
        Pattern pattern = Pattern.compile("^\\d+$");
        Matcher matcher = pattern.matcher(amount);
        if (!matcher.matches()) {
            return ApiResponseUtils.error("Amount is invalid");
        }

        return ApiResponseUtils.success(new JsonObject());
    }
}
