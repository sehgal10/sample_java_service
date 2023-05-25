package com.org.sample.service.eth.Model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for block
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "blocks_eth")
public class BlockEth {

    @Id
    @Field(value = "Block number")
    String blockNumber;

    @Field(value = "Block hash")
    String blockHash;

    @Field(value = "Block Data")
    Object blockData;

}
