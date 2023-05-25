package com.org.sample.service.eth.DTO;

import lombok.Data;

/**
 * DTO class for fees request body
 */
@Data
public class FeesRequestDTO {

    String from;
    String to;
    String value;
    String data;

}
