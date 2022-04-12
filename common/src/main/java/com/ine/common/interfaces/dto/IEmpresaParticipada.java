package com.ine.common.interfaces.dto;

import java.util.Date;

public interface IEmpresaParticipada {

	//region accessors for public property

	String getContasConsolidada();

	void setContasConsolidada(String contasConsolidada);

	Date getDataIntegracao();

	void setDataIntegracao(Date dataIntegracao);

	float getPercParticipacao();

	void setPercParticipacao(float percParticipacao);

	String getComentario();

	void setComentario(String comentario);

	String getClassName();

	void setClassName(String className);

	int getEstado();

	void setEstado(int estado);

	//endregion
}
