package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IContabilidade;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Indexed
@Entity
@Table(name="SGE_CONTABILIDADE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contabilidade extends com.ine.sge.models.AuditModel<String> implements IContabilidade, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_CONTB")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CNTA_DSG")
	@Size(max=50)
	private String cntaDsg;

	@Field
	@Column(name = "CNTA")
	@Size(max=4)
	private String cnta;
	
	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getCntaDsg() {
		return cntaDsg;
	}

	public void setCntaDsg(String cntaDsg) {
		this.cntaDsg = cntaDsg;
	}

	public String getCnta() {
		return cnta;
	}

	public void setCnta(String cnta) {
		this.cnta = cnta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion
}
