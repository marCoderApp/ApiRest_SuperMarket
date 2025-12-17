package com.supermarket.ApiRestSupermaket.service;

import com.supermarket.ApiRestSupermaket.dto.DetalleVentaDTO;
import com.supermarket.ApiRestSupermaket.dto.VentaDTO;
import com.supermarket.ApiRestSupermaket.exception.NotFoundException;
import com.supermarket.ApiRestSupermaket.mapper.Mapper;
import com.supermarket.ApiRestSupermaket.model.DetalleVenta;
import com.supermarket.ApiRestSupermaket.model.Producto;
import com.supermarket.ApiRestSupermaket.model.Sucursal;
import com.supermarket.ApiRestSupermaket.model.Venta;
import com.supermarket.ApiRestSupermaket.repository.ProductoRepository;
import com.supermarket.ApiRestSupermaket.repository.SucursalRepository;
import com.supermarket.ApiRestSupermaket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private SucursalService sucursalService;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<VentaDTO> listarVentas() {

        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();
        VentaDTO dto;

        for (Venta venta : ventas) {
            dto = Mapper.toDTO(venta);
            ventasDto.add(dto);
        }

        return ventasDto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {

        if (ventaDTO == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDTO.getIdSucursal() == null) throw new RuntimeException("No especificó la sucursal");
        if (ventaDTO.getDetalle() == null || ventaDTO.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto en la venta");

        //Buscar la sucursal por id
        Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElseThrow(null);

        if (sucursal == null) throw new NotFoundException("La sucursal no existe");

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDTO.getFecha());
        vent.setEstado(ventaDTO.getEstado());
        vent.setSucursal(sucursal);
        vent.setTotal(ventaDTO.getTotal());

        //Lista de detalles
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detDTO : ventaDTO.getDetalle()) {
            Producto prod = productoRepository.findByNombre(detDTO.getNombreProd()).orElseThrow(null);
            if (prod == null) throw new NotFoundException("El producto no existe:" + detDTO.getNombreProd()) ;

            //Crear detalle de venta
            DetalleVenta det = new DetalleVenta();
            det.setCantProd(detDTO.getCantProd());
            det.setPrecio(detDTO.getPrecio());
            det.setProd(prod);
            det.setVenta(vent);
            detalles.add(det);

            }

        //Seteamos la lista de detalles venta
        vent.setDetalle(detalles);
        ventaRepo.save(vent);

        return Mapper.toDTO(vent);
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepo.findById(id).orElseThrow(null);
        if (venta == null){
            throw new RuntimeException("Venta no encontrada");
        }else{
            if (ventaDTO.getFecha() != null){
                venta.setFecha(ventaDTO.getFecha());
            }

            if (ventaDTO.getEstado() != null){
                venta.setEstado(ventaDTO.getEstado());
            }

            if (ventaDTO.getTotal() != null){
                venta.setTotal(ventaDTO.getTotal());
            }

            if(ventaDTO.getIdSucursal() != null){
                Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElseThrow(null);
                if(sucursal != null){
                    venta.setSucursal(sucursal);
                }else{
                    throw new NotFoundException("La sucursal no existe");
                }
            }

            ventaRepo.save(venta);
        }


        return Mapper.toDTO(venta);
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = ventaRepo.findById(id).orElseThrow(null);
        if (venta == null){
            throw new RuntimeException("Venta no encontrada");
        }else{
            ventaRepo.deleteById(id);
        }
    }
}
