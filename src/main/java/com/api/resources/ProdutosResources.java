package com.api.resources;

import com.api.domain.Produtos;
import com.api.services.ProdutosService;
import com.api.services.exceptions.ProdutoNaoEncontradoException;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/produtos")
public class ProdutosResources {
    
    @Autowired
    private ProdutosService  produtosService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produtos>> listarAll() {
        
        return ResponseEntity.status(HttpStatus.OK).body(produtosService.listarAll());
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?>  buscar(@PathVariable("id") Long id) {
        
        Produtos produtos = null;
       
        try {
              produtos =  produtosService.buscar(id);
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Produtos produtos) {
        
        produtos = produtosService.salvar(produtos);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produtos.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        
        try {
            
            produtosService.deletar(id);
        } catch (ProdutoNaoEncontradoException e) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.noContent().build();
    }
    
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Produtos produtos,@PathVariable("id") Long id) {
        
        produtos.setId(id);
        produtosRepository.save(produtos);
        
        return ResponseEntity.noContent().build();
    }
}
