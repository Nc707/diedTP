package com.deso.etapa_final.repositories;

import com.deso.etapa_final.model.Plato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends CrudRepository<Plato, Long> {
}