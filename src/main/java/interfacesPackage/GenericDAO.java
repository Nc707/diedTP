/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfacesPackage;

import java.util.ArrayList;

/**
 *
 * @author lucia
 * @param <T>
 */
public interface GenericDAO<T> { //La idea es reutilizar el codigo para las funciones minimas indispensables.Por lo tanto este DAO generico utiliza T como parametro.Luego va a ser remplazado por ClienteM, VendedorM...
    public ArrayList<T> listar();
    public void crear (T dato); // (falta ver como se implementaria bien el crear...)
    public void actualizar (T dato);
    public void eliminar(int id); //elimina por id.
    T buscar(int id); // T es el objeto que va a retornar.
}
