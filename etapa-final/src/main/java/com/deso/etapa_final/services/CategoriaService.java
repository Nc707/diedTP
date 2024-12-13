package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Categoria;
import com.deso.etapa_final.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria addCategoria(String nombre, Categoria.TipoCategoria tipo) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre.toLowerCase());
        categoria.setTipo(tipo);
        return categoriaRepository.save(categoria);
    }

    public Iterable<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria updateCategoria(Long id, String nombre, Categoria.TipoCategoria tipo) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(nombre);
            categoria.setTipo(tipo);
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria getCategoriaByNombre(String nombre, Categoria.TipoCategoria tipo) {
        Iterable<Categoria> categorias = categoriaRepository.findAll();
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        return addCategoria(nombre, tipo);
    }

    public void deleteCategoriaByNombre(String nombre, Categoria.TipoCategoria tipo) {
        Categoria categoria = getCategoriaByNombre(nombre, tipo);
        categoriaRepository.delete(categoria);
    }
}
