package com.example.aos.sistema_aos_spring_boot.Servicios;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface CategoriaService {

    Categoria agregarCategoria(Categoria categoria);

    Categoria actualizarCategoria(Categoria categoria);

    Set<Categoria> obtenerCategorias();

    Categoria obtenerCategoria(Long categoriaId);

    Page<Categoria> findAll(Pageable pageable);

    void eliminarCategoria(Long categoriaId);
}
