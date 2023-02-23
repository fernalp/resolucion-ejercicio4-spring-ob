package com.fernandoalmanza.resolucionejercicio4ob.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String saludo(){
        return "Hola, me place saludarte";
    }

    @GetMapping("/")
    public String home(){
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap demo</title>
                  </head>
                  <body>
                    <h1>Hello, world!</h1>
                  </body>
                </html>
                """;
    }
}
