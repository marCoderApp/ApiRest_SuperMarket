package com.supermarket.ApiRestSupermaket.service;

import com.supermarket.ApiRestSupermaket.dto.SucursalDTO;
import com.supermarket.ApiRestSupermaket.exception.NotFoundException;
import com.supermarket.ApiRestSupermaket.mapper.Mapper;
import com.supermarket.ApiRestSupermaket.model.Producto;
import com.supermarket.ApiRestSupermaket.model.Sucursal;
import com.supermarket.ApiRestSupermaket.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> listarSucursales() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {

        Sucursal sucursal = Sucursal.builder()
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();


        return Mapper.toDTO(repo.save(sucursal));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {

        Sucursal sucursal = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("La sucursal no existe"));

        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.getDireccion());

        return Mapper.toDTO(repo.save(sucursal));
    }

    @Override
    public void eliminarSucursal(Long id) {

        if(!repo.existsById(id)){
            throw new NotFoundException("La sucursal no existe");
        }

        repo.deleteById(id);

    }
}
