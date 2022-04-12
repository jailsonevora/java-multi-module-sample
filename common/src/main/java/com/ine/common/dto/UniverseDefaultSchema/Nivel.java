package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.interfaces.dto.INivel;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Nivel extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements INivel, Serializable{

	@JsonProperty("divisao")
	private String divisao;

	@JsonProperty("grupo")
	private String grupo;

	@JsonProperty("classe")
	private String classe;

	@JsonProperty("subClasse")
	private String subClasse;

	@JsonProperty("categoria")
	private String categoria;

	@JsonProperty("subCategoria")
	private String subCategoria;

	@JsonProperty("posicao")
	private String posicao;

	@JsonProperty("estado")
	private int estado = 1;

	/*//FUE_CLASSIFICACAO$NIVEIS
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "niveis")
	private Set<Classificacao> classificacoes = new HashSet<>();*/

	//region accessors for public property

	/*public Set <Classificacao> getClassificacoes() {
		return classificacoes;
	}

	public void setClassificacoes(Set <Classificacao> classificacoes) {
		this.classificacoes = classificacoes;
	}*/

	public String getDivisao() {
		return divisao;
	}

	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getSubClasse() {
		return subClasse;
	}

	public void setSubClasse(String subClasse) {
		this.subClasse = subClasse;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubCategoria() {
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
}
