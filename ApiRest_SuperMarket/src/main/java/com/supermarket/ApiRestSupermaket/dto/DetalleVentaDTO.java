package com.supermarket.ApiRestSupermaket.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {
        private Long id;
        private String nombreProd;
        private int cantProd;
        private Double precio;
        private Double subTotal;


}
