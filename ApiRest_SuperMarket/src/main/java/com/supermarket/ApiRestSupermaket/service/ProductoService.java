package com.supermarket.ApiRestSupermaket.service;

import com.supermarket.ApiRestSupermaket.dto.ProductoDTO;
import com.supermarket.ApiRestSupermaket.exception.NotFoundException;
import com.supermarket.ApiRestSupermaket.mapper.Mapper;
import com.supermarket.ApiRestSupermaket.model.Producto;
import com.supermarket.ApiRestSupermaket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> listarProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {

        Producto prod = Producto.builder().nombre(productoDTO.getNombre())
                .precio(productoDTO.getPrecio())
                .categoria(productoDTO.getCategoria())
                .cantidad(productoDTO.getCantidad())
                .build();

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        //VERIFICAR EXISTENCIA DEL PRODUCTO
        Producto prod = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no existe"));

        prod.setNombre(productoDTO.getNombre());
        prod.setPrecio(productoDTO.getPrecio());
        prod.setCategoria(productoDTO.getCategoria());
        prod.setCantidad(productoDTO.getCantidad());

        return Mapper.toDTO(repo.save(prod));

    }

    @Override
    public void eliminarProducto(Long id) {
            if(!repo.existsById(id)){
                throw new NotFoundException("El producto no existe");
            }
            repo.deleteById(id);
    }
}
