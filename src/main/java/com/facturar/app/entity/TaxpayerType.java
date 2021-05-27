package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "taxpayer_type")
public class TaxpayerType implements Serializable {

    private static final long serialVersionUID = 3047502232908090918L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer afip_code;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAfip_code() {
        return afip_code;
    }

    public void setAfip_code(Integer afip_code) {
        this.afip_code = afip_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
