package com.org.sample.service.eth.Service;

import com.org.sample.service.eth.Model.AddressSyncTransactionStack;

public interface AddressSyncTransactionStackService {

    /**
     * add new address
     * 
     * @param address
     * @return
     */
    AddressSyncTransactionStack addAddress(String address);

}