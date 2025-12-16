package com.supermarket.ApiRestSupermaket.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private LocalDate fecha; 
	private String estado;
	
	@ManyToOne
	private Sucursal sucursal;

	@OneToMany (mappedBy = "venta")
	private List<DetalleVenta> detalle = new ArrayList<>();
	
	
}
