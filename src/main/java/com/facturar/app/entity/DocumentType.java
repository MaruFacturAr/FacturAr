package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "document_type")
public class DocumentType implements Serializable {

    private static final long serialVersionUID = -7843099984315585086L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer afip_code;

    public DocumentType(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAfip_code() {
        return afip_code;
    }

    public void setAfip_code(Integer afip_code) {
        this.afip_code = afip_code;
    }

}
