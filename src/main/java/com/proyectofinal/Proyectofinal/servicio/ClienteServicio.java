package com.proyectofinal.Proyectofinal.servicio;

import com.proyectofinal.Proyectofinal.controllers.dto.ClienteRegistroDto;
import com.proyectofinal.Proyectofinal.models.Clientes;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClienteServicio extends UserDetailsService {

    public Clientes save(ClienteRegistroDto registroDto);

    public List<Clientes> listarClientes();

}
