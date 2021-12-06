package br.edu.infnet.desenvservnuvemat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.desenvservnuvemat.model.Cotacao;
import br.edu.infnet.desenvservnuvemat.model.Pagavel;
import br.edu.infnet.desenvservnuvemat.repository.CotacaoRepository;

@Service
public abstract class CotacaoService {

	private CotacaoRepository cotacaoRepository;
//	private String CSV_PATH = "C:\\Users";
	
	
	public CotacaoService(CotacaoRepository cotacaoRepository) {
		this.cotacaoRepository = cotacaoRepository;
	}

	
	
	//getSomaDespesas
	public Double getSomaValores() {
		Iterable<Cotacao> cotacoes = cotacaoRepository.findAll();
		List<Pagavel> pagavelList = new ArrayList<>();
		for(Cotacao cotacao : cotacoes) {
			pagavelList.add(cotacao);
		}
		Double soma = 0d;
		for(Pagavel pagavel : pagavelList) {
			soma = soma + getValor(pagavel);
		}
		
		return soma;
	}
	
	public abstract Double getValor(Pagavel pagavel);
	
	
}
