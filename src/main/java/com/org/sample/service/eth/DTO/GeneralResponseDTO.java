package com.org.sample.service.eth.DTO;

import java.util.HashMap;

import lombok.Data;

/**
 * DTO class for general response
 */
@Data
public class GeneralResponseDTO {

    boolean status;
    Object data;
    String error;

    public GeneralResponseDTO(Object data) {
        this.status = true;
        this.data = data;
        this.error = "";
    }

    public GeneralResponseDTO(String error) {
        this.status = false;
        this.data = new HashMap<>();
        this.error = error;
    }
}
