package com.mariano.Ecommerce.repository;


import com.mariano.Ecommerce.model.Orden;
import com.mariano.Ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByUsuario (Usuario usuario);
}
