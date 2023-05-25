package com.org.sample.service.eth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.org.sample.service.eth.DTO.FeesRequestDTO;
import com.org.sample.service.eth.DTO.GeneralResponseDTO;
import com.org.sample.service.eth.Service.FeesService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for fees apis
 */
@RestController
public class FeesController {

    @Autowired
    FeesService feesService;

    @Operation(summary = "Estimates the fees for the given transaction parameters")
    @PostMapping(path = "/fees/eth")
    public ResponseEntity<?> estimateFees(@RequestBody FeesRequestDTO feesRequestDTO) {
        try {
            return new ResponseEntity<Object>(
                    new Gson().fromJson(feesService.estimateFees(feesRequestDTO), Object.class),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
