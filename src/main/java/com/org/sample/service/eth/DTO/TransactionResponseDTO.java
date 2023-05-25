package com.org.sample.service.eth.DTO;

import java.util.ArrayList;
import java.util.List;

import com.org.sample.service.eth.Model.TransactionHashEth;

import lombok.Data;

/**
 * DTO class for transaction hash response
 */
@Data
public class TransactionResponseDTO {

    String address;
    List<String> transactions;

    public TransactionResponseDTO(String address, List<TransactionHashEth> transactionEthList) {
        this.address = address;
        this.transactions = new ArrayList<>();
        transactionEthList.forEach(transactionEth -> transactions.add(transactionEth.getTransactionHash()));
    }

}
