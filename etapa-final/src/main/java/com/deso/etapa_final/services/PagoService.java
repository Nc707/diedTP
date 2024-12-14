package com.deso.etapa_final.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deso.etapa_final.model.Pago;
import com.deso.etapa_final.repositories.PagoRepository;


@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Iterable<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }
}
