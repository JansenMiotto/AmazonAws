package br.edu.infnet.desenvservnuvemat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.desenvservnuvemat.service.DolarService;
import br.edu.infnet.desenvservnuvemat.service.RealService;

@RestController
@RequestMapping("valor")
public class ValoresController {
	@Autowired
	private DolarService dolarService;
	@Autowired
	private RealService realService;
	
	@GetMapping("dolar")
	public Double valorDolar() {
		return dolarService.getSomaValores();
	}

	@GetMapping("real")
	public Double valorReal() {
		return realService.getSomaValores();
	}

}
