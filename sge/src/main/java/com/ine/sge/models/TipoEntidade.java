package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.ITipoEntidade;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="SGE_TIPOENTIDADE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoEntidade extends com.ine.sge.models.AuditModel<String> implements ITipoEntidade, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_TIPO_ENTIDADE")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "TIPO")
	@Size(max=100)
	private String tipo;

	@Column(name = "ESTADO")
	private int estado = 1;

	//region accessors for public property

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion

}
