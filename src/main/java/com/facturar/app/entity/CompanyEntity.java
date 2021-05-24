package com.facturar.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "companies")
public class CompanyEntity implements Serializable {

	private static final long serialVersionUID = -8415729848005452756L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long user_id;
	
	private Long billing_data_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date initial_date;
	
	private Long iibb_code;

	public CompanyEntity() {

	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getBilling_data_id() {
		return billing_data_id;
	}

	public void setBilling_data_id(Long billing_data_id) {
		this.billing_data_id = billing_data_id;
	}

	public Date getInitial_date() {
		return initial_date;
	}

	public void setInitial_date(Date initial_date) {
		this.initial_date = initial_date;
	}

	public Long getIibb_code() {
		return iibb_code;
	}

	public void setIibb_code(Long iibb_code) {
		this.iibb_code = iibb_code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}


}
