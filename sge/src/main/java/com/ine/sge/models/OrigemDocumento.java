package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IOrigemDocumento;
import com.ine.common.interfaces.dto.IOrigemDocumento_ISP_Universe;
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
@Table(name="SGE_ORIGEM_DOC")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrigemDocumento extends com.ine.sge.models.AuditModel<String> implements IOrigemDocumento, IOrigemDocumento_ISP_Universe, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_ORGDOC")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "ORIGEM")
	@Size(max=2)
	private String origem;

	@Field
	@Column(name = "ORIGEM_DSG")
	@Size(max=50)
	private String origemDsg;
	

	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getOrigemDoc() {
		return origem;
	}


	public void setOrigemDoc(String origem) {
		this.origem = origem;
	}


	public String getOrigemDsg() {
		return origemDsg;
	}


	public void setOrigemDsg(String origemDsg) {
		this.origemDsg = origemDsg;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
}
