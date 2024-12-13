package com.deso.etapa_final.exception;

public class NonSettedMetodoPagoException extends Exception{
    public NonSettedMetodoPagoException() {
        super("You have to set a payment method before confirming the order");
    }
}
