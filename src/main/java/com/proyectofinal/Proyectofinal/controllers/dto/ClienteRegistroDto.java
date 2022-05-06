package com.proyectofinal.Proyectofinal.controllers.dto;

import lombok.Getter;
import lombok.Setter;


public class ClienteRegistroDto {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombre;

    @Getter @Setter
    private String apellido;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    public ClienteRegistroDto(Long id, String nombre, String apellido, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public ClienteRegistroDto(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public ClienteRegistroDto(String email) {
        this.email = email;
    }

    public ClienteRegistroDto() {

    }
}
