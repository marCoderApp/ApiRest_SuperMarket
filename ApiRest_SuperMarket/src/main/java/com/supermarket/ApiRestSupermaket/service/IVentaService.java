package com.supermarket.ApiRestSupermaket.service;

import com.supermarket.ApiRestSupermaket.dto.VentaDTO;

import java.util.List;

public interface IVentaService {
    List<VentaDTO> listarVentas();
    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);
    void eliminarVenta(Long id);
}
