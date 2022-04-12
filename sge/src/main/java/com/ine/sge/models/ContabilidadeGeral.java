package com.ine.sge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Indexed
@Entity
@Table(name="SGE_CONTABILIDADEGERAL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContabilidadeGeral extends com.ine.sge.models.AuditModel<String> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "ID_CONTBGERAL")
	//@JsonIgnore
	private long id;

	@Column(name = "X61_1_VENDAS_PRODUCTOS")
	private float X61_1_Vendas_Productos;

	@Column(name = "X61_3_VENDAS_MERCADORIAS")
	private float X61_3_Vendas_Mercadorias;

	@Column(name = "X62_PRESTACAO_SERVICOS")
	private float X62_Prestacao_Servicos;

	@Column(name = "X63_OUTROS_PROVEITOS")
	private float X63_Outros_Proveitos;

	@Column(name = "X64_VARIACAO_PRODUCAO")
	private float X64_Variacao_Producao;

	@Column(name = "X65_TRABALHOS_PARA_EMPRESA")
	private float X65_Trabalhos_Para_Empresa;

	@Column(name = "X66_PROV_GANHOS_GERAIS")
	private float X66_Prov_Ganhos_Gerais;

	@Column(name = "X67_PROV_GANHOS_FILIAL")
	private float X67_Prov_Ganhos_Filial;

	@Column(name = "X68_OUTROS_PROVEITOS")
	private float X68_Outros_Proveitos;

	@Column(name = "X69_PROVEITO_GANHOS_EXT")
	private float X69_Proveito_Ganhos_Ext;

	@Column(name = "X71_CUSTOS_MERCADORIAS")
	private float X71_Custos_Mercadorias;

	@Column(name = "X72_CUSTOS_PESSOAL")
	private float X72_Custos_Pessoal;

	@Column(name = "X73_AMORTIZACAO")
	private float X73_Amortizacao;

	@Column(name = "X75_1_SUBCONTRACTOS")
	private float X75_1_SubContractos;

	@Column(name = "X75_2_FORN_SERV_TER")
	private float X75_2_Forn_Serv_Ter;

	@Column(name = "X75_3_IMPOSTOS")
	private float X75_3_Impostos;

	@Column(name = "X75_8_OUTROS_CUSTOS")
	private float X75_8_Outros_Custos;

	@Column(name = "X76_CUSTOS_PERDAS_GERAIS")
	private float X76_Custos_Perdas_Gerais;

	@Column(name = "X77_CUSTOS_PERDAS_FILIAL")
	private float X77_Custos_Perdas_Filial;

	@Column(name = "X78_OUTROS_CUSTOS")
	private float X78_Outros_Custos;

	@Column(name = "X79_CUSTOS_PERDAS_EXT")
	private float X79_Custos_Perdas_Ext;

	@Column(name = "X87_IMPOSTOS_LUCROS")
	private float X87_Impostos_Lucros;

	@Column(name = "X88_RESULTADOS_LIQUIDOS")
	private float X88_Resultados_Liquidos;

	@Column(name = "X11_1_TERRENOS_RECURSOS")
	private float X11_1_Terrenos_Recursos;

	@Column(name = "X11_2_EDIFICIOS")
	private float X11_2_Edificios;

	@Column(name = "X11_3_EQUIP_BASIC")
	private float X11_3_Equip_Basic;

	@Column(name = "X11_4_1_EQUIP_TRANSP")
	private float X11_4_1_Equip_Transp;

	@Column(name = "X11_5_EQUIP_ADMIN")
	private float X11_5_Equip_Admin;

	@Column(name = "X11_9_OUTROS_IMOB")
	private float X11_9_Outros_Imob;

	@Column(name = "X75_2_11_AGUA")
	private float X75_2_11_Agua;

	@Column(name = "X75_2_12_ELECTRICIDADE")
	private float X75_2_12_Electricidade;

	@Column(name = "X75_2_13_COMBUSTIVEIS")
	private float X75_2_13_Combustiveis;

	@Column(name = "X75_2_14_CONSERVASAO")
	private float X75_2_14_Conservasao;

	@Column(name = "X75_2_15_MATERIAL")
	private float X75_2_15_Material;

	@Column(name = "X75_2_16_FERRAMENT")
	private float X75_2_16_Ferrament;

	@Column(name = "X75_2_17_MATERIAL_ESCRIT")
	private float X75_2_17_Material_Escrit;

	@Column(name = "X75_2_18_LIVROS")
	private float X75_2_18_Livros;

	@Column(name = "X75_2_19_OUTROS_FORN")
	private float X75_2_19_Outros_Forn;

	@Column(name = "X75_2_20_COMUNICACAO")
	private float X75_2_20_Comunicacao;

	@Column(name = "X75_2_21_RENDAS")
	private float X75_2_21_Rendas;

	@Column(name = "X75_2_22_SEGUROS")
	private float X75_2_22_Seguros;

	@Column(name = "X75_2_23_DESLOCACAO")
	private float X75_2_23_Deslocacao;

	@Column(name = "X75_2_24_DESP_REPRES")
	private float X75_2_24_Desp_Repres;

	@Column(name = "X75_2_26_CONS_REP")
	private float X75_2_26_Cons_Rep;

	@Column(name = "X75_2_27_SEGURANCA")
	private float X75_2_27_Seguranca;

	@Column(name = "X75_2_28_LIMPEZA")
	private float X75_2_28_Limpeza;

	@Column(name = "X75_2_29_PUBLICIDADE")
	private float X75_2_29_Publicidade;

	@Column(name = "X75_2_30_CONTENCIOSO")
	private float X75_2_30_Contencioso;

	@Column(name = "X75_2_31_COMISSOES")
	private float X75_2_31_Comissoes;

	@Column(name = "X75_2_32_1_ASSISTENCIA_ESTRAN")
	private float X75_2_32_1_Assistencia_Estran;

	@Column(name = "X75_2_32_2_ASSISTENCIA_NAC")
	private float X75_2_32_2_Assistencia_Nac;

	@Column(name = "X75_2_33_TRAB_EXTERIOR")
	private float X75_2_33_Trab_Exterior;

	@Column(name = "X75_2_34_HONONARIOS")
	private float X75_2_34_Hononarios;

	@Column(name = "X75_2_35_ROYALITIES")
	private float X75_2_35_Royalities;

	@Column(name = "X75_2_35_OUTROS_SERV")
	private float X75_2_35_Outros_Serv;

	@Column(name = "X75_3_1_1_IMPOSTO_SELO")
	private float X75_3_1_1_Imposto_Selo;

	@Column(name = "X75_3_1_2_DIR_ADUANEIROS")
	private float X75_3_1_2_Dir_Aduaneiros;

	@Column(name = "X75_3_1_3_TAXAS")
	private float X75_3_1_3_Taxas;

	@Column(name = "X75_3_1_9_OUTROS_IMP_INDIR")
	private float X75_3_1_9_Outros_Imp_Indir;

	@Column(name = "X75_3_2_1_IMPOSTOS_CAPITAIS")
	private float X75_3_2_1_Impostos_Capitais;

	@Column(name = "X75_3_2_2_CONTRIB_PREDIAL")
	private float X75_3_2_2_Contrib_Predial;

	@Column(name = "X75_3_2_3_IMP_INDUS")
	private float X75_3_2_3_Imp_Indus;

	@Column(name = "X75_3_2_9_OUTROS_IMP_DIRECT")
	private float X75_3_2_9_Outros_Imp_Direct;

	@Column(name = "X72_1_RENUM_ORGAOS")
	private float X72_1_Renum_Orgaos;

	@Column(name = "X72_2_RENUM_PESSOAL")
	private float X72_2_Renum_Pessoal;

	@Column(name = "X72_3_1_PENSAO_ORGAIS")
	private float X72_3_1_Pensao_Orgais;

	@Column(name = "X72_3_2_PENSAO_PESSOAL")
	private float X72_3_2_Pensao_Pessoal;

	@Column(name = "X72_4_PREMIO_PENSAO")
	private float X72_4_Premio_Pensao;

	@Column(name = "X72_5_ENCARGOS")
	private float X72_5_Encargos;

	@Column(name = "X72_6_SEGUROS")
	private float X72_6_Seguros;

	@Column(name = "X72_7_FORMACAO")
	private float X72_7_Formacao;

	@Column(name = "X72_8_DESPESAS_PESSOAL")
	private float X72_8_Despesas_Pessoal;

	//region accessors for public property

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getX61_1_Vendas_Productos() {
		return X61_1_Vendas_Productos;
	}

	public void setX61_1_Vendas_Productos(float x61_1_Vendas_Productos) {
		X61_1_Vendas_Productos = x61_1_Vendas_Productos;
	}

	public float getX61_3_Vendas_Mercadorias() {
		return X61_3_Vendas_Mercadorias;
	}

	public void setX61_3_Vendas_Mercadorias(float x61_3_Vendas_Mercadorias) {
		X61_3_Vendas_Mercadorias = x61_3_Vendas_Mercadorias;
	}

	public float getX62_Prestacao_Servicos() {
		return X62_Prestacao_Servicos;
	}

	public void setX62_Prestacao_Servicos(float x62_Prestacao_Servicos) {
		X62_Prestacao_Servicos = x62_Prestacao_Servicos;
	}

	public float getX63_Outros_Proveitos() {
		return X63_Outros_Proveitos;
	}

	public void setX63_Outros_Proveitos(float x63_Outros_Proveitos) {
		X63_Outros_Proveitos = x63_Outros_Proveitos;
	}

	public float getX64_Variacao_Producao() {
		return X64_Variacao_Producao;
	}

	public void setX64_Variacao_Producao(float x64_Variacao_Producao) {
		X64_Variacao_Producao = x64_Variacao_Producao;
	}

	public float getX65_Trabalhos_Para_Empresa() {
		return X65_Trabalhos_Para_Empresa;
	}

	public void setX65_Trabalhos_Para_Empresa(float x65_Trabalhos_Para_Empresa) {
		X65_Trabalhos_Para_Empresa = x65_Trabalhos_Para_Empresa;
	}

	public float getX66_Prov_Ganhos_Gerais() {
		return X66_Prov_Ganhos_Gerais;
	}

	public void setX66_Prov_Ganhos_Gerais(float x66_Prov_Ganhos_Gerais) {
		X66_Prov_Ganhos_Gerais = x66_Prov_Ganhos_Gerais;
	}

	public float getX67_Prov_Ganhos_Filial() {
		return X67_Prov_Ganhos_Filial;
	}

	public void setX67_Prov_Ganhos_Filial(float x67_Prov_Ganhos_Filial) {
		X67_Prov_Ganhos_Filial = x67_Prov_Ganhos_Filial;
	}

	public float getX68_Outros_Proveitos() {
		return X68_Outros_Proveitos;
	}

	public void setX68_Outros_Proveitos(float x68_Outros_Proveitos) {
		X68_Outros_Proveitos = x68_Outros_Proveitos;
	}

	public float getX69_Proveito_Ganhos_Ext() {
		return X69_Proveito_Ganhos_Ext;
	}

	public void setX69_Proveito_Ganhos_Ext(float x69_Proveito_Ganhos_Ext) {
		X69_Proveito_Ganhos_Ext = x69_Proveito_Ganhos_Ext;
	}

	public float getX71_Custos_Mercadorias() {
		return X71_Custos_Mercadorias;
	}

	public void setX71_Custos_Mercadorias(float x71_Custos_Mercadorias) {
		X71_Custos_Mercadorias = x71_Custos_Mercadorias;
	}

	public float getX72_Custos_Pessoal() {
		return X72_Custos_Pessoal;
	}

	public void setX72_Custos_Pessoal(float x72_Custos_Pessoal) {
		X72_Custos_Pessoal = x72_Custos_Pessoal;
	}

	public float getX73_Amortizacao() {
		return X73_Amortizacao;
	}

	public void setX73_Amortizacao(float x73_Amortizacao) {
		X73_Amortizacao = x73_Amortizacao;
	}

	public float getX75_1_SubContractos() {
		return X75_1_SubContractos;
	}

	public void setX75_1_SubContractos(float x75_1_SubContractos) {
		X75_1_SubContractos = x75_1_SubContractos;
	}

	public float getX75_2_Forn_Serv_Ter() {
		return X75_2_Forn_Serv_Ter;
	}

	public void setX75_2_Forn_Serv_Ter(float x75_2_Forn_Serv_Ter) {
		X75_2_Forn_Serv_Ter = x75_2_Forn_Serv_Ter;
	}

	public float getX75_3_Impostos() {
		return X75_3_Impostos;
	}

	public void setX75_3_Impostos(float x75_3_Impostos) {
		X75_3_Impostos = x75_3_Impostos;
	}

	public float getX75_8_Outros_Custos() {
		return X75_8_Outros_Custos;
	}

	public void setX75_8_Outros_Custos(float x75_8_Outros_Custos) {
		X75_8_Outros_Custos = x75_8_Outros_Custos;
	}

	public float getX76_Custos_Perdas_Gerais() {
		return X76_Custos_Perdas_Gerais;
	}

	public void setX76_Custos_Perdas_Gerais(float x76_Custos_Perdas_Gerais) {
		X76_Custos_Perdas_Gerais = x76_Custos_Perdas_Gerais;
	}

	public float getX77_Custos_Perdas_Filial() {
		return X77_Custos_Perdas_Filial;
	}

	public void setX77_Custos_Perdas_Filial(float x77_Custos_Perdas_Filial) {
		X77_Custos_Perdas_Filial = x77_Custos_Perdas_Filial;
	}

	public float getX78_Outros_Custos() {
		return X78_Outros_Custos;
	}

	public void setX78_Outros_Custos(float x78_Outros_Custos) {
		X78_Outros_Custos = x78_Outros_Custos;
	}

	public float getX79_Custos_Perdas_Ext() {
		return X79_Custos_Perdas_Ext;
	}

	public void setX79_Custos_Perdas_Ext(float x79_Custos_Perdas_Ext) {
		X79_Custos_Perdas_Ext = x79_Custos_Perdas_Ext;
	}

	public float getX87_Impostos_Lucros() {
		return X87_Impostos_Lucros;
	}

	public void setX87_Impostos_Lucros(float x87_Impostos_Lucros) {
		X87_Impostos_Lucros = x87_Impostos_Lucros;
	}

	public float getX88_Resultados_Liquidos() {
		return X88_Resultados_Liquidos;
	}

	public void setX88_Resultados_Liquidos(float x88_Resultados_Liquidos) {
		X88_Resultados_Liquidos = x88_Resultados_Liquidos;
	}

	public float getX11_1_Terrenos_Recursos() {
		return X11_1_Terrenos_Recursos;
	}

	public void setX11_1_Terrenos_Recursos(float x11_1_Terrenos_Recursos) {
		X11_1_Terrenos_Recursos = x11_1_Terrenos_Recursos;
	}

	public float getX11_2_Edificios() {
		return X11_2_Edificios;
	}

	public void setX11_2_Edificios(float x11_2_Edificios) {
		X11_2_Edificios = x11_2_Edificios;
	}

	public float getX11_3_Equip_Basic() {
		return X11_3_Equip_Basic;
	}

	public void setX11_3_Equip_Basic(float x11_3_Equip_Basic) {
		X11_3_Equip_Basic = x11_3_Equip_Basic;
	}

	public float getX11_4_1_Equip_Transp() {
		return X11_4_1_Equip_Transp;
	}

	public void setX11_4_1_Equip_Transp(float x11_4_1_Equip_Transp) {
		X11_4_1_Equip_Transp = x11_4_1_Equip_Transp;
	}

	public float getX11_5_Equip_Admin() {
		return X11_5_Equip_Admin;
	}

	public void setX11_5_Equip_Admin(float x11_5_Equip_Admin) {
		X11_5_Equip_Admin = x11_5_Equip_Admin;
	}

	public float getX11_9_Outros_Imob() {
		return X11_9_Outros_Imob;
	}

	public void setX11_9_Outros_Imob(float x11_9_Outros_Imob) {
		X11_9_Outros_Imob = x11_9_Outros_Imob;
	}

	public float getX75_2_11_Agua() {
		return X75_2_11_Agua;
	}

	public void setX75_2_11_Agua(float x75_2_11_Agua) {
		X75_2_11_Agua = x75_2_11_Agua;
	}

	public float getX75_2_12_Electricidade() {
		return X75_2_12_Electricidade;
	}

	public void setX75_2_12_Electricidade(float x75_2_12_Electricidade) {
		X75_2_12_Electricidade = x75_2_12_Electricidade;
	}

	public float getX75_2_13_Combustiveis() {
		return X75_2_13_Combustiveis;
	}

	public void setX75_2_13_Combustiveis(float x75_2_13_Combustiveis) {
		X75_2_13_Combustiveis = x75_2_13_Combustiveis;
	}

	public float getX75_2_14_Conservasao() {
		return X75_2_14_Conservasao;
	}

	public void setX75_2_14_Conservasao(float x75_2_14_Conservasao) {
		X75_2_14_Conservasao = x75_2_14_Conservasao;
	}

	public float getX75_2_15_Material() {
		return X75_2_15_Material;
	}

	public void setX75_2_15_Material(float x75_2_15_Material) {
		X75_2_15_Material = x75_2_15_Material;
	}

	public float getX75_2_16_Ferrament() {
		return X75_2_16_Ferrament;
	}

	public void setX75_2_16_Ferrament(float x75_2_16_Ferrament) {
		X75_2_16_Ferrament = x75_2_16_Ferrament;
	}

	public float getX75_2_17_Material_Escrit() {
		return X75_2_17_Material_Escrit;
	}

	public void setX75_2_17_Material_Escrit(float x75_2_17_Material_Escrit) {
		X75_2_17_Material_Escrit = x75_2_17_Material_Escrit;
	}

	public float getX75_2_18_Livros() {
		return X75_2_18_Livros;
	}

	public void setX75_2_18_Livros(float x75_2_18_Livros) {
		X75_2_18_Livros = x75_2_18_Livros;
	}

	public float getX75_2_19_Outros_Forn() {
		return X75_2_19_Outros_Forn;
	}

	public void setX75_2_19_Outros_Forn(float x75_2_19_Outros_Forn) {
		X75_2_19_Outros_Forn = x75_2_19_Outros_Forn;
	}

	public float getX75_2_20_Comunicacao() {
		return X75_2_20_Comunicacao;
	}

	public void setX75_2_20_Comunicacao(float x75_2_20_Comunicacao) {
		X75_2_20_Comunicacao = x75_2_20_Comunicacao;
	}

	public float getX75_2_21_Rendas() {
		return X75_2_21_Rendas;
	}

	public void setX75_2_21_Rendas(float x75_2_21_Rendas) {
		X75_2_21_Rendas = x75_2_21_Rendas;
	}

	public float getX75_2_22_Seguros() {
		return X75_2_22_Seguros;
	}

	public void setX75_2_22_Seguros(float x75_2_22_Seguros) {
		X75_2_22_Seguros = x75_2_22_Seguros;
	}

	public float getX75_2_23_Deslocacao() {
		return X75_2_23_Deslocacao;
	}

	public void setX75_2_23_Deslocacao(float x75_2_23_Deslocacao) {
		X75_2_23_Deslocacao = x75_2_23_Deslocacao;
	}

	public float getX75_2_24_Desp_Repres() {
		return X75_2_24_Desp_Repres;
	}

	public void setX75_2_24_Desp_Repres(float x75_2_24_Desp_Repres) {
		X75_2_24_Desp_Repres = x75_2_24_Desp_Repres;
	}

	public float getX75_2_26_Cons_Rep() {
		return X75_2_26_Cons_Rep;
	}

	public void setX75_2_26_Cons_Rep(float x75_2_26_Cons_Rep) {
		X75_2_26_Cons_Rep = x75_2_26_Cons_Rep;
	}

	public float getX75_2_27_Seguranca() {
		return X75_2_27_Seguranca;
	}

	public void setX75_2_27_Seguranca(float x75_2_27_Seguranca) {
		X75_2_27_Seguranca = x75_2_27_Seguranca;
	}

	public float getX75_2_28_Limpeza() {
		return X75_2_28_Limpeza;
	}

	public void setX75_2_28_Limpeza(float x75_2_28_Limpeza) {
		X75_2_28_Limpeza = x75_2_28_Limpeza;
	}

	public float getX75_2_29_Publicidade() {
		return X75_2_29_Publicidade;
	}

	public void setX75_2_29_Publicidade(float x75_2_29_Publicidade) {
		X75_2_29_Publicidade = x75_2_29_Publicidade;
	}

	public float getX75_2_30_Contencioso() {
		return X75_2_30_Contencioso;
	}

	public void setX75_2_30_Contencioso(float x75_2_30_Contencioso) {
		X75_2_30_Contencioso = x75_2_30_Contencioso;
	}

	public float getX75_2_31_Comissoes() {
		return X75_2_31_Comissoes;
	}

	public void setX75_2_31_Comissoes(float x75_2_31_Comissoes) {
		X75_2_31_Comissoes = x75_2_31_Comissoes;
	}

	public float getX75_2_32_1_Assistencia_Estran() {
		return X75_2_32_1_Assistencia_Estran;
	}

	public void setX75_2_32_1_Assistencia_Estran(float x75_2_32_1_Assistencia_Estran) {
		X75_2_32_1_Assistencia_Estran = x75_2_32_1_Assistencia_Estran;
	}

	public float getX75_2_32_2_Assistencia_Nac() {
		return X75_2_32_2_Assistencia_Nac;
	}

	public void setX75_2_32_2_Assistencia_Nac(float x75_2_32_2_Assistencia_Nac) {
		X75_2_32_2_Assistencia_Nac = x75_2_32_2_Assistencia_Nac;
	}

	public float getX75_2_33_Trab_Exterior() {
		return X75_2_33_Trab_Exterior;
	}

	public void setX75_2_33_Trab_Exterior(float x75_2_33_Trab_Exterior) {
		X75_2_33_Trab_Exterior = x75_2_33_Trab_Exterior;
	}

	public float getX75_2_34_Hononarios() {
		return X75_2_34_Hononarios;
	}

	public void setX75_2_34_Hononarios(float x75_2_34_Hononarios) {
		X75_2_34_Hononarios = x75_2_34_Hononarios;
	}

	public float getX75_2_35_Royalities() {
		return X75_2_35_Royalities;
	}

	public void setX75_2_35_Royalities(float x75_2_35_Royalities) {
		X75_2_35_Royalities = x75_2_35_Royalities;
	}

	public float getX75_2_35_Outros_Serv() {
		return X75_2_35_Outros_Serv;
	}

	public void setX75_2_35_Outros_Serv(float x75_2_35_Outros_Serv) {
		X75_2_35_Outros_Serv = x75_2_35_Outros_Serv;
	}

	public float getX75_3_1_1_Imposto_Selo() {
		return X75_3_1_1_Imposto_Selo;
	}

	public void setX75_3_1_1_Imposto_Selo(float x75_3_1_1_Imposto_Selo) {
		X75_3_1_1_Imposto_Selo = x75_3_1_1_Imposto_Selo;
	}

	public float getX75_3_1_2_Dir_Aduaneiros() {
		return X75_3_1_2_Dir_Aduaneiros;
	}

	public void setX75_3_1_2_Dir_Aduaneiros(float x75_3_1_2_Dir_Aduaneiros) {
		X75_3_1_2_Dir_Aduaneiros = x75_3_1_2_Dir_Aduaneiros;
	}

	public float getX75_3_1_3_Taxas() {
		return X75_3_1_3_Taxas;
	}

	public void setX75_3_1_3_Taxas(float x75_3_1_3_Taxas) {
		X75_3_1_3_Taxas = x75_3_1_3_Taxas;
	}

	public float getX75_3_1_9_Outros_Imp_Indir() {
		return X75_3_1_9_Outros_Imp_Indir;
	}

	public void setX75_3_1_9_Outros_Imp_Indir(float x75_3_1_9_Outros_Imp_Indir) {
		X75_3_1_9_Outros_Imp_Indir = x75_3_1_9_Outros_Imp_Indir;
	}

	public float getX75_3_2_1_Impostos_Capitais() {
		return X75_3_2_1_Impostos_Capitais;
	}

	public void setX75_3_2_1_Impostos_Capitais(float x75_3_2_1_Impostos_Capitais) {
		X75_3_2_1_Impostos_Capitais = x75_3_2_1_Impostos_Capitais;
	}

	public float getX75_3_2_2_Contrib_Predial() {
		return X75_3_2_2_Contrib_Predial;
	}

	public void setX75_3_2_2_Contrib_Predial(float x75_3_2_2_Contrib_Predial) {
		X75_3_2_2_Contrib_Predial = x75_3_2_2_Contrib_Predial;
	}

	public float getX75_3_2_3_Imp_Indus() {
		return X75_3_2_3_Imp_Indus;
	}

	public void setX75_3_2_3_Imp_Indus(float x75_3_2_3_Imp_Indus) {
		X75_3_2_3_Imp_Indus = x75_3_2_3_Imp_Indus;
	}

	public float getX75_3_2_9_Outros_Imp_Direct() {
		return X75_3_2_9_Outros_Imp_Direct;
	}

	public void setX75_3_2_9_Outros_Imp_Direct(float x75_3_2_9_Outros_Imp_Direct) {
		X75_3_2_9_Outros_Imp_Direct = x75_3_2_9_Outros_Imp_Direct;
	}

	public float getX72_1_Renum_Orgaos() {
		return X72_1_Renum_Orgaos;
	}

	public void setX72_1_Renum_Orgaos(float x72_1_Renum_Orgaos) {
		X72_1_Renum_Orgaos = x72_1_Renum_Orgaos;
	}

	public float getX72_2_Renum_Pessoal() {
		return X72_2_Renum_Pessoal;
	}

	public void setX72_2_Renum_Pessoal(float x72_2_Renum_Pessoal) {
		X72_2_Renum_Pessoal = x72_2_Renum_Pessoal;
	}

	public float getX72_3_1_Pensao_Orgais() {
		return X72_3_1_Pensao_Orgais;
	}

	public void setX72_3_1_Pensao_Orgais(float x72_3_1_Pensao_Orgais) {
		X72_3_1_Pensao_Orgais = x72_3_1_Pensao_Orgais;
	}

	public float getX72_3_2_Pensao_Pessoal() {
		return X72_3_2_Pensao_Pessoal;
	}

	public void setX72_3_2_Pensao_Pessoal(float x72_3_2_Pensao_Pessoal) {
		X72_3_2_Pensao_Pessoal = x72_3_2_Pensao_Pessoal;
	}

	public float getX72_4_Premio_Pensao() {
		return X72_4_Premio_Pensao;
	}

	public void setX72_4_Premio_Pensao(float x72_4_Premio_Pensao) {
		X72_4_Premio_Pensao = x72_4_Premio_Pensao;
	}

	public float getX72_5_Encargos() {
		return X72_5_Encargos;
	}

	public void setX72_5_Encargos(float x72_5_Encargos) {
		X72_5_Encargos = x72_5_Encargos;
	}

	public float getX72_6_Seguros() {
		return X72_6_Seguros;
	}

	public void setX72_6_Seguros(float x72_6_Seguros) {
		X72_6_Seguros = x72_6_Seguros;
	}

	public float getX72_7_Formacao() {
		return X72_7_Formacao;
	}

	public void setX72_7_Formacao(float x72_7_Formacao) {
		X72_7_Formacao = x72_7_Formacao;
	}

	public float getX72_8_Despesas_Pessoal() {
		return X72_8_Despesas_Pessoal;
	}

	public void setX72_8_Despesas_Pessoal(float x72_8_Despesas_Pessoal) {
		X72_8_Despesas_Pessoal = x72_8_Despesas_Pessoal;
	}


	//endregion


}
