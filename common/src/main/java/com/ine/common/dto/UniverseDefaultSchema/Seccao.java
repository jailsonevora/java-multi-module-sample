package com.ine.common.dto.UniverseDefaultSchema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ine.common.interfaces.dto.*;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seccao extends com.ine.common.dto.UniverseDefaultSchema.AuditModel<String> implements ISeccao, IComuna_ISP_Universe, IMunicipio_ISP_Universe, IProvincia_ISP_Universe, Serializable{

	@JsonProperty("seccao")
	private float seccao;

	@JsonProperty("area")
	private long area;

	@JsonProperty("aldeia")
	private long aldeia;

	@JsonProperty("comuna")
	private String comuna;

	@JsonProperty("municipio")
	private String municipio;

	@JsonProperty("provincia")
	private String provincia;

	@JsonProperty("estado")
	private int estado = 1;

	//region accessors for public property


	public float getSeccao() {
		return seccao;
	}


	public void setSeccao(float seccao) {
		this.seccao = seccao;
	}


	public long getArea() {
		return area;
	}


	public void setArea(long area) {
		this.area = area;
	}


	public long getAldeia() {
		return aldeia;
	}


	public void setAldeia(long aldeia) {
		this.aldeia = aldeia;
	}


	public String getComuna() {
		return comuna;
	}


	public void setComuna(String comuna) {
		this.comuna = comuna;
	}


	public String getMunicipio() {
		return municipio;
	}


	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	//endregion
}
