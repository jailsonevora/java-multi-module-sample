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
import com.ine.common.interfaces.dto.IPais;
import com.ine.common.interfaces.dto.IProvincia;
import com.ine.sge.models.Pais;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Indexed
@Entity
@Table(name = "SGE_PROVINCIA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provincia<IPais extends com.ine.sge.models.Pais> extends com.ine.sge.models.AuditModel<String> implements IProvincia<IPais>, Serializable {
	
	private static long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_PROVIN")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CODIGO")
	@Size(max = 2)
	private String codigo;

	@Field
	@Column(name = "DESIGNACAO")
	@Size(max = 50)
	private String designacao;
	
	
	@Column(name = "ESTADO")
	private int estado = 1;

	@Field
	@Column(name = "SIGLA")
	@Size(max = 2)
	private String sigla;


	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=com.ine.sge.models.Pais.class)
    @JoinColumn(name = "PAIS", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private IPais pais;

	//region accessors for public property
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	public IPais getPais() {
		return pais;
	}


	public void setPais(IPais pais) {
		this.pais = pais;
	}
	//endregion
}
