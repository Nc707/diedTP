package com.deso.etapa_final.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deso.etapa_final.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
    public List<Cliente> findByNombreContaining(String nombre);
    public List<Cliente> findByDireccionContaining(String direccion);
    public List<Cliente> findByCuit(Long cuit);
    public List<Cliente> findByEmailContaining(String email);
    public List<Cliente> findByCoordenadasLatitud(Double latitud);
    public List<Cliente> findByCoordenadasLongitud(Double longitud);
}
