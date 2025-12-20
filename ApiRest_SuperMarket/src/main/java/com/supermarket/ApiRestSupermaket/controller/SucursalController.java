package com.supermarket.ApiRestSupermaket.controller;

import com.supermarket.ApiRestSupermaket.dto.SucursalDTO;
import com.supermarket.ApiRestSupermaket.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales(){
        return ResponseEntity.ok(sucursalService.listarSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO){
        SucursalDTO created = sucursalService.crearSucursal(sucursalDTO);
        return ResponseEntity.created(URI.create("/api/sucursales" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalDTO sucursalDTO){
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id, sucursalDTO));
    }

    @DeleteMapping("/{id}")
    public void eliminarSucursal(@PathVariable Long id){
        sucursalService.eliminarSucursal(id);
    }

}
