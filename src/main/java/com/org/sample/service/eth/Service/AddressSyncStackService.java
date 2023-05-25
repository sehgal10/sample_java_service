package com.org.sample.service.eth.Service;

import com.org.sample.service.eth.Model.AddressSyncStack;

public interface AddressSyncStackService {

    /**
     * add new address
     * 
     * @param address
     * @return
     */
    AddressSyncStack addAddress(String address);

}