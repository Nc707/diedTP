package com.deso.etapa_final.model;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Pago {
    private final long fecha;
    private final double monto;
    private final String resumen;
    private final String vendedor;

    public Pago(Pedido pedido) {
        this.fecha = new Date().getTime();
        this.monto = pedido.getPrecio();
        this.resumen = pedido.toString();
        this.vendedor = pedido.getVendedor().toString();
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = format.format(new Date(this.fecha));
        return fechaFormateada + " " + vendedor + " " + resumen;
    }
}
