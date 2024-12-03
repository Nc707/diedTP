package com.deso.etapa_final.repositories;

import java.util.Set;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.deso.etapa_final.model.Vendedor;

@Repository
public interface VendedorRepository extends CrudRepository<Vendedor, Long> {
        
        Vendedor findByNombre(String nombre);

        @Override
        Set<Vendedor> findAll();
        

}