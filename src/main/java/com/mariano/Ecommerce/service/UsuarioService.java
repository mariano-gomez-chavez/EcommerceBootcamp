package com.mariano.Ecommerce.service;



import com.mariano.Ecommerce.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findById(Integer id);
}
