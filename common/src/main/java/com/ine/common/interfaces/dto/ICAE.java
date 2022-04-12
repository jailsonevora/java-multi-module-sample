package com.ine.common.interfaces.dto;

public interface ICAE {

	//region accessors for public property

	/*public Set <Estabelecimento> getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Set <Estabelecimento> estabelecimento) {
		this.estabelecimento = estabelecimento;
	}*/

	/*public Set <Entidade> getEntidade() {
		return entidade;
	}

	public void setEntidade(Set <Entidade> entidade) {
		this.entidade = entidade;
	}*/

	String getCae();

	void setCae(String cae);

	String getDesignacao();

	void setDesignacao(String designacao);

	String getClassName();

	void setClassName(String className);

	int getEstado();

	void setEstado(int estado);

	//endregion
}
