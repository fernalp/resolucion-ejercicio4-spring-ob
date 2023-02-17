package com.fernandoalmanza.resolucionejercicio4ob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String saludo(){
        return "Hola, me place saludarte";
    }
}
