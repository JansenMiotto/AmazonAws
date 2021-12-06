package br.edu.infnet.desenvservnuvemat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.desenvservnuvemat.model.Cotacao;
import br.edu.infnet.desenvservnuvemat.model.Produto;
import br.edu.infnet.desenvservnuvemat.repository.CotacaoRepository;

@RestController
@RequestMapping("cotacao")
public class CotacaoController {
	
	@Autowired
	private CotacaoRepository cotacaoRepository;

	@PostMapping
	public Cotacao save(@RequestParam("fornecedor") String fornecedor, @RequestParam("valor") Double valor){
		Cotacao cotacao = new Cotacao();
		cotacao.setFornecedor(fornecedor);
		cotacao.setValor(valor);
		
		Cotacao cotacaoSalvo = cotacaoRepository.save(cotacao);
		return cotacaoSalvo;
			
		
	}
	
	@PutMapping
	public Cotacao atualizar(@RequestBody Cotacao cotacao) {
		Cotacao c = cotacaoRepository.save(cotacao);
		return c;
		
	}
}
