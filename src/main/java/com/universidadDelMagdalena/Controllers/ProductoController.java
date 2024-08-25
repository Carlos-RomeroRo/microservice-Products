package com.universidadDelMagdalena.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.universidadDelMagdalena.Entities.Producto;
import com.universidadDelMagdalena.Service.ProductoService;

@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) { // debo especificar el parametro (ya
                                                                           // funciona)

        if (productoService.getProductById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.getProductById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ID no fue encontrada: " + id);
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Producto producto) { // ya funciona
        try {
            productoService.saveProduct(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("El producto ha sido guardado.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El producto con ese ID ya existe.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProductById(@PathVariable("id") Long id, @RequestBody Producto detallesproducto) { // funciona
                                                                                                                   // //
                                                                                                                   // //
        // FUNCIONA
        Optional<Producto> optionalProducto = productoService.getProductById(id);
        if (optionalProducto.isPresent()) {
            productoService.updateProduct(id, detallesproducto);
            return ResponseEntity.status(HttpStatus.OK).body("Se ha podido actualizar el producto con la ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ID no fue encontrada: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") long id) { // ya funciona (202)
        Optional<Producto> producto = productoService.getProductById(id);
        if (producto.isPresent()) {
            productoService.deleteProductById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Se ha podido eliminar el producto con la ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ID no fue encontrada: " + id);
    }

}
