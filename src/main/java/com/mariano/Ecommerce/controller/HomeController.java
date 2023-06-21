package com.mariano.Ecommerce.controller;

import com.mariano.Ecommerce.model.DetalleOrden;
import com.mariano.Ecommerce.model.Orden;
import com.mariano.Ecommerce.model.Producto;
import com.mariano.Ecommerce.model.Usuario;
import com.mariano.Ecommerce.service.DetalleOrdenService;
import com.mariano.Ecommerce.service.OrdenService;
import com.mariano.Ecommerce.service.ProductoService;
import com.mariano.Ecommerce.service.UsuarioService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductoService productoService;

    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleOrdenService;
    @Autowired
    private UsuarioService usuarioService;
    List<DetalleOrden> detalles = new ArrayList<>();
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "/home";
    }

    @GetMapping("producto/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        Producto producto;
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "pages/itemdetail";
    }

    @GetMapping("/carrito")
    public String getCart() {

        return "pages/cart";
    }

    @PostMapping("/carrito")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto;
        double sumaTotal;
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        Integer idProducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p -> Objects.equals(p.getProducto().getId(), idProducto));
        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        return "pages/cart";
    }

    @GetMapping("/orden")
    public String orderDetail(Model model) {

        return "pages/orderdetail";
    }

    @GetMapping("/guardarorden")
    public String orderdetail() {
        return "redirect:/";
    }

}
