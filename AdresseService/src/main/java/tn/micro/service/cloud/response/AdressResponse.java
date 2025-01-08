package tn.micro.service.cloud.response;

import tn.micro.service.cloud.entity.Address;

public class AdressResponse {
    private long id;
    private String street;
    private String city;

    // Constructor to map Address entity
    public AdressResponse(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
