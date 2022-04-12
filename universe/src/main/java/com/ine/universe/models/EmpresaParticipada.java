package com.ine.universe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IEmpresaParticipada;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Indexed
@Entity
@Table(name="FUE_EMPPARTICIPADA")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmpresaParticipada extends com.ine.universe.models.AuditModel<String> implements IEmpresaParticipada, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_EMPAR")
	@JsonIgnore
	private long id;

	@Field
	@Column(name = "CONTASCONSOL")
	@Size(max=1)
	private String contasConsolidada;

	@Field
	@Column(name = "DATAINTEGRACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dataIntegracao;

	@Column(name = "PERCPARTICIPACAO")
	private float percParticipacao;

	@Field
	@Column(name = "COMENTARIO")
	@Size(max=400)
	private String comentario;

	@Field
	@Column(name = "CLASSNAME")
	@Size(max=50)
	private String className;

	@Column(name = "ESTADO")
	private int estado = 1;

	//FUE_ENTIDADE$EMPPARTICIPADA
	/*@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "empresa_participada")
	@JsonIgnore
	private Set<Entidade> entidade = new HashSet<>();*/

	//region accessors for public property

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
