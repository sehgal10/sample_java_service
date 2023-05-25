package com.org.sample.service.eth.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.org.sample.service.eth.Core.EthTransaction;
import com.org.sample.service.eth.DTO.SingleTransactionResponseDTO;
import com.org.sample.service.eth.Model.TransactionEthEs;
import com.org.sample.service.eth.Model.TransactionHashEth;
import com.org.sample.service.eth.Repository.TransactionEthEsRepository;
import com.org.sample.service.eth.Repository.TransactionHashEthRepository;
import com.org.sample.service.eth.Service.TransactionEthEsService;
import com.org.sample.service.eth.Utils.ApiError;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class for TransactionEthEsService interface
 */
@Service
@Slf4j
public class TransactionEthEsServiceImplementation implements TransactionEthEsService {

    @Value(value = "${eth.transaction.api.url}")
    private String ethTransactionApiUrl;

    @Value(value = "${eth.transaction.api.key}")
    private String ethTransactionApiKey;

    @Autowired
    TransactionEthEsRepository transactionEthEsRepository;

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
        Optional<TransactionEthEs> optionalTransactionEth = transactionEthEsRepository.findByTxnHash(txn_hash);
        if (optionalTransactionEth.isPresent()) {
            return new SingleTransactionResponseDTO(optionalTransactionEth.get());
        }
        throw new ApiError(HttpStatus.BAD_REQUEST, "Invalid transaction hash");
    }

    /**
     * function to index TransactionEthEs
     * 
     * @param transaction
     * @return inserted entity
     */
    @Override
    public JsonObject indexTransactionEthEs(String address) {
        try {
            JsonObject data = EthTransaction.getTransactions(address, ethTransactionApiUrl, ethTransactionApiKey);
            if (data.get("status").getAsBoolean()) {
                JsonArray transactionList = data.get("data").getAsJsonArray();
                transactionList.forEach(transaction -> {
                    log.info("Syncing transaction with hash: "
                            + transaction.getAsJsonObject().get("hash").getAsString());
                    Optional<TransactionHashEth> transactionHashEthOptional = transactionHashEthRepository
                            .findById(transaction.getAsJsonObject().get("hash").getAsString());
                    if (transactionHashEthOptional.isPresent()) {
                        TransactionHashEth transactionHashEth = transactionHashEthOptional.get();
                        transactionHashEth.setFrom(transaction.getAsJsonObject().get("from").getAsString());
                        transactionHashEth.setTo(transaction.getAsJsonObject().get("to").getAsString());
                        transactionHashEthRepository.save(transactionHashEth);
                    } else {
                        TransactionHashEth transactionHashEth = new TransactionHashEth(
                                transaction.getAsJsonObject().get("hash").getAsString(),
                                transaction.getAsJsonObject().get("from").getAsString().replaceFirst("0x", ""),
                                transaction.getAsJsonObject().get("to").getAsString().replaceFirst("0x", ""));
                        transactionHashEthRepository.save(transactionHashEth);
                    }
                    Optional<TransactionEthEs> savedTransactionEthEs = transactionEthEsRepository
                            .findByTxnHash(transaction.getAsJsonObject().get("hash").getAsString());
                    if (savedTransactionEthEs.isPresent()) {
                        TransactionEthEs transactionEthEs = savedTransactionEthEs.get();
                        transactionEthEs.updateData(transaction.getAsJsonObject());
                        transactionEthEsRepository.save(transactionEthEs);
                    } else {
                        TransactionEthEs transactionEthEs = new TransactionEthEs(transaction.getAsJsonObject());
                        transactionEthEsRepository.save(transactionEthEs);
                    }
                    log.info("Transaction with hash: " + transaction.getAsJsonObject().get("hash").getAsString()
                            + " synced successfully");
                });
                return data;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * function to get all TransactionEthEs
     * 
     * @param transaction
     * @return inserted entity
     */
    @Override
    public List<TransactionEthEs> getAllTransactionEthEs(String address) throws Exception {
        if (!address.equals("")) {
            List<String> transactionHash = transactionHashEthRepository.findAllByAddress(address);
            return transactionEthEsRepository.findAllByTxnHashIn(transactionHash);
        }
        return transactionEthEsRepository.findAll();
    }
}
