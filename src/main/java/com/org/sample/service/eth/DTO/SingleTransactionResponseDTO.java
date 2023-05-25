package com.org.sample.service.eth.DTO;

import com.org.sample.service.eth.Model.TransactionEth;
import com.org.sample.service.eth.Model.TransactionEthEs;

import lombok.Data;

/**
 * DTO class for details of single transaction
 */
@Data
public class SingleTransactionResponseDTO {

    String txn_hash;
    Object transaction;

    public SingleTransactionResponseDTO(TransactionEth transactionEth) {
        this.txn_hash = transactionEth.getTransactionHash();
        this.transaction = transactionEth.getTransactionData();
    }

    public SingleTransactionResponseDTO(TransactionEthEs transactionEthEs) {
        this.txn_hash = transactionEthEs.getTxnHash();
        this.transaction = transactionEthEs.getData();
    }

}
