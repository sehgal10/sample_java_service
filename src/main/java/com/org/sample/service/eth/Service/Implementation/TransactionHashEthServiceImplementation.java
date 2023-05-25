package com.org.sample.service.eth.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.org.sample.service.eth.DTO.SingleTransactionResponseDTO;
import com.org.sample.service.eth.DTO.TransactionResponseDTO;
import com.org.sample.service.eth.Model.TransactionEth;
import com.org.sample.service.eth.Model.TransactionHashEth;
import com.org.sample.service.eth.Repository.TransactionEthRepository;
import com.org.sample.service.eth.Repository.TransactionHashEthRepository;
import com.org.sample.service.eth.Service.TransactionHashEthService;
import com.org.sample.service.eth.Utils.ApiError;

/**
 * Service class for TransactionEthService interface
 */
@Service
public class TransactionHashEthServiceImplementation implements TransactionHashEthService {

    @Autowired
    TransactionEthRepository transactionEthRepository;

    @Autowired
    TransactionHashEthRepository transactionHashEthRepository;

    /**
     * Returns a single transaction details for given hash
     * 
     * @param txn_hash transaction hash
     * @return single transaction details for given hash
     * @throws Exception
     */
    @Override
    public SingleTransactionResponseDTO getTransactionByHash(String txn_hash) throws Exception {
        Optional<TransactionEth> optionalTransactionEth = transactionEthRepository.findByTransactionHash(txn_hash);
        if (optionalTransactionEth.isPresent()) {
            return new SingleTransactionResponseDTO(optionalTransactionEth.get());
        }
        throw new ApiError(HttpStatus.BAD_REQUEST, "Invalid transaction hash");
    }

    /**
     * Returns a list of all the transactions for the given address
     * 
     * @param address address of eth
     * @return list of all the transactions for the given address
     * @throws Exception
     */
    @Override
    public TransactionResponseDTO getAllTransactionByAddress(String address) throws Exception {
        List<TransactionHashEth> transactionEthList = transactionHashEthRepository.findByFromOrTo(address, address);
        if (!transactionEthList.isEmpty()) {
            return new TransactionResponseDTO(address, transactionEthList);
        }
        throw new ApiError(HttpStatus.BAD_REQUEST, "Invalid address");
    }

    /**
     * Returns a list of all the transactions for a given sender address
     * 
     * @param address address of sender
     * @return list of all the transactions for the given sender address
     * @throws Exception
     */
    @Override
    public TransactionResponseDTO getAllTransactionBySenderAddress(String address) throws Exception {
        List<TransactionHashEth> transactionEthList = transactionHashEthRepository.findByFrom(address);
        if (!transactionEthList.isEmpty()) {
            return new TransactionResponseDTO(address, transactionEthList);
        }
        throw new ApiError(HttpStatus.BAD_REQUEST, "Invalid address");
    }

    /**
     * Returns a list of transaction hash for a given receiver address
     * 
     * @param address address of receiver
     * @return list of all the transactions for the given receiver address
     * @throws Exception
     */
    @Override
    public TransactionResponseDTO getAllTransactionByReceiverAddress(String address) throws Exception {
        List<TransactionHashEth> transactionEthList = transactionHashEthRepository.findByTo(address);
        if (!transactionEthList.isEmpty()) {
            return new TransactionResponseDTO(address, transactionEthList);
        }
        throw new ApiError(HttpStatus.BAD_REQUEST, "Invalid address");
    }

}
