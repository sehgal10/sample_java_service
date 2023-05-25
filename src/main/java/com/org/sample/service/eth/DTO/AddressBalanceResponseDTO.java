package com.org.sample.service.eth.DTO;

import com.org.sample.service.eth.Model.AddressEth;

import lombok.Data;

/**
 * DTO class for address balance response
 */
@Data
public class AddressBalanceResponseDTO {

    String address;
    String balance;

    public AddressBalanceResponseDTO(AddressEth addressEth) {
        this.address = addressEth.getAddress();
        this.balance = addressEth.getBalance();
    }
}
