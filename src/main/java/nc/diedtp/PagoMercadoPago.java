/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

/**
 *
 * @author nicol
 */
public class PagoMercadoPago implements EstrategiaPago {
    final float RECARGO = 0.02f;
    private String alias;
    public PagoMercadoPago(String alias){
        this.alias = alias;
    }

    @Override
    public float cerrarPago(float valor) {
        return valor*(1.0f+RECARGO);
    }
}
