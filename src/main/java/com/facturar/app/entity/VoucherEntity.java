package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "vouchers")
public class VoucherEntity implements Serializable {

    private static final long serialVersionUID = -1051345196392317111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long sales_point_id;

    private Long counterfoil_id;

    private int status_id;

    private Long company_id;

    @Column(name = "user_id")
    private Long userId;

    public VoucherEntity(){

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

    public Long getSales_point_id() {
        return sales_point_id;
    }

    public void setSales_point_id(Long sales_point_id) {
        this.sales_point_id = sales_point_id;
    }

    public Long getCounterfoil_id() {
        return counterfoil_id;
    }

    public void setCounterfoil_id(Long counterfoil_id) {
        this.counterfoil_id = counterfoil_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
