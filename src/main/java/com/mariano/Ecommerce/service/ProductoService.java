package com.mariano.Ecommerce.service;

import com.mariano.Ecommerce.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public Producto create(Producto producto); //esto sirve para crear un producto
    public Optional<Producto> get(Integer id);//esto sirve para obtener un producto
    public void update(Producto producto);//esto sirve para actualizar un producto
    public void delete(Integer id);//esto sirve para eliminar un producto
    public List<Producto> findAll();//nos muestra toda la lista de productos
}
