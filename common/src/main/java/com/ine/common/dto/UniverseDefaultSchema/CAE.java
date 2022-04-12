package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.interfaces.dto.ICAE;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CAE extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements ICAE, Serializable{


	@JsonProperty("cae")
	private String cae;

	@JsonProperty("designacao")
	private String designacao;

	@JsonProperty("className")
	private String className;

	@JsonProperty("estado")
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
