package com.proyectofinal.Proyectofinal.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id")
    @Id
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    public Rol() {
    }

    public Rol(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }
}
