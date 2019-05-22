package com.api.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutosResources {
    
    @RequestMapping(value="/produtos",method = RequestMethod.GET)
    public String listarAll() {
        
        return "mesa,cadeira";
    }
}
