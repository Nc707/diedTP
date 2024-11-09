/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nc.dao;

import java.util.ArrayList;
import nc.modelo.Cliente;
import nc.modelo.Pedido;
import nc.modelo.Vendedor;

/**
 *
 * @author lucia
 */
public interface PedidoDAO {

    public ArrayList<Pedido> listar();

    public void add(Pedido pedido);

    public void actualizar(Pedido pedido);

    public void eliminar(int id);

    public Pedido buscar(int id);

    public Pedido crear(Vendedor vendedor, Cliente cliente);

}
