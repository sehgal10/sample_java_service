package com.org.sample.service.eth.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.sample.service.eth.Model.TransactionEth;

/**
 * Respository for transaction
 */
@Repository
public interface TransactionEthRepository extends MongoRepository<TransactionEth, String> {

    Optional<TransactionEth> findByTransactionHash(String transactionHash);

}
