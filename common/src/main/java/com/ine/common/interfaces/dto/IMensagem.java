package com.ine.common.interfaces.dto;

import com.ine.common.interfaces.dto.*;

public interface IMensagem<IEntidade> {

	//region accessors for public property

	boolean isLida();

	void setLida(boolean lida);

	long getIdRespostaAMensagem();

	void setIdRespostaAMensagem(long idRespostaAMensagem);

	IEntidade getEntidade();

	void setEntidade(IEntidade entidade);

	String getRemetente();

	void setRemetente(String remetente);

	String getDestinatario();

	void setDestinatario(String destinatario);

	String getTitulo();

	void setTitulo(String titulo);

	String getCorpoMensagem();

	void setCorpoMensagem(String corpoMensagem);

	int getEstado();

	void setEstado(int estado);

	//endregion
}
