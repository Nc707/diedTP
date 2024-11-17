/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controller;


import nc.dao.ClienteDAO;
import nc.modelo.Cliente;

import java.util.ArrayList;

public class MockClienteDAO implements ClienteDAO {
    @Override
    public ArrayList<Cliente> listar() {
        return new ArrayList<>();
    }

    @Override
    public void add(Cliente cliente) {
    }

    @Override
    public void actualizar(Cliente cliente) {
    }

    @Override
    public void eliminar(int id) {
    }

    @Override
    public Cliente buscar(int id) {
        // Simula un cliente para los tests
        return new Cliente(id, "Mock Name", 123456789L, "mock@example.com", "Mock Address", -34.6037, -58.3816);
    }
}