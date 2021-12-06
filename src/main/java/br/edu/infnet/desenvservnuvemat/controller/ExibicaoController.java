package br.edu.infnet.desenvservnuvemat.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import br.edu.infnet.desenvservnuvemat.model.Cotacao;
import br.edu.infnet.desenvservnuvemat.model.Fotografavel;
import br.edu.infnet.desenvservnuvemat.model.Produto;
import br.edu.infnet.desenvservnuvemat.repository.CotacaoRepository;
import br.edu.infnet.desenvservnuvemat.repository.ProdutoRepository;
import br.edu.infnet.desenvservnuvemat.service.AmazonClienteService;
import br.edu.infnet.desenvservnuvemat.service.CsvService;
import br.edu.infnet.desenvservnuvemat.service.DolarService;
import br.edu.infnet.desenvservnuvemat.service.FotografavelService;

@Controller
public class ExibicaoController {
	
	@Autowired
	private FotografavelService fotografavelService;
	@Autowired	
	private AmazonClienteService amazonClientService;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CsvService csvService;
	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private DolarService dolarService;
	
	
	
	@GetMapping("/")
	public String telaFoto(Map<String, Object> model, Model mod) {
		
		List<Fotografavel> all = fotografavelService.findAll();
		model.put("fotografavelList", all);
		mod.addAttribute("valorDolar", dolarService.getSomaValores());
		return "lista";
		
		
	}
	
	@RequestMapping(value = "download/{fileName}", method = RequestMethod.GET)
	public HttpEntity<byte[]> downloadFile(@PathVariable("fileName") String fileName) throws IOException{
		File file = amazonClientService.download(fileName);
		byte[] bytes = Files.readAllBytes(file.toPath());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentLength(bytes.length);
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
		return new HttpEntity<>(bytes, headers);
	
	
	}
	
	
	@GetMapping(value="fotos/{id}/excluir")
	public String excluir(@PathVariable Integer id){//, @PathVariable String fileName) {
		produtoRepository.deleteById(id);
		//amazonClientService.delete(fileName);
		return "redirect:/";
			
	}
	
	
	
	
    @GetMapping("/exportProd")
    public void exportarProdutos(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=produto_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<Produto> listProd = csvService.listProduto();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"id", "nome", "nomeImagem"};
        String[] nameMapping = {"id", "nome", "nomeImagem"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (Produto prod : listProd) {
            csvWriter.write(prod, nameMapping);
        }
         
        csvWriter.close();
         
    }
	
	
    @GetMapping("/exportCot")
    public void exportarCotacoes(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cotacao_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
         
        List<Cotacao> listCot = csvService.listCotacao();
 
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"id", "fornecedor", "valor"};
        String[] nameMapping = {"id", "fornecedor", "valor"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (Cotacao cot : listCot) {
            csvWriter.write(cot, nameMapping);
        }
         
        csvWriter.close();
         
    }
    
    @GetMapping("/cadastro")
    public String telaCadastro() {
    	return "cadastro";
    }
    
    @PostMapping("/cadastro")
    public String cadastro(@RequestParam("nome") String nome, @RequestPart("nomeImagem") MultipartFile file, @RequestParam("fornecedor") String fornecedor, @RequestParam("valor") Double valor) {
    	Produto prod = new Produto();
    	prod.setNome(nome);
    	prod.SetNomeImagem(file.getOriginalFilename());
    	produtoRepository.save(prod);
    	
    	Cotacao cot = new Cotacao();
    	cot.setFornecedor(fornecedor);
    	cot.setValor(valor);
    	cot.setProduto(prod);
    	
    	
    	cotacaoRepository.save(cot);    	
    	amazonClientService.save(file);
    	
    	return "redirect:/cadastro";
    }
    
    
}
