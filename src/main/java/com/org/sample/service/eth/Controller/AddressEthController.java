package com.org.sample.service.eth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.sample.service.eth.DTO.AddressBalanceResponseDTO;
import com.org.sample.service.eth.DTO.AddressNonceResponseDTO;
import com.org.sample.service.eth.DTO.GeneralResponseDTO;
import com.org.sample.service.eth.Service.AddressEthService;
import com.org.sample.service.eth.Utils.ApiError;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for address apis
 */
@RestController
@RequestMapping(path = "/address")
public class AddressEthController {

    @Autowired
    AddressEthService addressEthService;

    @Operation(summary = "Returns balance given an eth address")
    @GetMapping(path = "/balance/eth")
    public ResponseEntity<GeneralResponseDTO> getBalanceByAddress(@RequestParam String address) {
        try {
            AddressBalanceResponseDTO addressBalanceResponseDTO = addressEthService
                    .getBalanceByAddress(address);
            return new ResponseEntity<GeneralResponseDTO>(
                    new GeneralResponseDTO(addressBalanceResponseDTO), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()), e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Returns nonce given an eth address")
    @GetMapping(path = "/nonce/eth")
    public ResponseEntity<GeneralResponseDTO> getNonceByAddress(@RequestParam String address) {
        try {
            AddressNonceResponseDTO addressNonceResponseDTO = addressEthService
                    .getNonceByAddress(address);
            return new ResponseEntity<GeneralResponseDTO>(
                    new GeneralResponseDTO(addressNonceResponseDTO), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()), e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
