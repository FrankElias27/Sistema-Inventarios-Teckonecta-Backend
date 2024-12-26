package com.example.aos.sistema_aos_spring_boot.Servicios.impl;

import com.example.aos.sistema_aos_spring_boot.Modelo.Categoria;
import com.example.aos.sistema_aos_spring_boot.Repositorios.CategoriaRepository;
import com.example.aos.sistema_aos_spring_boot.Servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoriaServiceImpl  implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        // Verificar si el ID de la categoría es válido y si la categoría existe en la base de datos
        if (categoria.getCategoriaId() == null || !categoriaRepository.existsById(categoria.getCategoriaId())) {
            throw new EntityNotFoundException("La categoría con ID " + categoria.getCategoriaId() + " no existe.");
        }
        // Si la categoría existe, proceder con la actualización
        return categoriaRepository.save(categoria);
    }

    @Override
    public Set<Categoria> obtenerCategorias() {
        return new LinkedHashSet<>(categoriaRepository.findAll());
    }

    @Override
    public Categoria obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId).get();
    }

    @Override
    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    @Override
    public void eliminarCategoria(Long categoriaId) {
        if (categoriaRepository.existsById(categoriaId)) {
            categoriaRepository.deleteById(categoriaId);
        } else {
            throw new EntityNotFoundException("La categoría con ID " + categoriaId + " no existe.");
        }
    }
}
