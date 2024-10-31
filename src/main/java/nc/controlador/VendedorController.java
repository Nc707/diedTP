/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nc.modelo.Vendedor;
import nc.modelo.VendedorDAOandMemory.VendedorDAO;
import nc.modelo.VendedorDAOandMemory.VendedorMemory;

/**
 *
 * @author nicol
 */
public class VendedorController {
    public List<List> loadData(){
        VendedorDAO database = VendedorMemory.getInstancia();
        List<Vendedor> data = database.listar();
        return data.stream().map((Vendedor c) -> {
            ArrayList list = new ArrayList();
            list.add(c.getId());
            list.add(c.getNombre());
            list.add(c.getDireccion());
            return list;
        }).collect(Collectors.toList());
    }
}
