package com.deso.etapa_final.repositories;

import com.deso.etapa_final.model.Vendedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendedorRepository extends CrudRepository<Vendedor, Long> {

    List<Vendedor> findByNombreContaining(String nombre);
    List<Vendedor> findByDireccionContaining(String direccion);
    List<Vendedor> findByCoordenadasLatitud(Double latitud);
    List<Vendedor> findByCoordenadasLongitud(Double longitud);
    Optional<Vendedor> findById(Long vendedor_id);
}