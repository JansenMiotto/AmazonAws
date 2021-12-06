package br.edu.infnet.desenvservnuvemat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.desenvservnuvemat.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer>{

}
