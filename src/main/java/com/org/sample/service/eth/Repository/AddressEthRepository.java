package com.org.sample.service.eth.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.sample.service.eth.Model.AddressEth;

/**
 * Respository for address
 */
@Repository
public interface AddressEthRepository extends JpaRepository<AddressEth, String> {

    Optional<AddressEth> findByAddress(String address);

}
