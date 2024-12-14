package com.deso.etapa_final.repositories;

import com.deso.etapa_final.model.Plato;

import java.util.List;
import java.util.Optional;

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
    List<Plato> findByCategorias_nombreContaining(String nombre);

    List<Plato> findByNombreContainingAndVendedor_vendedorid(String nombre, Long idVendedor);
    List<Plato> findByDescripcionContainingAndVendedor_vendedorid(String descripcion, Long idVendedor);
    List<Plato> findByPrecioGreaterThanEqualAndVendedor_vendedorid(Double precio, Long idVendedor);
    List<Plato> findByPrecioLessThanEqualAndVendedor_vendedorid(Double precio, Long idVendedor);
    List<Plato> findByCaloriasGreaterThanEqualAndVendedor_vendedorid(Double calorias, Long idVendedor);
    List<Plato> findByCaloriasLessThanEqualAndVendedor_vendedorid(Double calorias, Long idVendedor);
    List<Plato> findByPesoGreaterThanEqualAndVendedor_vendedorid(Double peso, Long idVendedor);
    List<Plato> findByPesoLessThanEqualAndVendedor_vendedorid(Double peso, Long idVendedor);
    List<Plato> findByAptoCeliacoAndVendedor_vendedorid(Boolean aptoCeliaco, Long idVendedor);
    List<Plato> findByAptoVeganoAndVendedor_vendedorid(Boolean aptoVegano, Long idVendedor);
    List<Plato> findByCategorias_nombreContainingAndVendedor_vendedorid(String nombre, Long idVendedor);
    Optional<Plato> findByitemidAndVendedor_vendedorid(Long id, Long idVendedor);

}