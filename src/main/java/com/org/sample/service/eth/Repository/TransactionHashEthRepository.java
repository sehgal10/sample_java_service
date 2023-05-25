package com.org.sample.service.eth.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.sample.service.eth.Model.TransactionHashEth;

/**
 * Respository for transaction hash
 */
@Repository
public interface TransactionHashEthRepository extends JpaRepository<TransactionHashEth, String> {

    List<TransactionHashEth> findByFromOrTo(String from, String to);

    List<TransactionHashEth> findByFrom(String address);

    List<TransactionHashEth> findByTo(String address);

    @Query(value = "select transactionHash from TransactionHashEth as the where the.from = (:address) or the.to = (:address)")
    List<String> findAllByAddress(@Param("address") String address);

}
