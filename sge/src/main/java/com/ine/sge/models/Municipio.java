package com.ine.sge.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IMunicipio;
import com.ine.common.interfaces.dto.IProvincia;
import com.ine.sge.models.Provincia;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
@Table(name="SGE_MUNICIPIO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Municipio<IProvincia extends com.ine.sge.models.Provincia> extends com.ine.sge.models.AuditModel<String> implements IMunicipio<IProvincia>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_MUNICI")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CODIGO")
	@Size(max=4)
	private String codigo;

	@Field
	@Column(name = "DESIGNACAO")
	@Size(max=50)
	private String designacao;
	
	@Column(name = "ESTADO")
	private int estado = 1;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=com.ine.sge.models.Provincia.class)
    @JoinColumn(name = "PROVINCIA", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
	private IProvincia provincia;

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

	public IProvincia getProvincia() {
		return provincia;
	}

	public void setProvincia(IProvincia provincia) {
		this.provincia = provincia;
	}
	//endregion
}
