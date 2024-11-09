/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nc.dao;

import java.util.ArrayList;
import nc.modelo.Cliente;

/**
 *
 * @author lucia
 */
public interface ClienteDAO { // se realiza un Dao para cliente para la necesita de implentar funciones propias y exclusivas de cliente.

    public ArrayList<Cliente> listar();

    public void add(Cliente cliente);

    public void actualizar(Cliente cliente);

    public void eliminar(int id);

    public Cliente buscar(int id);

}
