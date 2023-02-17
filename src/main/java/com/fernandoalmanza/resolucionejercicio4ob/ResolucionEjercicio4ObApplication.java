package com.fernandoalmanza.resolucionejercicio4ob;

import com.fernandoalmanza.resolucionejercicio4ob.entities.Laptop;
import com.fernandoalmanza.resolucionejercicio4ob.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ResolucionEjercicio4ObApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ResolucionEjercicio4ObApplication.class, args);

        LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
        Laptop laptop = new Laptop(null, "Dell","8","Intel");
        Laptop laptop2 = new Laptop(null, "HP","16","AMD");
        Laptop laptop3 = new Laptop(null, "Asus","4","Intel");

        laptopRepository.save(laptop);
        laptopRepository.save(laptop2);
        laptopRepository.save(laptop3);

        System.out.println(laptopRepository.count());
    }

}
