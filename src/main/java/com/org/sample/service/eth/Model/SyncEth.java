package com.org.sample.service.eth.Model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for sync
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "sync_eth")
public class SyncEth {

    @Id
    String id;

    @Field(value = "Last synced block number")
    String lastSyncedBlockNumber;

    @Field(value = "Latest block number")
    String latestBlockNumber;

    @Field("Latest rate date")
    String latestRateDate;

}
