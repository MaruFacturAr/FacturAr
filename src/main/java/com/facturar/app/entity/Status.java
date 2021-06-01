package com.facturar.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long statud_id;

	private int activo = 1;

	private int inactivo = 0;

	public Status() {
	}

	public int getActivo() {
		return activo;
	}

	public int getInactivo() {
		return inactivo;
	}

}
