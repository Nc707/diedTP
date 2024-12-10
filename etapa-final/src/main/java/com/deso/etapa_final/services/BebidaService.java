package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.repositories.BebidaRepository;
import com.deso.etapa_final.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Bebida createBebida(String nombre, String descripcion, float precio, float graduacionAlcoholica, float tamaño, float peso, Iterable<String> categoria) {
        Bebida bebida = new Bebida();
        bebida.setNombre(nombre);
        bebida.setDescripcion(descripcion);
        bebida.setPrecio(precio);
        bebida.setGraduacionAlcoholica(graduacionAlcoholica);
        bebida.setTamaño(tamaño);
        bebida.setPeso(peso);
        if(categoria != null) {
            bebida.addCategoria(categoriaService.getCategoriaByNombre("Bebida", Categoria.TipoCategoria.BEBIDA));
            for (String cat : categoria) {
                bebida.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.BEBIDA));
                
            }
        }
        return bebidaRepository.save(bebida);
    }

    public List<Bebida> getAllBebidas() {
        return (List<Bebida>) bebidaRepository.findAll();
    }

    public Bebida getBebidaById(Long id) {
        return bebidaRepository.findById(id).orElse(null);
    }

    public Bebida updateBebida(Long id, String nombre, String descripcion, float precio, float graduacionAlcoholica, float tamaño, float peso, Iterable<String> categoria) {
        Bebida bebida = bebidaRepository.findById(id).orElse(null);
        if (bebida != null) {
            bebida.setNombre(nombre);
            bebida.setDescripcion(descripcion);
            bebida.setPrecio(precio);
            bebida.setGraduacionAlcoholica(graduacionAlcoholica);
            bebida.setTamaño(tamaño);
            bebida.setPeso(peso);
            if(categoria != null) {
                bebida.clearCategoria();
                bebida.addCategoria(categoriaService.getCategoriaByNombre("Bebida", Categoria.TipoCategoria.BEBIDA));
                for (String cat : categoria) {
                    bebida.addCategoria(categoriaService.getCategoriaByNombre(cat, Categoria.TipoCategoria.BEBIDA));
                }
            }
            return bebidaRepository.save(bebida);
        }
        return null;
    }

    public void deleteBebida(Long id) {
        bebidaRepository.deleteById(id);
    }
}
