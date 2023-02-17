package com.fernandoalmanza.resolucionejercicio4ob.controller;

import com.fernandoalmanza.resolucionejercicio4ob.entities.Laptop;
import com.fernandoalmanza.resolucionejercicio4ob.repository.LaptopRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {

    LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @PostMapping("/api/laptops")
    public Laptop save(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }
}
