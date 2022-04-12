package com.ine.sge.models;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IPais;
import com.ine.common.interfaces.dto.IPais_ISP_Universe;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Indexed
@Entity
@Table(name="SGE_PAIS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pais extends com.ine.sge.models.AuditModel<String> implements IPais, IPais_ISP_Universe, Serializable{

	private static long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_PAIS")
	//@JsonIgnore
	private long id;

	@Field
	@Column(name = "PAIS")
	private String pais;

	@Column(name = "ESTADO")
	private int estado = 1;

	/*@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "pais")
	@JsonIgnore
    private Set<Provincia> provincia = new HashSet<>();*/

	//region accessors for public property
	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	/*
	public Set<Provincia> getProvincia() {
			return provincia;
		}


	public void setProvincia(Set<Provincia> provincia) {
		this.provincia = provincia;
	}*/
	//endregion
}
