package com.facturar.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sales_point")
public class SalesPointEntity implements Serializable {

    private static final long serialVersionUID = 289668768359577529L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer afip_code;

    private  String name;

    private int status_id;

    private Long company_id;

    @Column(name = "user_id")
    private Long userId;

    public SalesPointEntity(){

    }

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
