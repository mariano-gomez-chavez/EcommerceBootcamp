package com.mariano.Ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadFileService {
    private final String FOLDER = "img\\";

    public String saveImage(MultipartFile file) {
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                java.nio.file.Path path = java.nio.file.Paths.get(FOLDER + file.getOriginalFilename());
                java.nio.file.Files.write(path, bytes);
                return file.getOriginalFilename();
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        }
        return "default.png";
    }

    public void deleteImage(String filename){
        try {
            String ruta = FOLDER + filename;
            File file = new File(ruta);
            if(file.exists()) { //si el archivo existe se elimina
                file.delete();
            }
        } catch (Exception e) { //si no existe se muestra un mensaje de error
            System.out.println("Error al eliminar la imagen: " + e.getMessage());
        }
    }
}
