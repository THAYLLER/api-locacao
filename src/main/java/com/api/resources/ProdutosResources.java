package com.api.resources;

import com.api.domain.Produtos;
import com.api.repository.ProdutosRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/produtos")
public class ProdutosResources {
    
    @Autowired
    private ProdutosRepository  produtosRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Produtos> listarAll() {
        
        return produtosRepository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void salvar(@RequestBody Produtos produtos) {
        
        produtosRepository.save(produtos);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Produtos buscar(@PathVariable("id") Long id) {
        
        Produtos produtos = produtosRepository.getOne(id);
        return produtos;
    }
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable("id") Long id) {
        
        produtosRepository.deleteById(id);
    }
    //parei na aula 2.9 da algarworks
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public void atualizar(@RequestBody Produtos produtos,@PathVariable("id") Long id) {
        
        produtos.setIdProduto(id);
        produtosRepository.save(produtos);
    }
}
