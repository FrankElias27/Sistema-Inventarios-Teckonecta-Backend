package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Enums.Estado;
import com.example.aos.sistema_aos_spring_boot.Enums.TipoMovimiento;
import com.example.aos.sistema_aos_spring_boot.Exceptions.CompraNoEncontradaException;
import com.example.aos.sistema_aos_spring_boot.Exceptions.DetallesCompraVaciosException;
import com.example.aos.sistema_aos_spring_boot.Exceptions.InventarioNoEncontradoException;
import com.example.aos.sistema_aos_spring_boot.Exceptions.ProductoNoEncontradoException;
import com.example.aos.sistema_aos_spring_boot.Modelo.*;
import com.example.aos.sistema_aos_spring_boot.Repositorios.*;
import com.example.aos.sistema_aos_spring_boot.Servicios.ComprasService;
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
public class ComprasServiceImpl implements ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private InventarioStockRepository inventarioStockRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private KardexRepository kardexRepository;

    @Override
    public Page<Compras> findAll(Pageable pageable) {
        return comprasRepository.findAll(pageable);
    }

    @Override
    public Compras agregarCompra(Compras compras) {
        return comprasRepository.save(compras);
    }

    @Override
    public Compras actualizarCompra(Compras compras) {
        // Verificar si el ID de la compra es válido y si la compra existe en la base de datos
        if (compras.getCompraId() == null || !comprasRepository.existsById(compras.getCompraId())) {
            throw new EntityNotFoundException("La compra con ID " + compras.getCompraId() + " no existe.");
        }
        // Si la compra existe, proceder con la actualización
        return comprasRepository.save(compras);
    }

    @Override
    public Set<Compras> obtenerCompras() {
        return new LinkedHashSet<>(comprasRepository.findAll());
    }

    @Override
    public Compras obtenerCompra(Long comprasId) {
        return comprasRepository.findById(comprasId).get();
    }

    @Override
    public void eliminarCompra(Long comprasId) {
        if (comprasRepository.existsById(comprasId)) {
            comprasRepository.deleteById(comprasId);
        } else {
            throw new EntityNotFoundException("La compra con ID " + comprasId + " no existe.");
        }
    }

    @Transactional
    @Override
    public void procesarCompra(Long compraId, Long inventarioId) {
        try {
            // Obtener los detalles de la compra
            List<DetalleCompra> detallesCompra = detalleCompraRepository.findByCompraCompraId(compraId);

            if (detallesCompra.isEmpty()) {
                throw new DetallesCompraVaciosException("No se encontraron detalles de compra para la compra con ID: " + compraId);
            }

            // Variables para el cálculo del total de productos y total a pagar
            double numTotalProductos = 0;
            double totalAPagar = 0;

            for (DetalleCompra detalle : detallesCompra) {
                // Buscar el producto en stock inventario para el inventario específico
                InventarioStock stockInventario = inventarioStockRepository
                        .findByProductoProductoIdAndInventarioInventarioId(detalle.getProducto().getProductoId(), inventarioId);

                if (stockInventario != null) {
                    // Si el producto existe en el inventario, actualizamos el stock
                    stockInventario.setCantidad(stockInventario.getCantidad() + detalle.getCantidad());
                    inventarioStockRepository.save(stockInventario);
                } else {
                    // Obtener el objeto Inventario a partir del inventarioId
                    Inventario inventario = inventarioRepository.findById(inventarioId)
                            .orElseThrow(() -> new InventarioNoEncontradoException("Inventario no encontrado para el ID: " + inventarioId));

                    // Si no existe, debemos crear un nuevo registro de stock para este producto
                    stockInventario = new InventarioStock();
                    stockInventario.setProducto(detalle.getProducto());
                    stockInventario.setInventario(inventario);
                    stockInventario.setCantidad(detalle.getCantidad());
                    inventarioStockRepository.save(stockInventario);
                }

                // Generar el registro en el kardex (registro de entrada)
                Kardex kardex = new Kardex();
                kardex.setProducto(detalle.getProducto());
                // Obtener el objeto Inventario a partir del inventarioId
                Inventario inventario = inventarioRepository.findById(inventarioId)
                        .orElseThrow(() -> new InventarioNoEncontradoException("Inventario no encontrado para el ID: " + inventarioId));

                kardex.setInventario(inventario);
                kardex.setDetalleCompra(detalle);
                kardex.setFechaMovimiento(LocalDateTime.now());

                TipoMovimiento tipoMovimiento = TipoMovimiento.ENTRADA;  // Asegúrate de que 'ENTRADA' sea parte de tu enum 'TipoMovimiento'
                kardex.setMovimiento(tipoMovimiento);

                kardex.setCantidad(detalle.getCantidad());
                kardex.setCostoUnitario(detalle.getCostoUnitarioSoles());
                kardex.setTotal(detalle.getCantidad() * detalle.getCostoUnitarioSoles());
                kardexRepository.save(kardex);

                // Actualizar el total de productos y total a pagar
                numTotalProductos += detalle.getCantidad();
                totalAPagar += detalle.getSubtotal();
            }

            // Actualizar la compra con los valores calculados
            Compras compra = comprasRepository.findById(compraId)
                    .orElseThrow(() -> new CompraNoEncontradaException("Compra no encontrada para el ID: " + compraId));

            compra.setNumTotalProductos(numTotalProductos);
            compra.setTotalaPagar(totalAPagar);
            Estado estado = Estado.PROCESADO;
            compra.setEstado(estado); // Cambiar estado a PROCESADO

            comprasRepository.save(compra);

        } catch (DetallesCompraVaciosException | InventarioNoEncontradoException | ProductoNoEncontradoException e) {
            // Manejo de excepciones específicas
            throw e;  // Re-throwing to allow higher layers (controller) to handle the exception
        } catch (Exception e) {
            // Excepción general para capturar cualquier otro error inesperado
            throw new RuntimeException("Error desconocido al procesar la compra: " + e.getMessage(), e);
        }
    }


}
