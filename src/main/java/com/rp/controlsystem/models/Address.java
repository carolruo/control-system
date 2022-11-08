package com.rp.controlsystem.models;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private Integer number;
    private String city;
    private String state;
    private String postalCode;
    @OneToOne(cascade = CascadeType.ALL)
    private Client client;

    protected Address() {
    }

    public Address(String street, Integer number, String city, String state, String postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
