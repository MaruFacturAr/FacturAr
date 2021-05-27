package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "documents")
public class Document implements Serializable {
    private final long serialVersionUID = 5946862030197964101L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer document_type_id;

    private String number;

    private Integer country_id;

    public Document(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDocument_type_id() {
        return document_type_id;
    }

    public void setDocument_type_id(Integer document_type_id) {
        this.document_type_id = document_type_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }
}
