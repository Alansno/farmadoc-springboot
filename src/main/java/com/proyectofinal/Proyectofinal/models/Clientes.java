package com.proyectofinal.Proyectofinal.models;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Clientes {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    @Id
    private Long id;

    @Getter @Setter @Column(name = "nombre")
    private String nombre;

    @Getter @Setter @Column(name = "apellido")
    private String apellido;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "clientes_roles", joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Collection<Rol> roles;

    public Clientes() {
    }

    public Clientes(String nombre, String apellido, String email, String password, Collection<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Clientes(Long id, String nombre, String apellido, String email, String password, Collection<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;


    }

    public Collection<Rol> getRoles() {
        return roles;
    }
}
