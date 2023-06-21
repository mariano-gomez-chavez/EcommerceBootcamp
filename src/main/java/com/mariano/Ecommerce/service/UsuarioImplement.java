package com.mariano.Ecommerce.service;


import com.mariano.Ecommerce.model.Usuario;
import com.mariano.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioImplement implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }
}

