package com.api.resources;

import com.api.domain.Produtos;
import com.api.repository.ProdutosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?>  buscar(@PathVariable("id") Long id) {
        
        Produtos produtos =  produtosRepository.getOne(id);
        if(produtos == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }
    @RequestMapping(method = RequestMethod.POST)
    public void salvar(@RequestBody Produtos produtos) {
        
        produtosRepository.save(produtos);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable("id") Long id) {
        
        produtosRepository.deleteById(id);
    }
    //parei na aula 2.9 da algarworks
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public void atualizar(@RequestBody Produtos produtos,@PathVariable("id") Long id) {
        
        produtos.setId(id);
        produtosRepository.save(produtos);
    }
}
