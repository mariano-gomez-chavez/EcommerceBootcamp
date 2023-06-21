package com.mariano.Ecommerce.service;

import com.mariano.Ecommerce.model.Orden;
import com.mariano.Ecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface OrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save (Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (Usuario usuario);
}
