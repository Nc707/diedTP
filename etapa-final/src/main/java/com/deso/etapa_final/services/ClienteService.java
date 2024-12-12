package com.deso.etapa_final.services;

import com.deso.etapa_final.model.Cliente;
import com.deso.etapa_final.model.Coordenada;
import com.deso.etapa_final.model.Pago;
import com.deso.etapa_final.model.Pedido;
import com.deso.etapa_final.repositories.ClienteRepository;
import com.deso.etapa_final.repositories.PagoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
