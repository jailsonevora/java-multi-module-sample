package com.ine.common.interfaces.dto;

public interface INivel {

	//region accessors for public property


	/*public Set <Classificacao> getClassificacoes() {
		return classificacoes;
	}

	public void setClassificacoes(Set <Classificacao> classificacoes) {
		this.classificacoes = classificacoes;
	}*/

	String getDivisao();

	void setDivisao(String divisao);

	String getGrupo();

	void setGrupo(String grupo);

	String getClasse();

	void setClasse(String classe);

	String getSubClasse();

	void setSubClasse(String subClasse);

	String getCategoria();

	void setCategoria(String categoria);

	String getSubCategoria();

	void setSubCategoria(String subCategoria);

	String getPosicao();

	void setPosicao(String posicao);

	int getEstado();

	void setEstado(int estado);

	//endregion
}
