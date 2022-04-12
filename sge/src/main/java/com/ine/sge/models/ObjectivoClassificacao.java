package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IObjectivoClassificacao;
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
@Table(name="SGE_OBJECTIVOCLASSIFICACAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ObjectivoClassificacao extends com.ine.sge.models.AuditModel<String> implements IObjectivoClassificacao, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_OBJCL")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CODIGO")
	@Size(max=75)
	private String codigo;

	@Field
	@Column(name = "DESIGNACAO")
	@Size(max=150)
	private String designacao;

	@Column(name = "ESTADO")
	private int estado = 1;

	//region accessors for public property

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDesignacao() {
		return designacao;
	}


	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion
}
