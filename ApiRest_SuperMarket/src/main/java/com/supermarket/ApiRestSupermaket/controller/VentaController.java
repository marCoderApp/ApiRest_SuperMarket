package com.supermarket.ApiRestSupermaket.controller;

import com.supermarket.ApiRestSupermaket.dto.VentaDTO;
import com.supermarket.ApiRestSupermaket.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas(){
        return  ResponseEntity.ok(ventaService.listarVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO){
        VentaDTO created = ventaService.crearVenta(ventaDTO);

        return ResponseEntity.created(URI.create("/api/ventas" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO){
        return ResponseEntity.ok(ventaService.actualizarVenta(id, ventaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
