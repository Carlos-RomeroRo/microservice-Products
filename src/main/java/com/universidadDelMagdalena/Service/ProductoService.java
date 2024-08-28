package com.universidadDelMagdalena.Service;

import java.util.Optional;

import com.universidadDelMagdalena.Entities.Producto;


public interface ProductoService {
    Optional<Producto> getProductById(Long id);
    Producto saveProduct(Producto producto);
    Producto updateProduct(Long id, Producto detallesProducto);
    void deleteProductById(Long id);
}
