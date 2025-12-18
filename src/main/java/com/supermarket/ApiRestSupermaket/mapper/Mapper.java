package com.supermarket.ApiRestSupermaket.mapper;

import com.supermarket.ApiRestSupermaket.dto.DetalleVentaDTO;
import com.supermarket.ApiRestSupermaket.dto.ProductoDTO;
import com.supermarket.ApiRestSupermaket.dto.SucursalDTO;
import com.supermarket.ApiRestSupermaket.dto.VentaDTO;
import com.supermarket.ApiRestSupermaket.model.Producto;
import com.supermarket.ApiRestSupermaket.model.Sucursal;
import com.supermarket.ApiRestSupermaket.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de producto
        public static ProductoDTO toDTO(Producto producto){
            if(producto == null){return null;}

            return ProductoDTO.builder()
                    .id(producto.getId())
                    .nombre(producto.getNombre())
                    .categoria(producto.getCategoria())
                    .precio(producto.getPrecio())
                    .cantidad(producto.getCantidad())
                    .build();
        }
    //Mapeo de Sucursal

    public static SucursalDTO toDTO(Sucursal sucursal){
            if(sucursal == null){return null;}

            return SucursalDTO.builder()
                    .id(sucursal.getId())
                    .nombre(sucursal.getNombre())
                    .direccion(sucursal.getDireccion())
                    .build();
    }

    //Mapeo de venta

    public static VentaDTO toDTO(Venta venta){
            if(venta == null){return null;}

            var detalle = venta.getDetalle().stream().map(
                    det ->
                            DetalleVentaDTO.builder()
                                    .id(det.getProd().getId())
                                    .nombreProd(det.getProd().getNombre())
                                    .cantProd(det.getProd().getCantidad())
                                    .precio(det.getProd().getPrecio())
                                    .subTotal(det.getPrecio() * det.getCantProd())
                                            .build()
            ).collect(Collectors.toList());

            var total = detalle.stream().map(DetalleVentaDTO::getSubTotal).
                    reduce(0.0, Double::sum);

            return VentaDTO.builder()
                    .id(venta.getId())
                    .fecha(venta.getFecha())
                    .idSucursal(venta.getSucursal().getId())
                    .estado(venta.getEstado())
                    .detalle(detalle)
                    .total(total)
                    .build();
    }

}
