package com.universidadDelMagdalena.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidadDelMagdalena.Entities.Producto;
import com.universidadDelMagdalena.Repositories.ProductoRepository;



@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public Optional<Producto> getProductById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto saveProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProduct(Long id, Producto detallesProducto) {
            Optional<Producto> optionalProducto = productoRepository.findById(id); 
            Producto producto = optionalProducto.get(); // descomprime para obtener cada elemento del OPTIONAL
            producto.setName(detallesProducto.getName());
            producto.setDescription(detallesProducto.getDescription());
            producto.setPrice(detallesProducto.getPrice());
            producto.setStock(detallesProducto.getStock());
            return productoRepository.save(producto);
    }

    @Override
    public void deleteProductById(Long id) {
        productoRepository.deleteById(id);
    }

    
}
