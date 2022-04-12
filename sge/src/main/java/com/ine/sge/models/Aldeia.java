package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IAldeia;
import com.ine.common.interfaces.dto.ICAE;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="SGE_ALDEIA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aldeia<IComuna extends com.ine.sge.models.Comuna> extends com.ine.sge.models.AuditModel<String> implements IAldeia<IComuna>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_ALDEIA")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "CODIGO")
	@Size(max=5)
	private String codigo;

	@Field
	@Column(name = "DESIGNACAO")
	@Size(max=200)
	private String designacao;

	@Column(name = "ESTADO")
	private int estado = 1;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=com.ine.sge.models.Comuna.class)
	@JoinColumn(name = "COMUNA", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	//@JsonIgnore
	private IComuna comuna;

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

	public IComuna getComuna() {
		return comuna;
	}

	public void setComuna(IComuna comuna) {
		this.comuna = comuna;
	}

	//endregion
}
