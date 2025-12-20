package com.supermarket.ApiRestSupermaket.service;

import com.supermarket.ApiRestSupermaket.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {
    List<SucursalDTO> listarSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDTO);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO);
    void eliminarSucursal(Long id);


}
