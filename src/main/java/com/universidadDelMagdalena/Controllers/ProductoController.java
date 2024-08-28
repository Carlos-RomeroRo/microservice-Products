package com.universidadDelMagdalena.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidadDelMagdalena.Service.ProductoService;
import com.universidadDelMagdalena.dto.ProductoDTO;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) { // debo especificar el parametro (ya
                                                                           // funciona)
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.getProductById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error inesperado.");
        }
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody ProductoDTO productoDTO) { // ya funciona
        try {
            productoService.saveProduct(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("El producto ha sido guardado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage()); // error 422
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProductById(@PathVariable("id") Long id, @RequestBody ProductoDTO detallesproducto) {
        try {
            productoService.updateProduct(id, detallesproducto);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha podido actualizar el producto con la ID: " + id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception EntityNotFoundException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityNotFoundException.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") long id) { // ya funciona (202)
        try {
            productoService.deleteProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha podido eliminar el producto con la ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
