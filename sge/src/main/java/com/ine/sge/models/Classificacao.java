package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IClassificacao;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Indexed
@Entity
@Table(name="SGE_CLASSIFICACAO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Classificacao<INivel extends com.ine.sge.models.Nivel> extends com.ine.sge.models.AuditModel<String> implements IClassificacao<INivel>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_CLASS")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CLASSIFICACAO")
	@Size(max=5)
	private String clasificacao;

	@Field
	@Column(name = "DESIGNACAO")
	@Size(max=200)
	private String designacao;

	@Field
	@Column(name = "CLASSNAME")
	@Size(max=50)
	private String className;

	@Column(name = "ESTADO")
	private int estado = 1;


	//FUE_CLASSIFICACAO$NIVEIS
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			targetEntity=com.ine.sge.models.Nivel.class
	)
	@JoinTable(name = "SGE_CLASSIFICACAO$NIVEIS",
			joinColumns = { @JoinColumn(name = "ID_CLASS") },
			inverseJoinColumns = { @JoinColumn(name = "ID_NIVEL") })
	private Set<INivel> niveis = new HashSet<>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClasificacao() {
		return clasificacao;
	}

	public void setClasificacao(String clasificacao) {
		this.clasificacao = clasificacao;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Set <INivel> getNiveis() {
		return niveis;
	}

	public void setNiveis(Set <INivel> niveis) {
		this.niveis = niveis;
	}
}
