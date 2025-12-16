package com.supermarket.ApiRestSupermaket.repository;

import com.supermarket.ApiRestSupermaket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
