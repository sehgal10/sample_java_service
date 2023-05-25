package com.org.sample.service.eth.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for address
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresss_eth")
public class AddressEth {

    @Id
    @Column(name = "Address")
    String address;

    @Column(name = "Balance")
    String balance;

    @Column(name = "Nonce")
    String nonce;
    
    public AddressEth(String address, JsonObject data) {
        this.address = address;
        this.balance = data.get("balance").getAsString();
        this.nonce = data.get("nonce").getAsString();
    }

    public void updateAddress(JsonObject data) {
        this.balance = data.get("balance").getAsString();
        this.nonce = data.get("nonce").getAsString();
    }
}
