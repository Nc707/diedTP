package com.deso.etapa_final.repositories;

import com.deso.etapa_final.model.Plato;
import com.deso.etapa_final.model.Vendedor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends CrudRepository<Plato, Long> {
    List<Plato> findByNombreContaining(String nombre);
    List<Plato> findByDescripcionContaining(String descripcion);
    List<Plato> findByVendedor_nombre(String nombre);
    List<Plato> findByVendedor_Vendedorid(Long vendedorId);
    List<Plato> findByPrecioGreaterThanEqual(Double precio);
    List<Plato> findByPrecioLessThanEqual(Double precio);
    List<Plato> findByCaloriasGreaterThanEqual(Double calorias);
    List<Plato> findByCaloriasLessThanEqual(Double calorias);
    List<Plato> findByPesoGreaterThanEqual(Double peso);
    List<Plato> findByPesoLessThanEqual(Double peso);
    List<Plato> findByAptoCeliaco(Boolean aptoCeliaco);
    List<Plato> findByAptoVegano(Boolean aptoVegano);

}