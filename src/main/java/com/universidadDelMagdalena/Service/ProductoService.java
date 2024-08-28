package com.universidadDelMagdalena.Service;

import com.universidadDelMagdalena.dto.ProductoDTO;


public interface ProductoService {
    ProductoDTO getProductById(Long id);
    ProductoDTO saveProduct(ProductoDTO productoDTO);
    ProductoDTO updateProduct(Long id, ProductoDTO detallesProducto);
    void deleteProductById(Long id);
}
