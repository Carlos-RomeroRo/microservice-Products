package com.universidadDelMagdalena.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidadDelMagdalena.Entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
