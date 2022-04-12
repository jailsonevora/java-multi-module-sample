package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.interfaces.dto.IEmpresaParticipada;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmpresaParticipada extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements IEmpresaParticipada, Serializable{

	/*@JsonProperty("id")
	private long id;*/

	@JsonProperty("contasConsolidada")
	private String contasConsolidada;

	@JsonProperty("dataIntegracao")
	private java.util.Date dataIntegracao;

	@JsonProperty("percParticipacao")
	private float percParticipacao;

	@JsonProperty("comentario")
	private String comentario;

	@JsonProperty("className")
	private String className;

	@JsonProperty("estado")
	private int estado = 1;

	//region accessors for public property

	public String getContasConsolidada() {
		return contasConsolidada;
	}

	public void setContasConsolidada(String contasConsolidada) {
		this.contasConsolidada = contasConsolidada;
	}

	public Date getDataIntegracao() {
		return dataIntegracao;
	}

	public void setDataIntegracao(Date dataIntegracao) {
		this.dataIntegracao = dataIntegracao;
	}

	public float getPercParticipacao() {
		return percParticipacao;
	}

	public void setPercParticipacao(float percParticipacao) {
		this.percParticipacao = percParticipacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
