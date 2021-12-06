package br.edu.infnet.desenvservnuvemat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.desenvservnuvemat.model.Fotografavel;
import br.edu.infnet.desenvservnuvemat.model.Produto;
import br.edu.infnet.desenvservnuvemat.repository.ProdutoRepository;

@Service
public class FotografavelService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<Fotografavel> findAll(){
		Iterable<Produto> produtos = produtoRepository.findAll();
		List<Fotografavel> fotografavelList = new ArrayList<>();
		for(Produto prod : produtos) {
			fotografavelList.add(prod);
		}
		
		return fotografavelList;
	}
}
