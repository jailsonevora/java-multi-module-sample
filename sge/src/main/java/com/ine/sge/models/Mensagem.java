package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ine.common.interfaces.dto.IEntidade;
import com.ine.common.interfaces.dto.IMensagem;
import com.ine.sge.models.Entidade;
import org.hibernate.annotations.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="SGE_MENSAGEM")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mensagem<IEntidade extends com.ine.sge.models.Entidade> extends com.ine.sge.models.AuditModel<String> implements IMensagem<IEntidade>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_MENSAGEM")
	private long id;

	@Field
	@Column(name = "REMETENTE")
	@Size(max=200)
	private String remetente;

	@Field
	@Column(name = "DESTINATARIO")
	@Size(max=200)
	private String destinatario;

	@Field
	@Column(name = "TITULO")
	@Size(max=400)
	private String titulo;

	@Field
	@Lob
	@Column(name = "CORPO_MENSAGEM")
	private String corpoMensagem;

	@Column(name = "LIDAS")
	private boolean lida;

	@Column(name = "ID_Resposta_A_MENSAGEM")
	private long idRespostaAMensagem;

	@Column(name = "ESTADO")
	private int estado = 1;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity=com.ine.sge.models.Entidade.class)
	//@LazyToOne(LazyToOneOption.NO_PROXY)
	@JoinColumn(name = "ENTIDADE")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private IEntidade entidade;

	//region accessors for public property

	public boolean isLida() {
		return lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}

	public long getIdRespostaAMensagem() {
		return idRespostaAMensagem;
	}

	public void setIdRespostaAMensagem(long idRespostaAMensagem) {
		this.idRespostaAMensagem = idRespostaAMensagem;
	}

	public IEntidade getEntidade() {
		return entidade;
	}

	public void setEntidade(IEntidade entidade) {
		this.entidade = entidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCorpoMensagem() {
		return corpoMensagem;
	}

	public void setCorpoMensagem(String corpoMensagem) {
		this.corpoMensagem = corpoMensagem;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	//endregion
}
