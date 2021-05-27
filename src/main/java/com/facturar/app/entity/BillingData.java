package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "billing_data")
public class BillingData implements Serializable {

    private static final long serialVersionUID = 6773551430833834956L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String legal_name;

    private String fantasy_name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Document document;

    private Integer taxpayer_type_id;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    private Phone phone;

    public BillingData(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }

    public String getFantasy_name() {
        return fantasy_name;
    }

    public void setFantasy_name(String fantasy_name) {
        this.fantasy_name = fantasy_name;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Integer getTaxpayer_type_id() {
        return taxpayer_type_id;
    }

    public void setTaxpayer_type_id(Integer taxpayer_type_id) {
        this.taxpayer_type_id = taxpayer_type_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
