package com.mariano.Ecommerce.service;



import com.mariano.Ecommerce.model.DetalleOrden;
import com.mariano.Ecommerce.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenImplement implements DetalleOrdenService  {
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;//lo trae de DetalleOrdenRepository

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) { //lo implementa de DetalleOrdenService

        return detalleOrdenRepository.save(detalleOrden);
    }
}
