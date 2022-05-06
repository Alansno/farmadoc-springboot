package com.proyectofinal.Proyectofinal.controllers;

import com.proyectofinal.Proyectofinal.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistroControlador {

    @Autowired
    private ClienteServicio servicio;

    @GetMapping("/login")
    public String iniciarSesion() {
        return "index";
    }

    @GetMapping("/farmadoc")
    public String verPaginaDeInicio() {
        return "index2";
    }

    @GetMapping("/consultorios")
    public String verConsultorios() {
        return "index3";
    }

    @GetMapping("/farmacias")
    public String verFarmacia() {
        return "index4";
    }

    @GetMapping("/usuarios/admin")
    @PreAuthorize("hasAnyRole('admin')")
    public String verUsuarios(Model model) {
        model.addAttribute("clientes", servicio.listarClientes());
        return "index5";
    }

}
