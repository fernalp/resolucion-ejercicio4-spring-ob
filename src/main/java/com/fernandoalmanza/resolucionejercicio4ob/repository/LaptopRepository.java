package com.fernandoalmanza.resolucionejercicio4ob.repository;

import com.fernandoalmanza.resolucionejercicio4ob.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {
}
