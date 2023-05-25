package com.org.sample.service.eth.Service;

import com.org.sample.service.eth.DTO.SingleTransactionResponseDTO;
import com.org.sample.service.eth.DTO.TransactionResponseDTO;

/**
 * Interface for transaction service
 */
public interface TransactionHashEthService {

    /**
     * Returns a single transaction details for given hash
     * 
     * @param txn_hash transaction hash
     * @return single transaction details for given hash
     * @throws Exception
     */
    SingleTransactionResponseDTO getTransactionByHash(String txn_hash) throws Exception;

    /**
     * Returns a list of all the transactions for the given address
     * 
     * @param address address of eth
     * @return list of all the transactions for the given address
     * @throws Exception
     */
    TransactionResponseDTO getAllTransactionByAddress(String address) throws Exception;

    /**
     * Returns a list of all the transactions for a given sender address
     * 
     * @param address address of sender
     * @return list of all the transactions for the given sender address
     * @throws Exception
     */
    TransactionResponseDTO getAllTransactionBySenderAddress(String address) throws Exception;

    /**
     * Returns a list of transaction hash for a given receiver address
     * 
     * @param address address of receiver
     * @return list of all the transactions for the given receiver address
     * @throws Exception
     */
    TransactionResponseDTO getAllTransactionByReceiverAddress(String address) throws Exception;

}