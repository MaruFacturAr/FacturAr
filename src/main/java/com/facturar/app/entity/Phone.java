package com.facturar.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phones")
public class Phone implements Serializable {

	private static final long serialVersionUID = 2294776912290782800L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@JsonIgnore
	@OneToOne(mappedBy = "phone")
	private UserEntity user;

	private String phone_type_id;

	private String country_code; // Harcode +54

	private String number;

	private String extension;

	public Phone() {

	}

	public Phone(Long id, UserEntity user, String phone_type_id, String country_code, String number, String extension) {
		super();
		this.id = id;
		this.user = user;
		this.phone_type_id = phone_type_id;
		this.country_code = country_code;
		this.number = number;
		this.extension = extension;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getPhone_type_id() {
		return phone_type_id;
	}

	public void setPhone_type_id(String phone_type_id) {
		this.phone_type_id = phone_type_id;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
