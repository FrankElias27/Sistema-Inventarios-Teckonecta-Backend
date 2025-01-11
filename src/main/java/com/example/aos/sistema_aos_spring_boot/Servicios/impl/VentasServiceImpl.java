package com.example.aos.sistema_aos_spring_boot.Servicios.impl;


import com.example.aos.sistema_aos_spring_boot.Enums.Estado;
import com.example.aos.sistema_aos_spring_boot.Enums.TipoMovimiento;
import com.example.aos.sistema_aos_spring_boot.Exceptions.*;
import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import com.example.aos.sistema_aos_spring_boot.Repositorios.*;
import com.example.aos.sistema_aos_spring_boot.Servicios.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class VentasServiceImpl implements VentasService {

    @Autowired
    private VentasRepository ventasRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    @Autowired
    private InventarioStockRepository inventarioStockRepository;
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private KardexRepository kardexRepository;

    @Override
    public Page<Ventas> findAll(Pageable pageable) {
        return ventasRepository.findAll(pageable);
    }

    @Override
    public Ventas agregarVenta(Ventas ventas) {
        return ventasRepository.save(ventas);
    }

    @Override
    public Ventas actualizarVenta(Ventas ventas) {
        // Verificar si el ID de la venta es válido y si la venta existe en la base de datos
        if (ventas.getVentaId() == null || !ventasRepository.existsById(ventas.getVentaId())) {
            throw new EntityNotFoundException("La venta con ID " + ventas.getVentaId() + " no existe.");
        }
        // Si la venta existe, proceder con la actualización
        return ventasRepository.save(ventas);
    }

    @Override
    public Set<Ventas> obtenerVentas() {
        return new LinkedHashSet<>(ventasRepository.findAll());
    }

    @Override
    public Ventas obtenerVenta(Long ventasId) {
        return ventasRepository.findById(ventasId).get();
    }

    @Override
    public void eliminarVenta(Long ventasId) {
        if (ventasRepository.existsById(ventasId)) {
            ventasRepository.deleteById(ventasId);
        } else {
            throw new EntityNotFoundException("La venta con ID " + ventasId + " no existe.");
        }
    }

    @Transactional
    @Override
    public void procesarVenta(Long ventaId, Long inventarioId) {
        try {
            // Obtener los detalles de la venta
            List<DetalleVenta> detallesVenta = detalleVentaRepository.findByVentaVentaId(ventaId);

            if (detallesVenta.isEmpty()) {
                throw new DetallesVentaVaciosException("No se encontraron detalles de venta para la venta con ID: " + ventaId);
            }

            // Variables para el cálculo del total de productos y total a pagar
            double numTotalProductos = 0;
            double totalAPagar = 0;

            for (DetalleVenta detalle : detallesVenta) {
                // Buscar el producto en stock inventario para el inventario específico
                InventarioStock stockInventario = inventarioStockRepository
                        .findByProductoProductoIdAndInventarioInventarioId(detalle.getProducto().getProductoId(), inventarioId);

                if (stockInventario != null) {
                    // Validar si el stock disponible es suficiente
                    if (stockInventario.getCantidad() < detalle.getCantidad()) {
                        throw new StockInsuficienteException(
                                "Stock insuficiente para el producto: " + detalle.getProducto().getNombre() +
                                        ". Disponible: " + stockInventario.getCantidad() +
                                        ", Solicitado: " + detalle.getCantidad()
                        );
                    }

                    // Si el producto existe en el inventario, actualizamos el stock
                    stockInventario.setCantidad(stockInventario.getCantidad() - detalle.getCantidad());
                    inventarioStockRepository.save(stockInventario);
                } else {
                    // Obtener el objeto Inventario a partir del inventarioId
                    Inventario inventario = inventarioRepository.findById(inventarioId)
                            .orElseThrow(() -> new InventarioNoEncontradoException("Inventario no encontrado para el ID: " + inventarioId));

                    // Lanzar excepción personalizada si el producto no está disponible en el inventario
                    throw new ProductoNoDisponibleEnInventarioException(
                            "El producto:" + detalle.getProducto().getNombre() +
                                    " no está disponible en el inventario con ID " + inventarioId);
                }

                // Generar el registro en el kardex (registro de entrada)
                Kardex kardex = new Kardex();
                kardex.setProducto(detalle.getProducto());
                // Obtener el objeto Inventario a partir del inventarioId
                Inventario inventario = inventarioRepository.findById(inventarioId)
                        .orElseThrow(() -> new InventarioNoEncontradoException("Inventario no encontrado para el ID: " + inventarioId));

                kardex.setInventario(inventario);
                kardex.setDetalleVenta(detalle);
                kardex.setFechaMovimiento(LocalDateTime.now());

                TipoMovimiento tipoMovimiento = TipoMovimiento.SALIDA;  // Asegúrate de que 'ENTRADA' sea parte de tu enum 'TipoMovimiento'
                kardex.setMovimiento(tipoMovimiento);

                kardex.setCantidad(detalle.getCantidad());
                kardex.setCostoUnitario(detalle.getCostoUnitario());
                kardex.setTotal(detalle.getCantidad() * detalle.getCostoUnitario());
                kardexRepository.save(kardex);

                // Actualizar el total de productos y total a pagar
                numTotalProductos += detalle.getCantidad();
                totalAPagar += detalle.getSubtotal();
            }

            // Actualizar la venta con los valores calculados
            Ventas venta = ventasRepository.findById(ventaId)
                    .orElseThrow(() -> new VentaNoEncontradaException("Venta no encontrada para el ID: " + ventaId));

            venta.setNumTotalProducto(numTotalProductos);
            venta.setTotalaPagar(totalAPagar);
            Estado estado = Estado.PROCESADO;
            venta.setEstado(estado); // Cambiar estado a PROCESADO

            ventasRepository.save(venta);

        } catch (DetallesVentaVaciosException | InventarioNoEncontradoException | ProductoNoDisponibleEnInventarioException | StockInsuficienteException e) {
            // Manejo de excepciones específicas
            throw e;  // Re-throwing to allow higher layers (controller) to handle the exception
        } catch (Exception e) {
            // Excepción general para capturar cualquier otro error inesperado
            throw new RuntimeException("Error desconocido al procesar la venta: " + e.getMessage(), e);
        }
    }


}
