package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "voucher_type")
public class VoucherType implements Serializable {
    private static final long serialVersionUID = -3557038809456128414L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Long letter_id;

    private String name;

    private String afip_code;

    public  VoucherType(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLetter_id() {
        return letter_id;
    }

    public void setLetter_id(Long letter_id) {
        this.letter_id = letter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAfip_code() {
        return afip_code;
    }

    public void setAfip_code(String afip_code) {
        this.afip_code = afip_code;
    }



}
