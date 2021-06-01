package com.facturar.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class CompanyEntity implements Serializable {

    private static final long serialVersionUID = -8415729848005452756L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_data_id", referencedColumnName = "id")
    private BillingData billingData;

    @Temporal(TemporalType.TIMESTAMP)
    private Date initial_date;

    private String iibb_code;


    public CompanyEntity() {

    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(Date initial_date) {
        this.initial_date = initial_date;
    }

    public String getIibb_code() {
        return iibb_code;
    }

    public void setIibb_code(String iibb_code) {
        this.iibb_code = iibb_code;
    }

    public BillingData getBillingData() {
        return billingData;
    }

    public void setBillingData(BillingData billingData) {
        this.billingData = billingData;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }


}