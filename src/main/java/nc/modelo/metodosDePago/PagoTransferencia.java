/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo.metodosDePago;

/**
 *
 * @author nicol
 */
public class PagoTransferencia implements EstrategiaPago{
    final float RECARGO = 0.04f;
    String cbu;
    int cuit;
    public PagoTransferencia(String cbu, int cuit){
        this.cbu = cbu;
        this.cuit = cuit;
    }

    @Override
    public float cerrarPago(float valor) {
        return valor*(1.0f+RECARGO);
    }
}
