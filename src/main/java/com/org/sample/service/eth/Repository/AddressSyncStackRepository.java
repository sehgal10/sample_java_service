package com.org.sample.service.eth.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.sample.service.eth.Model.AddressSyncStack;

/**
 * Respository for AddressSyncStack
 */
@Repository
public interface AddressSyncStackRepository extends JpaRepository<AddressSyncStack, String> {

    Optional<AddressSyncStack> findFirstByOrderByTimestampDesc();

    List<AddressSyncStack> findByAddress(String address);


}
