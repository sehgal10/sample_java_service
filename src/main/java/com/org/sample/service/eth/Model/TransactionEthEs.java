package com.org.sample.service.eth.Model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for block
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "transaction_eth_es")
public class TransactionEthEs {

    @Id
    String id;

    @Indexed
    @Field(value = "txn_hash")
    String txnHash;

    @Field(value = "data")
    Object data;

    public void updateData(JsonObject data) {
        this.data = new Gson().fromJson(data, Object.class);
    }

    public TransactionEthEs(JsonObject data) {
        this.txnHash = data.get("hash").getAsString();
        this.data = new Gson().fromJson(data, Object.class);
    }

}
