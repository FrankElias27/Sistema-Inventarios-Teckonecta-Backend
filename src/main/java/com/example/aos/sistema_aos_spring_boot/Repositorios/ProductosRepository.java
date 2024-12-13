package com.example.aos.sistema_aos_spring_boot.Repositorios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Modelo.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductosRepository extends JpaRepository<Productos,Long> {

    List<Productos> findByCategoria(Categoria categoria);

    List<Productos> findByActivo(Boolean estado);

    List<Productos> findByCategoriaAndActivo(Categoria categoria,Boolean estado);

    Page<Productos> findByNombreContaining(String nombre, Pageable pageable);

    List<Productos> findByNombreContainingIgnoreCase(String nombre);


}