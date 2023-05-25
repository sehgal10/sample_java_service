package com.org.sample.service.eth.DTO;

import com.org.sample.service.eth.Model.AddressEth;

import lombok.Data;

/**
 * DTO class for address nonce response
 */
@Data
public class AddressNonceResponseDTO {

    String address;
    String nonce;

    public AddressNonceResponseDTO(AddressEth addressEth) {
        this.address = addressEth.getAddress();
        this.nonce = addressEth.getNonce();
    }
}
