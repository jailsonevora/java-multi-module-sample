package com.ine.common.interfaces.dto;

import java.util.Set;

public interface IClassificacao<INivel> {

	String getClasificacao();

	void setClasificacao(String clasificacao);

	String getDesignacao();

	void setDesignacao(String designacao);

	String getClassName();

	void setClassName(String className);

	int getEstado();

	void setEstado(int estado);

	Set<INivel> getNiveis();

	void setNiveis(Set <INivel> niveis);
}
