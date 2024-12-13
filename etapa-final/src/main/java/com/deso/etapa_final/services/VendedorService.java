package com.deso.etapa_final.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Vendedor;
import com.deso.etapa_final.repositories.VendedorRepository;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

     public Iterable<Vendedor> generalSearch(String searchable, String orderBy, String orderDirection) {    
        Set<Vendedor> resultSet = new HashSet<>();

        // Buscar por nombre
        List<Vendedor> byNombre = vendedorRepository.findByNombreContaining(searchable);
        resultSet.addAll(byNombre);

        // Buscar por dirección
        List<Vendedor> byDireccion = vendedorRepository.findByDireccionContaining(searchable);
        resultSet.addAll(byDireccion);

        // Buscar por latitud
        try {
            Double latitud = Double.parseDouble(searchable);
            List<Vendedor> byLatitud = vendedorRepository.findByCoordenadasLatitud(latitud);
            resultSet.addAll(byLatitud);
        } catch (NumberFormatException e) {
            // No es un número, no buscar por latitud
        }

        // Buscar por longitud
        try {
            Double longitud = Double.parseDouble(searchable);
            List<Vendedor> byLongitud = vendedorRepository.findByCoordenadasLongitud(longitud);
            resultSet.addAll(byLongitud);
        } catch (NumberFormatException e) {
            // No es un número, no buscar por longitud
        }

        // Buscar por ID
        try {
            Long vendedorId = Long.parseLong(searchable);
            Vendedor byId = vendedorRepository.findById(vendedorId).orElse(null);
            if(byId != null) resultSet.add(byId);
        } catch (NumberFormatException e) {
            // No es un número, no buscar por ID
        }
        List<Vendedor> resultList = new ArrayList<>(resultSet);
        resultList.sort((v1, v2) -> {
            int comparison = 0;
            switch (orderBy) {
                case "id":
                    comparison = Long.compare(v1.getId(), v2.getId());
                    break;
                case "nombre":
                    comparison = v1.getNombre().compareTo(v2.getNombre());
                    break;
                case "direccion":
                    comparison = v1.getDireccion().compareTo(v2.getDireccion());
                    break;
                case "latitud":
                    comparison = Double.compare(v1.getCoordenadas().getLatitud(), v2.getCoordenadas().getLatitud());
                    break;
                case "longitud":
                    comparison = Double.compare(v1.getCoordenadas().getLongitud(), v2.getCoordenadas().getLongitud());
                    break;
            }
            return "DESC".equals(orderDirection) ? -comparison : comparison;
        });
        return resultList;
    }

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