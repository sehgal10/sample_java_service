package com.org.sample.service.eth.Tasks;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.org.sample.service.eth.Model.AddressSyncStack;
import com.org.sample.service.eth.Model.AddressSyncTransactionStack;
import com.org.sample.service.eth.Repository.AddressSyncStackRepository;
import com.org.sample.service.eth.Repository.AddressSyncTransactionStackRepository;
import com.org.sample.service.eth.Service.AddressEthService;
import com.org.sample.service.eth.Service.TransactionEthEsService;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

/**
 * Component class to run task periodically
 */
@Component
public class Scheduler {

    @Autowired
    AddressSyncStackRepository addressSyncStackRepository;

    @Autowired
    AddressSyncTransactionStackRepository addressSyncTransactionStackRepository;

    @Autowired
    AddressEthService addressEthService;

    @Autowired
    TransactionEthEsService transactionEthEsService;

    /**
     * Runs every 2 sec
     */
    @Scheduled(fixedDelay = 2000)
    @SchedulerLock(name = "fetchAddressAddress")
    @Transactional
    public void fetchAddressAddress() {
        Optional<AddressSyncStack> addressSyncStackOptional = addressSyncStackRepository
                .findFirstByOrderByTimestampDesc();
        if (addressSyncStackOptional.isPresent()) {
            AddressSyncStack addressSyncStack = addressSyncStackOptional.get();
            addressSyncStackRepository.delete(addressSyncStack);
            addressEthService.indexAddress(addressSyncStack.getAddress());
        }
    }

    /**
     * Runs every 20 sec
     */
    @Scheduled(fixedDelay = 20000)
    @SchedulerLock(name = "fetchTransactionAddress")
    @Transactional
    public void fetchTransactionAddress() {
        Optional<AddressSyncTransactionStack> addressSyncStackOptional = addressSyncTransactionStackRepository
                .findFirstByOrderByTimestampDesc();
        if (addressSyncStackOptional.isPresent()) {
            AddressSyncTransactionStack addressSyncTransactionStack = addressSyncStackOptional.get();
            addressSyncTransactionStackRepository.delete(addressSyncTransactionStack);
            transactionEthEsService.indexTransactionEthEs(addressSyncTransactionStack.getAddress());
        }
    }
}
