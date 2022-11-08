package com.rp.controlsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<WorkOrder> orders = new ArrayList<>();

    protected Client() {
    }

    public Client(String name, String phoneNumber, String email, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public List<WorkOrder> getOrders() {
        return orders;
    }

    public void addOrder(WorkOrder order) {
        this.orders.add(order);
    }
}
