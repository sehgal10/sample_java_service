package com.org.sample.service.eth.Service;

import com.org.sample.service.eth.DTO.AddressBalanceResponseDTO;
import com.org.sample.service.eth.DTO.AddressNonceResponseDTO;
import com.org.sample.service.eth.Model.AddressEth;

/**
 * Interface for address service
 */
public interface AddressEthService {

    /**
     * Function to get address balance for the given address
     * @param address address of the eth
     * @return address balance response dto
     * @throws Exception
     */
    AddressBalanceResponseDTO getBalanceByAddress(String address) throws Exception;

    /**
     * Function to get address nonce for the given address
     * @param address address of the eth
     * @return address nonce response dto
     * @throws Exception
     */
    AddressNonceResponseDTO getNonceByAddress(String address) throws Exception;

    /**
     * function to index address
     * 
     * @param transaction
     * @return inserted entity
     */
    AddressEth indexAddress(String address);

}