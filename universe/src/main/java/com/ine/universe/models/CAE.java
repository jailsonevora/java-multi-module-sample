package com.ine.universe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ICAE;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="FUE_CAE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CAE extends com.ine.universe.models.AuditModel<String> implements ICAE, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_CAE")
	@JsonIgnore
	private long id;

	@Field
	@Column(name = "CAE")
	@Size(max=5)
	private String cae;

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

	//FUE_ESTABELECIMENTO$CAE_SEC
	/*@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "cae")
	@JsonIgnore
	private Set<Estabelecimento> estabelecimento = new HashSet<>();*/

	//FUE_ENTIDADE$CAE_SEC
	/*@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					//CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "cae")
	@JsonIgnore
	private Set<Entidade> entidade = new HashSet<>();*/

	//region accessors for public property

	/*public Set <Estabelecimento> getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Set <Estabelecimento> estabelecimento) {
		this.estabelecimento = estabelecimento;
	}*/

	/*public Set <Entidade> getEntidade() {
		return entidade;
	}

	public void setEntidade(Set <Entidade> entidade) {
		this.entidade = entidade;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCae() {
		return cae;
	}

	public void setCae(String cae) {
		this.cae = cae;
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

	//endregion
}
