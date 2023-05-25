package com.org.sample.service.eth.Model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for AddressSyncStack
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_sync_stack")
public class AddressSyncStack {

    @Id
    String id;

    @Column(name = "address")
    String address;

    @Column(name = "timestamp")
    Timestamp timestamp;

    public AddressSyncStack(String address) {
        this.id = UUID.randomUUID().toString();
        this.address = address;
        this.timestamp = new Timestamp(new Date().getTime());
    }

}
