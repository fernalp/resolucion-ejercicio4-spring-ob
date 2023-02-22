package com.fernandoalmanza.resolucionejercicio4ob.controller;

import com.fernandoalmanza.resolucionejercicio4ob.entities.Laptop;
import com.fernandoalmanza.resolucionejercicio4ob.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public ResponseEntity<List<Laptop>> findAll(){
        List<Laptop> result = laptopRepository.findAll();
        if (result.size()>0){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable("id") String serial){
        Optional<Laptop> optionalLaptop = laptopRepository.findById(serial);
        if (optionalLaptop.isPresent()){
            return ResponseEntity.ok(optionalLaptop.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getSerial() != null){
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getSerial() == null){
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getSerial())){
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable("id") String serial){
        if (!laptopRepository.existsById(serial)){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(serial);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Laptop> deleteAll(){
        if (laptopRepository.count() > 0){
            laptopRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
