package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Pago;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.repositories.ClienteRepository;
import com.deso.etapa_final.repositories.PagoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagoRepository pagoRepository;

    public Cliente addCliente(long cuit, String email, String direccion, Coordenada coordenadas, String nombre) {
        Cliente cliente = new Cliente();
        cliente.setCuit(cuit);
        cliente.setEmail(email);
        cliente.setDireccion(direccion);
        cliente.setCoordenadas(coordenadas);
        cliente.setNombre(nombre);
        return clienteRepository.save(cliente);
    }

    public Iterable<Cliente> getClientes() {
        return clienteRepository.findAll();
    }
public Iterable<Cliente> generalSearch(String searchable, String orderBy, String orderDirection) {
    Long longValue = null;
    HashSet<Cliente> resultSet = new HashSet<>();

    List<Cliente> clientesbyNombre = clienteRepository.findByNombreContaining(searchable);
    resultSet.addAll(clientesbyNombre);

    List<Cliente> clientesbyDireccion = clienteRepository.findByDireccionContaining(searchable);
    resultSet.addAll(clientesbyDireccion);

    List<Cliente> clientesbyEmail = clienteRepository.findByEmailContaining(searchable);
    resultSet.addAll(clientesbyEmail);

    try {
        longValue = Long.parseLong(searchable);

        List<Cliente> clientesbyCuit = clienteRepository.findByCuit(longValue);
        resultSet.addAll(clientesbyCuit);

        Cliente clientebyId = clienteRepository.findById(longValue).get();
        if(clientebyId != null){
            resultSet.add(clientebyId);
        }
    } catch (NumberFormatException e) {}
    
    Double doubleValue = null;
    try {
        doubleValue = Double.parseDouble(searchable);
        List<Cliente> clientesbyLatitud = clienteRepository.findByCoordenadasLatitud(doubleValue);
        resultSet.addAll(clientesbyLatitud);
        List<Cliente> clientesbyLongitud = clienteRepository.findByCoordenadasLongitud(doubleValue);
        resultSet.addAll(clientesbyLongitud);
    } catch (NumberFormatException e) {}

    List<Cliente> resultList = new ArrayList<>(resultSet);
    resultList.sort((c1, c2) -> {
        int comparison = 0;
        switch (orderBy) {
            case "id":
                comparison = c1.getClienteid().compareTo(c2.getClienteid());
                break;
            case "nombre":
                comparison = c1.getNombre().compareTo(c2.getNombre());
                break;
            case "cuit":
                comparison = c1.getCuit().compareTo(c2.getCuit());
                break;
            case "email":
                comparison = c1.getEmail().compareTo(c2.getEmail());
                break;
            case "direccion":
                comparison = c1.getDireccion().compareTo(c2.getDireccion());
                break;
            case "latitud":
                comparison = Double.compare(c1.getCoordenadas().getLatitud(), c2.getCoordenadas().getLatitud());
                break;
            case "longitud":
                comparison = Double.compare(c1.getCoordenadas().getLongitud(), c2.getCoordenadas().getLongitud());
                break;
            default:
                comparison = c1.getClienteid().compareTo(c2.getClienteid());
                break;
        }
        return orderDirection.equals("ASC") ? comparison : -comparison;
    });
    return resultList;
}

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente updateCliente(Long id, long cuit, String email, String direccion, Coordenada coordenadas, String nombre) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setCuit(cuit);
            cliente.setEmail(email);
            cliente.setDireccion(direccion);
            cliente.setCoordenadas(coordenadas);
            cliente.setNombre(nombre);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public void generarPago(Pedido pedido) {
        Pago pago = new Pago(pedido);
        pagoRepository.save(pago);
    }
}
