package br.edu.infnet.desenvservnuvemat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.desenvservnuvemat.model.Pagavel;
import br.edu.infnet.desenvservnuvemat.repository.CotacaoRepository;

@Service
public class DolarService extends CotacaoService{


	@Autowired
	public DolarService(CotacaoRepository cotacaoRepository) {
		super(cotacaoRepository);
	}
	
	
	@Override
	public Double getValor(Pagavel pagavel) {
		// TODO Auto-generated method stub
		return pagavel.getValor() * 5.6;
	}

}
