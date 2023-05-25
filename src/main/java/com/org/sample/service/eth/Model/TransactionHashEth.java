package com.org.sample.service.eth.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for transaction hash
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_hash_eth")
public class TransactionHashEth {

    @Id
    @Column(name = "`Transaction Hash`")
    String transactionHash;

    @Column(name = "`From`")
    String from;

    @Column(name = "`To`")
    String to;

}
