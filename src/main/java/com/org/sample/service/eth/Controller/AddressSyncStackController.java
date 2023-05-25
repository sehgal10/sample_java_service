package com.org.sample.service.eth.Controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.org.sample.service.eth.DTO.AddressRequestDTO;
import com.org.sample.service.eth.DTO.GeneralResponseDTO;
import com.org.sample.service.eth.Model.AddressSyncStack;
import com.org.sample.service.eth.Service.AddressSyncStackService;
import com.org.sample.service.eth.Service.AddressSyncTransactionStackService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * Controller class for address/sync apis
 */
@RestController
public class AddressSyncStackController {

    @Autowired
    AddressSyncStackService addressSyncStackService;

    @Autowired
    AddressSyncTransactionStackService addressSyncTransactionStackService;

    @Operation(summary = "Add new address to address and transaction stack")
    @PostMapping(path = "/address/sync")
    @Transactional
    public ResponseEntity<?> addAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        try {
            JsonObject jsonObject = new JsonObject();
            AddressSyncStack addressSyncStack = addressSyncStackService.addAddress(addressRequestDTO.getAddress());
            addressSyncTransactionStackService.addAddress(addressRequestDTO.getAddress());
            jsonObject.addProperty("address", addressSyncStack.getAddress());
            jsonObject.addProperty("timestamp", addressSyncStack.getTimestamp().toString());
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(new Gson().fromJson(jsonObject, Object.class)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<GeneralResponseDTO>(new GeneralResponseDTO(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
