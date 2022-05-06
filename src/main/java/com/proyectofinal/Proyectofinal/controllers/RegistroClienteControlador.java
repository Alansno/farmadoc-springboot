package com.proyectofinal.Proyectofinal.controllers;

import com.proyectofinal.Proyectofinal.controllers.dto.ClienteRegistroDto;
import com.proyectofinal.Proyectofinal.servicio.ClienteServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registrate")
public class RegistroClienteControlador {

    private ClienteServicio clienteServicio;

    public RegistroClienteControlador(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @ModelAttribute("clientes")
    public ClienteRegistroDto retornarNuevoClienteRegistroDTO() {
        return new ClienteRegistroDto();
    }

    @GetMapping
    public String mostrarFormularioDeRegistro() {
        return "registrate";
    }

    @PostMapping
    public String registrarCuentaDelCliente(@ModelAttribute("clientes") ClienteRegistroDto registroDto) {
        clienteServicio.save(registroDto);
        return "redirect:/registrate?exito";
    }
}
