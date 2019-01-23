package br.com.allanlarangeiras.geradorcontrole.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.allanlarangeiras.geradorcontrole.model.Controle;
import br.com.allanlarangeiras.geradorcontrole.service.GeradorControleService;

@Controller
public class GeradorController {
	
	
	private GeradorControleService geradorControleService;

	@Autowired
	public GeradorController(GeradorControleService geradorControleService) {
		this.geradorControleService = geradorControleService;
	}
	
	@RequestMapping(value= {"/gerar/{cpfCnpj}", "/gerar"}, method=RequestMethod.GET)
	@ResponseBody
	public Controle gerar(@PathVariable(value = "cpfCnpj", required=false) String cpfCnpj) {
		Controle controle = new Controle(cpfCnpj);
		controle.setControle(geradorControleService.gerarCodigoControle(controle.getCpfCnpj()));
		return controle;
	}
	
}
