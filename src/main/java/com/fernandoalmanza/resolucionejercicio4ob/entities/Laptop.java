package com.fernandoalmanza.resolucionejercicio4ob.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String serial;
    private String fabricante;
    private String ram;
    private String procesador;

    public Laptop() {
    }

    public Laptop(String serial, String fabricante, String ram, String procesador) {
        this.serial = serial;
        this.fabricante = fabricante;
        this.ram = ram;
        this.procesador = procesador;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "serial=" + serial +
                ", fabricante='" + fabricante + '\'' +
                ", ram='" + ram + '\'' +
                ", procesador='" + procesador + '\'' +
                '}';
    }
}
