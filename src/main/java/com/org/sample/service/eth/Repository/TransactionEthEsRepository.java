package com.org.sample.service.eth.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.org.sample.service.eth.Model.TransactionEthEs;

/**
 * Respository for TransactionEthEs
 */
@Repository
public interface TransactionEthEsRepository extends MongoRepository<TransactionEthEs, String> {

    Optional<TransactionEthEs> findByTxnHash(String txnHash);

    List<TransactionEthEs> findAllByTxnHashIn(List<String> txnHash);

}
