package com.deso.etapa_final.exception;

public class NonExistentCarritoException extends Exception {
    public NonExistentCarritoException(Long clienteId) {
        super("No existe un carrito para el cliente con id " + clienteId);
    }

}