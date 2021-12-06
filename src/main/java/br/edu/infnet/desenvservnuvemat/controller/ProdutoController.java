package br.edu.infnet.desenvservnuvemat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.infnet.desenvservnuvemat.model.Produto;
import br.edu.infnet.desenvservnuvemat.repository.ProdutoRepository;
import br.edu.infnet.desenvservnuvemat.service.AmazonClienteService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private AmazonClienteService amazonClientService;
	
	@PostMapping
	public Produto salvar(@RequestParam("nome") String nome, @RequestPart(value = "file") MultipartFile file) {
		String amazonFileName = amazonClientService.save(file);
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.SetNomeImagem(amazonFileName);
		
		return produtoRepository.save(produto);
	}
	
	@PutMapping
	public Produto atualizar(@RequestBody Produto produto) {
		Produto p = produtoRepository.save(produto);
		return p;
		
	}
	
	
	
}
