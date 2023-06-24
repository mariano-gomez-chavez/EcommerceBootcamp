package com.mariano.Ecommerce.service;

import com.mariano.Ecommerce.model.Orden;
import com.mariano.Ecommerce.model.Usuario;
import com.mariano.Ecommerce.repository.OrdenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenImplement implements OrdenService{
    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public List<Orden> findAll() {

        return ordenRepository.findAll(); //sirve para traer todos los datos de la tabla de orden
    }

    @Override
    public Orden save(Orden orden) {
       return ordenRepository.save(orden);//me devuelve el objeto orden

    }

    @Override
    public String generarNumeroOrden() {
        int numero = 0;
        String numeroConcatenado = "";
        List<Orden> ordenes = ordenRepository.findAll();
        List<Integer> numeros = new ArrayList<Integer>();
        ordenes.forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));//juntamos todos los numeros de ordenes en una lista

        return null;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }
}
