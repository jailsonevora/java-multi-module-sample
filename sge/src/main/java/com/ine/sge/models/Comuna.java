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
import com.ine.common.interfaces.dto.IComuna;
import com.ine.common.interfaces.dto.IMunicipio;
import com.ine.sge.models.Municipio;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
@Table(name="SGE_COMUNA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comuna<IMunicipio extends com.ine.sge.models.Municipio> extends com.ine.sge.models.AuditModel<String> implements IComuna<IMunicipio>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_COMUNA")
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
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=com.ine.sge.models.Municipio.class)
    @JoinColumn(name = "MUNICIPIO", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    //@JsonIgnore
    private IMunicipio municipio;

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

	public IMunicipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(IMunicipio municipio) {
		this.municipio = municipio;
	}

	//endregion
}
