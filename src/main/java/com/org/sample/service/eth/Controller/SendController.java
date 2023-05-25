package com.org.sample.service.eth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.org.sample.service.eth.DTO.GeneralResponseDTO;
import com.org.sample.service.eth.DTO.SendRequestDTO;
import com.org.sample.service.eth.Service.SendService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for send apis
 */
@RestController
public class SendController {

    @Autowired
    SendService sendService;

    @Operation(summary = "Sends raw transaction to the blockchain")
    @PostMapping(path = "/send/eth")
    public ResponseEntity<?> sendRawTransaction(@RequestBody SendRequestDTO sendRequestDTO) {
        try {
            return new ResponseEntity<Object>(
                    new Gson().fromJson(sendService.sendRawTransaction(sendRequestDTO.getTxn_raw()),
                            Object.class),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
