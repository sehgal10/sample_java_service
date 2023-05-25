package com.org.sample.service.eth.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.sample.service.eth.Model.AddressSyncTransactionStack;

/**
 * Respository for AddressSyncTransactionStack
 */
@Repository
public interface AddressSyncTransactionStackRepository extends JpaRepository<AddressSyncTransactionStack, String> {

    Optional<AddressSyncTransactionStack> findFirstByOrderByTimestampDesc();

    List<AddressSyncTransactionStack> findByAddress(String address);

}
