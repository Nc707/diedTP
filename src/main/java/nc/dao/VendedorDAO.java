/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nc.dao;

import java.util.ArrayList;
import nc.modelo.Vendedor;

/**
 *
 * @author lucia
 */
public interface VendedorDAO {

    public ArrayList<Vendedor> listar();

    public void add(Vendedor vendedor);

    public void actualizar(Vendedor vendedor);

    public void eliminar(int id);

    public Vendedor buscar(int id);

    public Vendedor crear(String nombre, String direccion, double cx, double cy);
}
