package com.org.sample.service.eth.Service.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.sample.service.eth.Model.AddressSyncStack;
import com.org.sample.service.eth.Repository.AddressSyncStackRepository;
import com.org.sample.service.eth.Service.AddressSyncStackService;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class for AddressSyncStackService interface
 */
@Service
@Slf4j
public class AddressSyncStackServiceImplementation implements AddressSyncStackService {

    @Autowired
    AddressSyncStackRepository addressSyncStackRepository;

    /**
     * add new address
     * 
     * @param address
     * @return
     */
    @Override
    public AddressSyncStack addAddress(String address) {
        List<AddressSyncStack> addressSyncStackList = addressSyncStackRepository.findByAddress(address);
        for (AddressSyncStack addressSyncStack : addressSyncStackList) {
            long now = (new Date().getTime() / 1000) % 60;
            long then = (addressSyncStack.getTimestamp().getTime() / 1000) % 60;
            if (now - then < 60) {
                log.info("Address: " + address + " already exists within 60 sec of timestamp");
                return addressSyncStack;
            }
        }
        return addressSyncStackRepository.save(new AddressSyncStack(address));
    }

}
