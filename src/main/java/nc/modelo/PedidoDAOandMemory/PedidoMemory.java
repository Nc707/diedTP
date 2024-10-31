/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo.PedidoDAOandMemory;

import java.util.ArrayList;
import nc.modelo.Cliente;
import nc.modelo.Pedido;
import nc.modelo.Vendedor;
import nc.modelo.interfacesPackage.GenericDAO;

/**
 *
 * @author lucia
 */
public class PedidoMemory implements GenericDAO<Pedido>{
    
 ArrayList<Pedido> pedidos = new ArrayList<>();
 
    @Override
   public ArrayList<Pedido> listar() {
       return pedidos;
    }

    @Override
    public void add(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public void actualizar(Pedido pedido) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedido.getId()) {
                pedidos.set(i, pedido);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
         for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == id){
                pedidos.remove(i);
                return;
            }
        }
    }

    @Override
    public Pedido buscar(int id) {
       for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == id){
                return pedidos.get(i);
            }
        }
        return null;
    }
    
    
     public Pedido crear(Vendedor vendedor, Cliente cliente) {
        Pedido pedido = new Pedido( vendedor, cliente);
         return pedido;
    }
}