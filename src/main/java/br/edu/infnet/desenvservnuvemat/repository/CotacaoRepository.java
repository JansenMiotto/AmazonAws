package br.edu.infnet.desenvservnuvemat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.desenvservnuvemat.model.Cotacao;

@Repository
public interface CotacaoRepository extends CrudRepository<Cotacao, Integer>{
}
