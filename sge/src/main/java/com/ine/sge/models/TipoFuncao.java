package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ITipoFuncao;
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
@Table(name="SGE_TIPOFUNCAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoFuncao extends com.ine.sge.models.AuditModel<String> implements ITipoFuncao, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_TIPFUN")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "TIPO")
	@Size(max=100)
	private String tipo;
		
	@Column(name = "ESTADO")
	private int estado = 1;

	//region accessors for public property

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTIPO() {
		return tipo;
	}

	public void setTIPO(String tIPO) {
		tipo = tIPO;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
	
}
