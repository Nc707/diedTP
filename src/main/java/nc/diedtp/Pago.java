/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nicol
 */
public class Pago {
    public final long fecha;
    public final double monto;
    public final String resumen;
    public final String vendedor;
    public Pago(Pedido pedido) {
        this.fecha = new Date().getTime();
        this.monto = pedido.getPrecio();
        this.resumen = pedido.toString();
        this.vendedor=pedido.getVendedor().toString();
    }
    @Override
    public String toString(){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = format.format(this.fecha);
            return fechaFormateada+vendedor+resumen;
    }
}
