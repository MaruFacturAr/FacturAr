package com.facturar.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomersEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billig_data_id", referencedColumnName = "id") //Aca pongo "billig" porque en la tabla esta asi.
	private BillingData billing_data_id;
	
	public CustomersEntity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BillingData getBilling_data_id() {
		return billing_data_id;
	}

	public void setBilling_data_id(BillingData billing_data_id) {
		this.billing_data_id = billing_data_id;
	}
	

}
