package com.org.sample.service.eth.Service.Implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.sample.service.eth.Model.AddressSyncTransactionStack;
import com.org.sample.service.eth.Repository.AddressSyncTransactionStackRepository;
import com.org.sample.service.eth.Service.AddressSyncTransactionStackService;

/**
 * Service class for AddressSyncTransactionStackService interface
 */
@Service
public class AddressSyncTransactionStackServiceImplementation implements AddressSyncTransactionStackService {

    @Autowired
    AddressSyncTransactionStackRepository addressSyncTransactionStackRepository;

    /**
     * add new address
     * 
     * @param address
     * @return
     */
    @Override
    public AddressSyncTransactionStack addAddress(String address) {
        List<AddressSyncTransactionStack> addressSyncTransactionStackList = addressSyncTransactionStackRepository
                .findByAddress(address);
        for (AddressSyncTransactionStack addressSyncTransactionStack : addressSyncTransactionStackList) {
            long now = (new Date().getTime() / 1000) % 60;
            long then = (addressSyncTransactionStack.getTimestamp().getTime() / 1000) % 60;
            if (now - then < 60) {
                return addressSyncTransactionStack;
            }
        }
        return addressSyncTransactionStackRepository.save(new AddressSyncTransactionStack(address));
    }

}
