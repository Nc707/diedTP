/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.dao.memory;

import nc.dao.VendedorDAO;
import java.util.ArrayList;
import nc.modelo.Vendedor;

/**
 *
 * @author lucia
 */
public class VendedorMemory implements VendedorDAO{
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    private static VendedorMemory uniqueInstance;
    public static VendedorMemory getInstancia(){
        if(uniqueInstance == null){
            uniqueInstance = new VendedorMemory();
        }
        return uniqueInstance;
    }
    @Override
    public ArrayList<Vendedor> listar() {
       return vendedores;
    }

    @Override
    public void add(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    @Override
    public void actualizar(Vendedor vendedor) {
        for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getId() == vendedor.getId()) {
                vendedores.set(i, vendedor);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
         for (int i = 0; i <vendedores.size(); i++) {
            if (vendedores.get(i).getId() == id){
                vendedores.remove(i);
                return;
            }
        }
    }

    @Override
    public Vendedor buscar(int id) {
           for (int i = 0; i < vendedores.size(); i++) {
            if (vendedores.get(i).getId() == id){
                return vendedores.get(i);
            }
        }
        return null;
    
    }
    public Vendedor crear(String nombre, String direccion, double cx, double cy){
    Vendedor vendedor = new Vendedor(nombre, direccion, cx, cy);
    add(vendedor);
    return vendedor;
    }
}
