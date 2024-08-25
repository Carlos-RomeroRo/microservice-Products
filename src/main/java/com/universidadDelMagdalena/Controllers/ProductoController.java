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
import com.universidadDelMagdalena.Repositories.ProductoRepository;

@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) { // debo especificar el parametro (ya
                                                                           // funciona)
        if (productoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(productoRepository.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ID no fue encontrada: " + id);
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody Producto producto) { // ya funciona
        try {
            Producto savedProduct = productoRepository.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El producto con ese ID ya existe.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProductById(@PathVariable("id") Long id, @RequestBody Producto detallesproducto) { // YA
                                                                                                                   // FUNCIONA
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get(); // descomprime para obtener cada elemento del OPTIONAL
            producto.setName(detallesproducto.getName());
            producto.setDescription(detallesproducto.getDescription());
            producto.setPrice(detallesproducto.getPrice());
            producto.setStock(detallesproducto.getStock());
            return ResponseEntity.status(HttpStatus.OK).body(productoRepository.save(producto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ID no fue encontrada: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") long id) { // ya funciona
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            productoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Se ha podido eliminar el producto con la ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La ID no fue encontrada: " + id);
    }

}
