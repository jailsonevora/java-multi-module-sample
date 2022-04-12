package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.dto.UniverseDefaultSchema.Nivel;
import com.ine.common.interfaces.dto.IClassificacao;
import com.ine.common.interfaces.dto.INivel;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Classificacao<INivel> extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements IClassificacao<INivel>, Serializable{

	@JsonProperty("clasificacao")
	private String clasificacao;

	@JsonProperty("designacao")
	private String designacao;

	@JsonProperty("className")
	private String className;

	@JsonProperty("estado")
	private int estado = 1;

	//FUE_CLASSIFICACAO$NIVEIS
	//ManyToMany
	private Set<INivel> niveis = new HashSet<>();

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
