package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "counterfoils")
public class CounterfoilEntity implements Serializable {
    private static final long serialVersionUID = 4892010090150670800L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String code;

    private String name;

    private Long voucher_type_id;

    private Long from_number;

    private Long to_number;

    private Long next_number;

    @Column(name = "user_id")
    private Long userId;

    private int status_id;

    private Long company_id;

    public CounterfoilEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVoucher_type_id() {
        return voucher_type_id;
    }

    public void setVoucher_type_id(Long voucher_type_id) {
        this.voucher_type_id = voucher_type_id;
    }

    public Long getFrom_number() {
        return from_number;
    }

    public void setFrom_number(Long from_number) {
        this.from_number = from_number;
    }

    public Long getTo_number() {
        return to_number;
    }

    public void setTo_number(Long to_number) {
        this.to_number = to_number;
    }

    public Long getNext_number() {
        return next_number;
    }

    public void setNext_number(Long next_number) {
        this.next_number = next_number;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
