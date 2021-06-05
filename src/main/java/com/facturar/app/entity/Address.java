package com.facturar.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 6378353443085899431L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String street;

    private String city;

    private Integer address_type_id;

    private Integer province_id;

    private Integer country_id;

    public Address() {

    }

    public Address(Long id, String street, String city, Integer address_type_id, Integer province_id,
                   Integer country_id) {
        super();
        this.id = id;
        this.street = street;
        this.city = city;
        this.address_type_id = address_type_id;
        this.province_id = province_id;
        this.country_id = country_id;
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

    public Integer getAddress_type_id() {
        return address_type_id;
    }

    public void setAddress_type_id(Integer address_type_id) {
        this.address_type_id = address_type_id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Long getId() {
        return id;
    }


}
