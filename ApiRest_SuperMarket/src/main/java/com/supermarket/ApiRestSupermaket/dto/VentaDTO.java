package com.supermarket.ApiRestSupermaket.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    //datos de venta
    private Long id;
    private LocalDate fecha;
    private String estado;
    //datos de sucursal
    private Long idSucursal;
    //lista de detalles de venta
    private List<DetalleVentaDTO> detalle;

    //total de la venta
    private Double total;

}
