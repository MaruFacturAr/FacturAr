package com.facturar.app.entity;

import java.io.Serializable;

import javax.persistence.*;

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

	@Column(name = "user_id")
	private Long userId;

	private int status_id;

	private Long company_id;


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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
