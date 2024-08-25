package com.universidadDelMagdalena.dto;

import lombok.Data; // debe agregarse la libreria Lombok (versión especifica)

@Data
public class ProductoDTO {
    private long id;
    private String name;
    private String description;
    private String price;
    private Integer stock;
}
