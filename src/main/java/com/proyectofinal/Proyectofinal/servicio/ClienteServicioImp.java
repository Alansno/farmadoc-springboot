package com.proyectofinal.Proyectofinal.servicio;

import com.proyectofinal.Proyectofinal.controllers.dto.ClienteRegistroDto;
import com.proyectofinal.Proyectofinal.models.Clientes;
import com.proyectofinal.Proyectofinal.models.Rol;
import com.proyectofinal.Proyectofinal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServicioImp implements ClienteServicio{


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private UserRepository userRepository;

    public ClienteServicioImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Clientes save(ClienteRegistroDto registroDto) {
        Clientes clientes = new Clientes(registroDto.getNombre(), registroDto.getApellido(), registroDto.getEmail(), passwordEncoder.encode(registroDto.getPassword()),
                Arrays.asList(new Rol("ROLE_USER")));
        return userRepository.save(clientes);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Clientes clientes = userRepository.findByEmail(username);
        if (clientes == null) {
            throw new UsernameNotFoundException("Campos invalidos");
        }
        return new User(clientes.getEmail(), clientes.getPassword(), mapearAutoridadesRoles(clientes.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    @Override
    public List<Clientes> listarClientes() {
        return userRepository.findAll();
    }


}
