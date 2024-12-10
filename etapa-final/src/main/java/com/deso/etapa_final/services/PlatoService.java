package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.model.Categoria;
import com.deso.etapa_final.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Plato createPlato(String nombre, String descripcion, float precio, float peso, float tamaño, Iterable<String> categorias) {
        Plato plato = new Plato();
        plato.setNombre(nombre);
        plato.setDescripcion(descripcion);
        plato.setPrecio(precio);
        plato.setPeso(peso);
        if (categorias != null) {
            plato.addCategoria(categoriaService.getCategoriaByNombre("Plato", Categoria.TipoCategoria.PLATO));
            for (String cat : categorias) {
                plato.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.PLATO));
            }
        }
        return platoRepository.save(plato);
    }

    public List<Plato> getAllPlatos() {
        return (List<Plato>) platoRepository.findAll();
    }

    public Plato getPlatoById(Long id) {
        return platoRepository.findById(id).orElse(null);
    }

    public Plato updatePlato(Long id, String nombre, String descripcion, float precio, float peso, float tamaño, Iterable<String> categorias) {
        Plato plato = platoRepository.findById(id).orElse(null);
        if (plato != null) {
            plato.setNombre(nombre);
            plato.setDescripcion(descripcion);
            plato.setPrecio(precio);
            plato.setPeso(peso);
            if (categorias != null) {
                plato.addCategoria(categoriaService.getCategoriaByNombre("Plato", Categoria.TipoCategoria.PLATO));
                for (String cat : categorias) {
                    plato.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.PLATO));
                }
            }
            return platoRepository.save(plato);
        }
        return null;
    }

    public void deletePlato(Long id) {
        platoRepository.deleteById(id);
    }
}