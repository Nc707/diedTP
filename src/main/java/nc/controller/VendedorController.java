package nc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nc.modelo.Coordenada;
import nc.modelo.Vendedor;
import nc.dao.VendedorDAO;
import nc.dao.memory.VendedorMemory;
import nc.modelo.Cliente;



public class VendedorController {
    
    private VendedorDAO vendedores = VendedorMemory.getInstancia();
    
    public List<List> loadData(){
        List<Vendedor> data = vendedores.listar();
      
        return data.stream().map((Vendedor c) -> {
            ArrayList list = new ArrayList();
            list.add(c.getId());
            list.add(c.getNombre());
            list.add(c.getDireccion());
            return list;
        }).collect(Collectors.toList());
    }
        public List getVendedor(int ID){
        List vendedorData = new ArrayList();
        Vendedor vendor = vendedores.buscar(ID);
        vendedorData.add(ID);
        vendedorData.add(vendor.getNombre());
        vendedorData.add(vendor.getDireccion());
        vendedorData.add(vendor.getCoordenada().getLatitude());
        vendedorData.add(vendor.getCoordenada().getLongitude());
        return vendedorData;
    }
    
    public void modificarVendedor(int ID, String nombre, String direccion, double cx, double cy){
        Vendedor vendor = vendedores.buscar(ID);
        vendor.setNombre(nombre);
        vendor.setDireccion(direccion);
        vendor.setCoordenada(new Coordenada(cx, cy));
    }


    public Vendedor crear(String nombre, String direccion, double cx, double cy){
        Vendedor vendedor = new Vendedor(nombre, direccion, cx, cy);
        vendedores.add(vendedor);
        return vendedor;
    }
    public void setVendedorDAO(VendedorDAO vendedorDAO) {
    this.vendedores = vendedorDAO;
    }
    public void eliminar(int id){
        vendedores.eliminar(id);
    }

    
}

