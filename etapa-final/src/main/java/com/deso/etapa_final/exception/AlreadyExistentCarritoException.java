package com.deso.etapa_final.exception;

public class AlreadyExistentCarritoException extends Exception {
    public AlreadyExistentCarritoException(Long clienteId) {
        super("Ya existe un carrito para el cliente con id " + clienteId);
    }
}
