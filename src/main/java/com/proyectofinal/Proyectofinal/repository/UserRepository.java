package com.proyectofinal.Proyectofinal.repository;

import com.proyectofinal.Proyectofinal.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Clientes, Long> {


    public Clientes findByEmail(String email);

}
