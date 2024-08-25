package com.universidadDelMagdalena.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.universidadDelMagdalena.Entities.Producto;
import com.universidadDelMagdalena.dto.ProductoDTO;

@Mapper 
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);
    public ProductoDTO productToProductDTO(Producto product);
    public Producto productDTOToProduct(ProductoDTO productDTO);
} 
