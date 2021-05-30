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
@Table(name = "items")
public class ItemEntity implements Serializable {
	
	private static final long serialVersionUID = 602353108840557528L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long item_type_id;
	
	private String name;
	
	private String code;
	
	private String alernative_code;
	
	private String description;
	
	private Long currency_id;
	
	private Double price;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified_date;
	
	private int status_id;
	
	private Long company_id;

	public ItemEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItem_type_id() {
		return item_type_id;
	}

	public void setItem_type_id(Long item_type_id) {
		this.item_type_id = item_type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAlernative_code() {
		return alernative_code;
	}

	public void setAlernative_code(String alernative_code) {
		this.alernative_code = alernative_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(Long currency_id) {
		this.currency_id = currency_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
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
	
	
	
	
	
	
	
	
	


}
