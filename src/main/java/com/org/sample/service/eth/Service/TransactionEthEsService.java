package com.org.sample.service.eth.Service;

import java.util.List;

import com.google.gson.JsonObject;
import com.org.sample.service.eth.DTO.SingleTransactionResponseDTO;
import com.org.sample.service.eth.Model.TransactionEthEs;

public interface TransactionEthEsService {

    /**
     * Returns a single transaction details for given hash
     * 
     * @param txn_hash transaction hash
     * @return single transaction details for given hash
     * @throws Exception
     */
    SingleTransactionResponseDTO getTransactionByHash(String txn_hash) throws Exception;

    /**
     * function to index TransactionEthEs
     * 
     * @param transaction
     * @return inserted entity
     */
    JsonObject indexTransactionEthEs(String address);

    /**
     * function to get all TransactionEthEs by address if available
     * 
     * @param address
     * @return inserted entity
     */
    List<TransactionEthEs> getAllTransactionEthEs(String address) throws Exception;

}