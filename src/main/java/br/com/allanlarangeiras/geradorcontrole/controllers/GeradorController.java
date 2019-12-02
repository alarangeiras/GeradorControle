package br.com.allanlarangeiras.geradorcontrole.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.allanlarangeiras.geradorcontrole.model.Controle;
import br.com.allanlarangeiras.geradorcontrole.service.GeradorControleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="Gerador de Controle")
public class GeradorController {
	
	@Autowired
	private GeradorControleService geradorControleService;
	
	@RequestMapping(value= {"/gerar/{cpfCnpj}", "/gerar"}, method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "Gera o controle com base no CNPJ")
	public Controle gerar(@PathVariable(value = "cpfCnpj", required=false) String cpfCnpj) {
		Controle controle = new Controle(cpfCnpj);
		controle.setControle(geradorControleService.gerarCodigoControle(controle.getCpfCnpj()));
		return controle;
	}
	
}
