package com.deso.etapa_final.controllers;

import com.deso.etapa_final.model.Pago;
import com.deso.etapa_final.services.PagoService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<String>> getAllPagos() {
        Iterable<Pago> pagos = pagoService.getAllPagos();
        Iterable<String> resumenes = ((List<Pago>) pagos).stream()
                .map(Pago::getResumen)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resumenes);
    }
}
