package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ITituloAcademico;
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
@Table(name="SGE_TITULOACADEMICO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TituloAcademico extends com.ine.sge.models.AuditModel<String> implements ITituloAcademico, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_TITUACA")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "TITULO")
	@Size(max=50)
	private String titulo;
	

	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
}
