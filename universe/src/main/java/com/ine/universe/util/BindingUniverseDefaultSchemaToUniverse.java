package com.ine.universe.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ine.universe.models.*;

import javax.transaction.Transactional;
import java.util.Properties;
import java.util.Set;

public class BindingUniverseDefaultSchemaToUniverse {

	public static com.ine.universe.models.Entidade Binding(com.ine.universe.models.Entidade<
			com.ine.universe.models.Socio,
			com.ine.universe.models.EmpresaParticipada,
			com.ine.universe.models.Estabelecimento<com.ine.universe.models.Contacto, com.ine.universe.models.CAE>,
			com.ine.universe.models.CAE,
			com.ine.universe.models.Contacto> Universe_Entidade,

            com.ine.common.dto.UniverseDefaultSchema.Entidade<
			com.ine.universe.models.Socio,
			com.ine.universe.models.EmpresaParticipada,
			com.ine.universe.models.Estabelecimento<com.ine.universe.models.Contacto, com.ine.universe.models.CAE>,
			com.ine.universe.models.CAE,
			com.ine.universe.models.Contacto> UniverseDefaultSchema_Entidade){


		/*Converte os objecto que veem em HashSET e LinkedMAp uma vez que por estarmos a autilizar genericos, este n\ao consegue inferir o tipo de dados para a deserializacao, havendo essa necessidade de faze-la programaticamente*/

		ObjectMapper mapper = new ObjectMapper();

		//CAE
		Set<CAE> convertedCAE = mapper.convertValue(UniverseDefaultSchema_Entidade.getCae(), new TypeReference<Set<CAE>>() { });
		Universe_Entidade.setCae(convertedCAE);


		//CONTACTO
		Contacto convertedContacto = mapper.convertValue(UniverseDefaultSchema_Entidade.getContacto(), Contacto.class);
		Universe_Entidade.setContacto(convertedContacto);


		//EMPRESAPARTICIPADA
		Set<EmpresaParticipada> convertedEmpresaParticipada = mapper.convertValue(UniverseDefaultSchema_Entidade.getEmpresaParticipada(), new TypeReference<Set<EmpresaParticipada>>() { });
		Universe_Entidade.setEmpresaParticipada(convertedEmpresaParticipada);


		//ESTABELECIMENTO
		Set<Estabelecimento<Contacto,CAE>> convertedEstabelecimento = mapper.convertValue(UniverseDefaultSchema_Entidade.getEstabelecimento(), new TypeReference<Set<Estabelecimento<Contacto,CAE>>>() { });
		Universe_Entidade.setEstabelecimento(convertedEstabelecimento);


		//SOCIO
		Set<Socio> convertedSocio = mapper.convertValue(UniverseDefaultSchema_Entidade.getSocio(), new TypeReference<Set<Socio>>() { });
		Universe_Entidade.setSocio(convertedSocio);



		Universe_Entidade.setWebUrlGrupo(UniverseDefaultSchema_Entidade.getWebUrlGrupo());
		Universe_Entidade.setVolumeVendas(UniverseDefaultSchema_Entidade.getVolumeVendas());
		Universe_Entidade.setVolumeNegocioGrupo(UniverseDefaultSchema_Entidade.getVolumeNegocioGrupo());
		Universe_Entidade.setVolume_vendas(UniverseDefaultSchema_Entidade.getVolume_vendas());
		Universe_Entidade.setVersao(UniverseDefaultSchema_Entidade.getVersao());
		Universe_Entidade.setTituloAcademico(UniverseDefaultSchema_Entidade. getTituloAcademico());
		Universe_Entidade.setTipoEntidade(UniverseDefaultSchema_Entidade.getTipoEntidade());
		Universe_Entidade.setTelefoneIndGrupo(UniverseDefaultSchema_Entidade.getTelefoneIndGrupo());
		Universe_Entidade.setTelefoneGrupo(UniverseDefaultSchema_Entidade.getTelefoneGrupo());
		Universe_Entidade.setState(UniverseDefaultSchema_Entidade.getState());
		Universe_Entidade.setSituacaoEmpresa(UniverseDefaultSchema_Entidade.getSituacaoEmpresa());
		Universe_Entidade.setSituacaoActivGrupo(UniverseDefaultSchema_Entidade.getSituacaoActivGrupo());
		Universe_Entidade.setSiac(UniverseDefaultSchema_Entidade.getSiac());
		Universe_Entidade.setSexo(UniverseDefaultSchema_Entidade.getSexo());
		Universe_Entidade.setSector(UniverseDefaultSchema_Entidade.getSector());
		Universe_Entidade.setProfissao(UniverseDefaultSchema_Entidade.getProfissao());
		Universe_Entidade.setPessoa(UniverseDefaultSchema_Entidade.getPessoa());
		Universe_Entidade.setPais(UniverseDefaultSchema_Entidade.getPais());
		Universe_Entidade.setOrigemDocGrupo(UniverseDefaultSchema_Entidade.getOrigemDocGrupo());
		Universe_Entidade.setNumRge(UniverseDefaultSchema_Entidade.getNumRge());
		Universe_Entidade.setNumPessoasServGrupo(UniverseDefaultSchema_Entidade.getNumPessoasServGrupo());
		Universe_Entidade.setNumFuncRemNac(UniverseDefaultSchema_Entidade.getNumFuncRemNac());
		Universe_Entidade.setNumFuncRemEst(UniverseDefaultSchema_Entidade.getNumFuncRemEst());
		Universe_Entidade.setNumFuncNRemNac(UniverseDefaultSchema_Entidade.getNumFuncNRemNac());
		Universe_Entidade.setNumFuncNRemEst(UniverseDefaultSchema_Entidade.getNumFuncNRemEst());
		Universe_Entidade.setNumFuncNrem(UniverseDefaultSchema_Entidade.getNumFuncNrem());
		Universe_Entidade.setNumFuncNac(UniverseDefaultSchema_Entidade.getNumFuncNac());
		Universe_Entidade.setNumFuncMulherRem(UniverseDefaultSchema_Entidade.getNumFuncMulherRem());
		Universe_Entidade.setNumFuncMulherNrem(UniverseDefaultSchema_Entidade.getNumFuncMulherNrem());
		Universe_Entidade.setNumFuncMRemNac(UniverseDefaultSchema_Entidade.getNumFuncMRemNac());
		Universe_Entidade.setNumFuncMRemEst(UniverseDefaultSchema_Entidade.getNumFuncMRemEst());
		Universe_Entidade.setNumFuncMNRemNac(UniverseDefaultSchema_Entidade.getNumFuncMNRemNac());
		Universe_Entidade.setNumFuncMNRemEst(UniverseDefaultSchema_Entidade.getNumFuncMNRemEst());
		Universe_Entidade.setNumFuncionarioMulher(UniverseDefaultSchema_Entidade.getNumFuncionarioMulher());
		Universe_Entidade.setNumFuncionarioMNac(UniverseDefaultSchema_Entidade.getNumFuncionarioMNac());
		Universe_Entidade.setNumFuncionarioMEst(UniverseDefaultSchema_Entidade.getNumFuncionarioMEst());
		Universe_Entidade.setNumFuncionarioHomem(UniverseDefaultSchema_Entidade.getNumFuncionarioHomem());
		Universe_Entidade.setNumFuncionarioHNac(UniverseDefaultSchema_Entidade.getNumFuncionarioHNac());
		Universe_Entidade.setNumFuncionarioHEst(UniverseDefaultSchema_Entidade.getNumFuncionarioHEst());
		Universe_Entidade.setNumFuncHRemNac(UniverseDefaultSchema_Entidade.getNumFuncHRemNac());
		Universe_Entidade.setNumFuncHRemEst(UniverseDefaultSchema_Entidade.getNumFuncHRemEst());
		Universe_Entidade.setNumFuncHomemRem(UniverseDefaultSchema_Entidade.getNumFuncHomemRem());
		Universe_Entidade.setNumFuncHomemNrem(UniverseDefaultSchema_Entidade.getNumFuncHomemNrem());
		Universe_Entidade.setNumFuncHNRemNac(UniverseDefaultSchema_Entidade.getNumFuncHNRemNac());
		Universe_Entidade.setNumFuncHNRemEst(UniverseDefaultSchema_Entidade.getNumFuncHNRemEst());
		Universe_Entidade.setNumFuncEst(UniverseDefaultSchema_Entidade.getNumFuncEst());
		Universe_Entidade.setNumEstabsTotal(UniverseDefaultSchema_Entidade.getNumEstabsTotal());
		Universe_Entidade.setNumEstabelecimentos(UniverseDefaultSchema_Entidade.getNumEstabelecimentos());
		Universe_Entidade.setNumEmpresasNacGrupo(UniverseDefaultSchema_Entidade.getNumEmpresasNacGrupo());
		Universe_Entidade.setNumEmpresasEstGrupo(UniverseDefaultSchema_Entidade.getNumEmpresasEstGrupo());
		Universe_Entidade.setNumDoc(UniverseDefaultSchema_Entidade.getNumDoc());
		Universe_Entidade.setNomeRespGrupo(UniverseDefaultSchema_Entidade.getNomeRespGrupo());
		Universe_Entidade.setNomeGrupo(UniverseDefaultSchema_Entidade.getNomeGrupo());
		Universe_Entidade.setNifGrupo(UniverseDefaultSchema_Entidade.getNifGrupo());
		Universe_Entidade.setNaturezaJurGrupo(UniverseDefaultSchema_Entidade.getNaturezaJurGrupo());
		Universe_Entidade.setNaturalId(UniverseDefaultSchema_Entidade.getNaturalId());
		Universe_Entidade.setMesesActividade(UniverseDefaultSchema_Entidade.getMesesActividade());
		Universe_Entidade.setLongitude(UniverseDefaultSchema_Entidade.getLongitude());
		Universe_Entidade.setLatitude(UniverseDefaultSchema_Entidade.getLatitude());
		Universe_Entidade.setInstituicao(UniverseDefaultSchema_Entidade.getInstituicao());
		Universe_Entidade.setIdade(UniverseDefaultSchema_Entidade.getIdade());
		Universe_Entidade.setGue(UniverseDefaultSchema_Entidade.getGue());
		Universe_Entidade.setGrupoHolding(UniverseDefaultSchema_Entidade.getGrupoHolding());
		Universe_Entidade.setFormaJuridica(UniverseDefaultSchema_Entidade.getFormaJuridica());
		Universe_Entidade.setFaxIndGrupo(UniverseDefaultSchema_Entidade.getFaxIndGrupo());
		Universe_Entidade.setFaxGrupo(UniverseDefaultSchema_Entidade.getFaxGrupo());
		Universe_Entidade.setEstadoAprovacao(UniverseDefaultSchema_Entidade.getEstadoAprovacao());
		Universe_Entidade.setEmailRespGrupo(UniverseDefaultSchema_Entidade.getEmailRespGrupo());
		Universe_Entidade.setEliminado(UniverseDefaultSchema_Entidade.getEliminado());
		Universe_Entidade.setDataultImaInsercao(UniverseDefaultSchema_Entidade.getDataultImaInsercao());
		Universe_Entidade.setDataSituacaoActividade(UniverseDefaultSchema_Entidade.getDataSituacaoActividade());
		Universe_Entidade.setDataSge(UniverseDefaultSchema_Entidade.getDataSge());
		Universe_Entidade.setDataInitactGrupo(UniverseDefaultSchema_Entidade.getDataInitactGrupo());
		Universe_Entidade.setDataFimGrupo(UniverseDefaultSchema_Entidade.getDataFimGrupo());
		Universe_Entidade.setDataFimActGrupo(UniverseDefaultSchema_Entidade.getDataFimActGrupo());
		Universe_Entidade.setDataDocumento(UniverseDefaultSchema_Entidade.getDataDocumento());
		Universe_Entidade.setDataCostituicaoFim(UniverseDefaultSchema_Entidade.getDataCostituicaoFim());
		Universe_Entidade.setDataConstGrupo(UniverseDefaultSchema_Entidade.getDataConstGrupo());
		Universe_Entidade.setContabilidade(UniverseDefaultSchema_Entidade.getContabilidade());
		Universe_Entidade.setComentarioGrupo(UniverseDefaultSchema_Entidade.getComentarioGrupo());
		Universe_Entidade.setComentarioEntidade(UniverseDefaultSchema_Entidade.getComentarioEntidade());
		Universe_Entidade.setCodGrupo(UniverseDefaultSchema_Entidade.getCodGrupo());
		Universe_Entidade.setCapPublico(UniverseDefaultSchema_Entidade.getCapPublico());
		Universe_Entidade.setCapPrivado(UniverseDefaultSchema_Entidade.getCapPrivado());
		Universe_Entidade.setCapitalSocial(UniverseDefaultSchema_Entidade.getCapitalSocial());
		Universe_Entidade.setCapEstrang(UniverseDefaultSchema_Entidade.getCapEstrang());
		Universe_Entidade.setCanal(UniverseDefaultSchema_Entidade.getCanal());
		Universe_Entidade.setCaePrincipalGrupo(UniverseDefaultSchema_Entidade.getCaePrincipalGrupo());
		Universe_Entidade.setBi(UniverseDefaultSchema_Entidade.getBi());
		Universe_Entidade.setAnoEliminacao(UniverseDefaultSchema_Entidade.getAnoEliminacao());
		Universe_Entidade.setAnoActividade(UniverseDefaultSchema_Entidade.getAnoActividade());
		Universe_Entidade.setActiEcoPrincipal(UniverseDefaultSchema_Entidade.getActiEcoPrincipal());
		Universe_Entidade.setAbreviatura(UniverseDefaultSchema_Entidade.getAbreviatura());
		Universe_Entidade.setActiComercial(UniverseDefaultSchema_Entidade.getActiComercial());

		return Universe_Entidade;
	}

	/*POJO pojo = mapper.convertValue(singleObject, POJO.class);
			// or:
			List<POJO> pojos = mapper.convertValue(listOfObjects, new TypeReference<List<POJO>>() { });
			this is functionally same as if you did:

			byte[] json = mapper.writeValueAsBytes(singleObject);
			POJO pojo = mapper.readValue(json, POJO.class);*/
}
