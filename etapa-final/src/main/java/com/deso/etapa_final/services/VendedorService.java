package com.deso.etapa_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.repositories.VendedorRepository;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor addVendedor(String nombre, String direccion, double latitud, double longitud) {
        Coordenada coordenadas = new Coordenada(latitud, longitud);
        Vendedor vendedor = new Vendedor(nombre, direccion, coordenadas);
        return vendedorRepository.save(vendedor);
    }

    public Iterable<Vendedor> getVendedores() {
        return vendedorRepository.findAll();
    }

    public Vendedor getVendedorById(long id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    public Vendedor updateVendedor(long id, String nombre, String direccion, double latitud, double longitud) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
        if (vendedor != null) {
            vendedor.setNombre(nombre);
            vendedor.setDireccion(direccion);
            vendedor.setCoordenadas(new Coordenada(latitud, longitud));
            return vendedorRepository.save(vendedor);
        }
        return null;
    }
    
    public void deleteVendedor(long id) {
        vendedorRepository.deleteById(id);
    }
}