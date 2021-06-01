package com.facturar.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {

    private static final long serialVersionUID = 2294776912290782800L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "phone")
    private UserEntity user;

    private Integer phone_type_id;

    private String country_code; // Harcode +54

    private String number;

    private String extension;

    public Integer getCity_code() {
        return city_code;
    }

    public void setCity_code(Integer city_code) {
        this.city_code = city_code;
    }

    private Integer city_code;

    public Phone() {

    }

    public Phone(Long id, UserEntity user, Integer phone_type_id, String country_code, String number, String extension, Integer city_code) {
        this.id = id;
        this.user = user;
        this.phone_type_id = phone_type_id;
        this.country_code = country_code;
        this.number = number;
        this.extension = extension;
        this.city_code = city_code;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getPhone_type_id() {
        return phone_type_id;
    }

    public void setPhone_type_id(Integer phone_type_id) {
        this.phone_type_id = phone_type_id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
