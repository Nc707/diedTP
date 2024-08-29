/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import java.util.ArrayList;

/**
 *
 * @author lucia
 */
public class Vendedor {
    private static int next_id = 0;
    private int id;
    private String nombre;
    private String direccion;
    private ArrayList<Coordenada> coordenadas;
  
    
    public Vendedor(String nombre, String direccion){
        this.id = next_id;
        next_id++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenadas = new ArrayList<>();
        
    }
    public void añadirCoordenada(Coordenada c){
        this.coordenadas.add(c);
    }
    public Coordenada verCoordenada(int index){
        return coordenadas.get(index);
    }
    public int cantidadCoordenadas(){
        return coordenadas.size();
    }
    public void eliminarCoordenada(int index){
        try{
        this.coordenadas.remove(index);
        }
        catch(Error e){
            System.out.println(e);
        }
    }
    public void eliminarCoordenada(Coordenada c){
        try{
            this.coordenadas.remove(c);
        }
        catch(Error e){
            System.out.println(e);
        }
    }
    public String getDireccion(){
        return direccion;
    }
    public int getId()
   {
       return id;
   }
   public String getNombre()
   {
       return nombre;
   }
    
}
