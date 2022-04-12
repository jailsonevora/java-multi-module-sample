package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ICanal;
import com.ine.common.interfaces.dto.ICanal_ISP_Universe;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="SGE_CANAL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Canal extends com.ine.sge.models.AuditModel<String> implements ICanal, ICanal_ISP_Universe, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_CANAL")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CANAL")
	@Size(max=50)
	private String canal;

	@Column(name = "ESTADO")
	private int estado = 1;


	//region accessors for public property

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion

}
