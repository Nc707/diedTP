package com.deso.etapa_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.model.metodosDePago.EstrategiasDePago;
import com.deso.etapa_final.model.metodosDePago.PagoMercadoPago;
import com.deso.etapa_final.model.metodosDePago.PagoTransferencia;
import com.deso.etapa_final.repositories.EstrategiasDePagoRepository;

@Service
public class EstrategiasDePagoService {

    @Autowired
    private EstrategiasDePagoRepository repository;

    public EstrategiasDePago guardarMercadoPago(String alias) {
        PagoMercadoPago mercadoPago = new PagoMercadoPago(alias);
        return repository.save(mercadoPago);
    }

    public EstrategiasDePago guardarTransferencia(String cbu, Long cuit) {
        PagoTransferencia transferencia = new PagoTransferencia(cbu, cuit);
        return repository.save(transferencia);
    }

    public EstrategiasDePago obtenerEstrategia(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Estrategia no encontrada"));
    }
}
