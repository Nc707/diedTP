package nc.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nc.modelo.Coordenada;
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
        public List getVendedor(int ID){
        List vendedorData = new ArrayList();
        Vendedor vendor = VendedorMemory.getInstancia().buscar(ID);
        vendedorData.add(ID);
        vendedorData.add(vendor.getNombre());
        vendedorData.add(vendor.getDireccion());
        vendedorData.add(vendor.getCoordenada().getLatitude());
        vendedorData.add(vendor.getCoordenada().getLongitude());
        return vendedorData;
    }
    
    public void modificarVendedor(int ID, String nombre, String direccion, double cx, double cy){
        Vendedor vendor = VendedorMemory.getInstancia().buscar(ID);
        vendor.setNombre(nombre);
        vendor.setDireccion(direccion);
        vendor.setCoordenada(new Coordenada(cx, cy));
    }


    public Vendedor crear(String nombre, String direccion, double cx, double cy){
        Vendedor vendedor = new Vendedor(nombre, direccion, cx, cy);
        VendedorMemory.getInstancia().add(vendedor);
        return vendedor;
    }
    
}

