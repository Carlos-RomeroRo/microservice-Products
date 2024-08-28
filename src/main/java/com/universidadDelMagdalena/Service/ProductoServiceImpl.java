package com.universidadDelMagdalena.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidadDelMagdalena.Entities.Producto;
import com.universidadDelMagdalena.Mappers.ProductoMapper;
import com.universidadDelMagdalena.Repositories.ProductoRepository;
import com.universidadDelMagdalena.dto.ProductoDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ProductoDTO getProductById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("La ID no puede ser nula.");
            }
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el producto con la ID: " + id));
            return ProductoMapper.INSTANCE.productToProductDTO(producto);
        } catch (Exception e) {
            throw new EntityNotFoundException("Ha ocurrido un error al obtener el producto con la ID: " + id);
        }
    }

    @Override
    public ProductoDTO saveProduct(ProductoDTO productoDTO) {
        try {
            if (productoDTO == null) {
                throw new IllegalArgumentException("El producto no puede ser nulo");
            }
            Producto productoDB = ProductoMapper.INSTANCE.productDTOToProduct(productoDTO);
            productoRepository.save(productoDB);
            return ProductoMapper.INSTANCE.productToProductDTO(productoDB);

        } catch (Exception e) {
            throw new EntityNotFoundException("Ha ocurrido un error al insertar el producto");
        }

    }

    @Override
    public ProductoDTO updateProduct(Long id, ProductoDTO detallesProducto) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("La ID no puede ser nula.");
            }
            if (detallesProducto == null) {
                throw new IllegalArgumentException("Los detalles del producto no pueden ser nulos.");
            }
            if (!productoRepository.existsById(id)) {
                throw new EntityNotFoundException("No se encontro el producto con la ID: " + id);
            }

            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el producto con la ID: " + id));
            producto.setName(detallesProducto.getName());
            producto.setDescription(detallesProducto.getDescription());
            producto.setPrice(detallesProducto.getPrice());
            producto.setStock(detallesProducto.getStock());
            Producto productoFromDB = productoRepository.save(producto);
            return ProductoMapper.INSTANCE.productToProductDTO(productoFromDB);

        } catch (Exception e) {
            throw new RuntimeException("Ha ocurrido un error al actualizar el producto con la ID: " + id);
        }
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("La ID no puede ser nula.");
            }
            if (!productoRepository.existsById(id)) {
                throw new EntityNotFoundException("No se encontro el producto con la ID: " + id);
            }
            productoRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("Ha ocurrido un error al eliminar el producto con la ID: " + id);
        }

    }

}
