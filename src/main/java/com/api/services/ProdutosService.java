package com.api.services;

import com.api.domain.Produtos;
import com.api.repository.ProdutosRepository;
import com.api.services.exceptions.ProdutoNaoEncontradoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {
    
    @Autowired
    private ProdutosRepository  produtosRepository;
    
    public List<Produtos> listarAll() {
     
        return produtosRepository.findAll();
    }
    
    public Produtos buscar(Long id) {
        
        Produtos produtos =  produtosRepository.getOne(id);
        
        if(produtos == null) throw new ProdutoNaoEncontradoException("Produto não pode ser encontrado");
            
        return produtos;
    }
    
    public Produtos salvar(Produtos produtos) {
        
        produtos.setId(null);
        
        return produtosRepository.save(produtos);
    }
    
    public void deletar(Long id) {
        try {
            
            produtosRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            
            throw new ProdutoNaoEncontradoException("Produto não pode ser encontrado");
        }
        
    }
    
    public void atualizar(Produtos produtos) {
        
        verificarExistencia(produtos.getId());
        produtosRepository.save(produtos);
    }
    
    private void verificarExistencia(Long id) {
        
        buscar(id);
    }
}
