package com.org.sample.service.eth.Model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for transaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "transactions_eth")
public class TransactionEth {

    @Id
    @Field(value = "Transaction hash")
    String transactionHash;

    @Field(value = "Transaction Data")
    Object transactionData;

}
