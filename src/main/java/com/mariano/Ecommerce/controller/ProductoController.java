package com.mariano.Ecommerce.controller;


import com.mariano.Ecommerce.model.Producto;
import com.mariano.Ecommerce.model.Usuario;
import com.mariano.Ecommerce.service.ProductoService;
import com.mariano.Ecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("administrador/productos")
public class ProductoController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("")
    public String read(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "pages/itemread";
    }

    @GetMapping("/create")
    public String create() {
        return "pages/itemcreate";
    }

    @PostMapping("/update")//PostMapping sirve para enviar datos a la base de datos
    public String update(Producto producto, @RequestParam("img") MultipartFile file) {//MultiparFile sirve para subir archivos img
        Producto productoConImage;
        productoConImage = productoService.get(producto.getId()).get();//get sirve para obtener datos de la base de datos
        if(file.isEmpty()){//isEmpty sirve para verificar si el archivo esta vacio
            producto.setImagen(productoConImage.getImagen());//sirve para obtener la imagen
        }else{
            if(!productoConImage.getImagen().equals("default.png")){ //si la imagen es diferente a default.png se elimina la imagen
                uploadFileService.deleteImage(productoConImage.getImagen());
            }
            String imagename = uploadFileService.saveImage(file);//sirve para guardar la imagen
            producto.setImagen(imagename);
        }
        LOGGER.info("Producto: {}", producto);
        productoService.update(producto);
        return "redirect:/administrador/productos";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Producto producto;
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Producto: {}", producto);
        model.addAttribute("producto", producto);
        return "pages/itemupdate";
    }

    @GetMapping("/delete/{id}") //GetMapping sirve para obtener datos de la base de datos
    public String delete(@PathVariable Integer id) {
        Producto producto;
        producto = productoService.get(id).get();
        if(!producto.getImagen().equals("default.png")){
            uploadFileService.deleteImage(producto.getImagen());
        }
        productoService.delete(id);
        return "redirect:/administrador/productos";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file) {
        LOGGER.info("producto: {}" + producto);
        Usuario usuario = new Usuario(10003, "Mariano", "Mariano97", "mariano27897@gmail.com", "Sala 444 4B", "12345678", "ADMIN", "123456");
        producto.setUsuario(usuario);
        if(producto.getId()==null){
            String imagename = uploadFileService.saveImage(file);
            producto.setImagen(imagename);
        }
        productoService.create(producto);
        return "redirect:/administrador/productos";
    }
}

