package com.api.services.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{
    
    private static final long serialVersionUID = 186930055614629710L;
    
    public ProdutoNaoEncontradoException(String menssagem) {
        
        super(menssagem);
    }
    
    public ProdutoNaoEncontradoException(String menssagem, Throwable cause) {
        
        super(menssagem,cause);
    }
}
