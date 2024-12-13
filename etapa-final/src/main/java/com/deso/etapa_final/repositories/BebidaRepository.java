package com.deso.etapa_final.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.deso.etapa_final.model.Bebida;
import com.deso.etapa_final.model.Vendedor;

public interface BebidaRepository extends CrudRepository<Bebida, Long> {

        List<Bebida> findByNombreContaining(String nombre);
        List<Bebida> findByDescripcionContaining(String descripcion);
        List<Bebida> findByVendedor_nombre(String nombre);
        List<Bebida> findByVendedor_Vendedorid(Long vendedorId);
        List<Bebida> findByPrecioGreaterThanEqual(Double precio);
        List<Bebida> findByPrecioLessThanEqual(Double precio);
        List<Bebida> findByGraduacionAlcoholicaGreaterThanEqual(Float graduacion);
        List<Bebida> findByGraduacionAlcoholicaLessThanEqual(Float graduacion); 
        List<Bebida> findByTamañoGreaterThanEqual(Float tamanio);
        List<Bebida> findByTamañoLessThanEqual(Float tamanio);
        List<Bebida> findByPesoGreaterThanEqual(Float peso);
        List<Bebida> findByPesoLessThanEqual(Float peso);
        List<Bebida> findByCategorias_nombreContaining(String nombre);
}