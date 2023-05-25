package com.org.sample.service.eth.Service;

import com.google.gson.JsonObject;
import com.org.sample.service.eth.DTO.FeesRequestDTO;

/**
 * Interface for fees service
 */
public interface FeesService {

    /**
     * Estimates fees for a given transaction parameters
     * 
     * @param feesRequestDTO
     * @return JsonObject containing gas price information
     * @throws Exception
     */
    JsonObject estimateFees(FeesRequestDTO feesRequestDTO) throws Exception;

}