package com.ine.sge.v1.controllers;

import com.ine.sge.dao.IEntidadeRepository;
import com.ine.sge.models.Entidade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("indicadoresControllerV1")
@RequestMapping("/api/v1")
@Api(value = "indicadores", description = "Indicadores API")
public class IndicadoresController {

	private final IEntidadeRepository entidadeRepository;

	@Autowired
	public IndicadoresController(IEntidadeRepository entidadeRepository) {
		this.entidadeRepository = entidadeRepository;
	}

	@RequestMapping(value="/indicadores/entidades/naoatualizados/{tipoentidadeID}/{startdate}/{enddate}", method= RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the entities not upgraded", responseContainer="List")
	public ResponseEntity<Page<?>> showIndicatorsNaoatualizados(@Valid @PathVariable Long tipoentidadeID, @Valid @PathVariable String startdate, @Valid @PathVariable String enddate, @Valid Pageable pageable) {
		try {

			Date startdateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date enddateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);

			return new ResponseEntity<>(entidadeRepository.findAllByUpdatedAtBetweenAndEstadoAndTipoEntidade_IdOrderByUpdatedAtDesc(startdateParsed, enddateParsed, 1, tipoentidadeID,  pageable), HttpStatus.OK);

		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value="/indicadores/entidades/criadoseliminados/{tipoentidadeID}/{startdate}/{enddate}", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the entities created and deleted", responseContainer="List")
	public ResponseEntity<Page<?>> showIndicatorsCriadosEliminados(@Valid @PathVariable Long tipoentidadeID, @Valid @PathVariable String startdate, @Valid @PathVariable String enddate, @Valid Pageable pageable) {
		try {

			Date startdateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date enddateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);

			return new ResponseEntity<>(entidadeRepository.findAllByEstadoAndUpdatedAtBetweenAndTipoEntidade_IdOrderByUpdatedAtDesc(0, startdateParsed, enddateParsed, tipoentidadeID, pageable), HttpStatus.OK);

		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value="/indicadores/entidades/porprovincias/{tipoentidadeID}/{startdate}/{enddate}", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves all the entities by province, return object COUNT - PROVINCE NAME", responseContainer="List")
	public ResponseEntity<Page<?>> showIndicatorsPorProvincias(@Valid @PathVariable Long tipoentidadeID, @Valid @PathVariable String startdate, @Valid @PathVariable String enddate, @Valid Pageable pageable) {
		try {

			Date startdateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date enddateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);

			return new ResponseEntity<>(entidadeRepository.findAllByEntidadesPorProvincias(startdateParsed, enddateParsed, tipoentidadeID, pageable), HttpStatus.OK);

		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value="/indicadores/media/homens/mulheres/porano/{tipoentidadeID}/{startdate}/{enddate}", method=RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Retrieves avg from man and woman by year, return object YEAR - MAN - WOMAN", responseContainer="List")
	public ResponseEntity<Page<?>> showIndicatorsPorAno(@Valid @PathVariable Long tipoentidadeID, @Valid @PathVariable String startdate, @Valid @PathVariable String enddate, @Valid Pageable pageable) {
		try {

			Date startdateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date enddateParsed = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);

			return new ResponseEntity<>(entidadeRepository.avgAllByNumFuncionarioHomemBetween(startdateParsed, enddateParsed, tipoentidadeID, pageable), HttpStatus.OK);

		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
