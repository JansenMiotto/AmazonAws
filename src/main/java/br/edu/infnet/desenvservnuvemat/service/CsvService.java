package br.edu.infnet.desenvservnuvemat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.desenvservnuvemat.model.Cotacao;
import br.edu.infnet.desenvservnuvemat.model.Produto;
import br.edu.infnet.desenvservnuvemat.repository.CotacaoRepository;
import br.edu.infnet.desenvservnuvemat.repository.ProdutoRepository;

@Service
@Transactional
public class CsvService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CotacaoRepository cotacaoRepository;
	
	 public List<Produto> listProduto() {
		 return (List<Produto>) produtoRepository.findAll();
	 }
	
	 public List<Cotacao> listCotacao() {
		 return (List<Cotacao>) cotacaoRepository.findAll();
	 }	
	
}
