package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
public class Country implements Serializable {
    private static final long serialVersionUID = 7558811479469461154L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Integer code;

    private String domination;

    public Country(){

    }

    public Country(Long id, Integer code, String domination, String name) {
        this.id = id;
        this.code = code;
        this.domination = domination;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDomination() {
        return domination;
    }

    public void setDomination(String domination) {
        this.domination = domination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private  String name;

}
