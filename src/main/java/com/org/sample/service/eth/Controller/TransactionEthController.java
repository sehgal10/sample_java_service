package com.org.sample.service.eth.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.sample.service.eth.DTO.GeneralResponseDTO;
import com.org.sample.service.eth.DTO.SingleTransactionResponseDTO;
import com.org.sample.service.eth.DTO.TransactionResponseDTO;
import com.org.sample.service.eth.Service.TransactionEthEsService;
import com.org.sample.service.eth.Service.TransactionHashEthService;
import com.org.sample.service.eth.Utils.ApiError;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for transaction apis
 */
@RestController
@RequestMapping(path = "/txn")
public class TransactionEthController {

    @Autowired
    TransactionHashEthService transactionHashEthService;

    @Autowired
    TransactionEthEsService transactionEthEsService;

    @Operation(summary = "Returns the transaction data given a transaction hash")
    @GetMapping(path = "/eth")
    public ResponseEntity<GeneralResponseDTO> getTransactionByHash(@RequestParam String txn_hash) {
        try {
            SingleTransactionResponseDTO singleTransactionResponseDTO = transactionEthEsService
                    .getTransactionByHash(txn_hash);
            return new ResponseEntity<GeneralResponseDTO>(
                    new GeneralResponseDTO(singleTransactionResponseDTO), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()), e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Returns list of all the transaction hash given an eth address")
    @GetMapping(path = "/eth/all")
    public ResponseEntity<GeneralResponseDTO> getAllTransactionByAddress(@RequestParam String address) {
        try {
            TransactionResponseDTO transactionResponseDTO = transactionHashEthService
                    .getAllTransactionByAddress(address);
            return new ResponseEntity<GeneralResponseDTO>(
                    new GeneralResponseDTO(transactionResponseDTO), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()), e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Returns list of all the transaction hash where the given eth address is the sender")
    @GetMapping(path = "/eth/send")
    public ResponseEntity<GeneralResponseDTO> getAllTransactionBySenderAddress(@RequestParam String address) {
        try {
            TransactionResponseDTO transactionResponseDTO = transactionHashEthService
                    .getAllTransactionBySenderAddress(address);
            return new ResponseEntity<GeneralResponseDTO>(
                    new GeneralResponseDTO(transactionResponseDTO), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()), e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Returns list of all the transaction hash where the given eth address is the receiver")
    @GetMapping(path = "/eth/receive")
    public ResponseEntity<GeneralResponseDTO> getAllTransactionByReceiverAddress(@RequestParam String address) {
        try {
            TransactionResponseDTO transactionResponseDTO = transactionHashEthService
                    .getAllTransactionByReceiverAddress(address);
            return new ResponseEntity<GeneralResponseDTO>(
                    new GeneralResponseDTO(transactionResponseDTO), HttpStatus.OK);
        } catch (ApiError e) {
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()), e.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
