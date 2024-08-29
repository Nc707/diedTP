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
public class Cliente {
    private int id;
    private int cuit;
    private String email;
    private String direccion;
    private ArrayList<Coordenada> coordenadas;
    //CORDENADAS;
    public Cliente( int id,int cuit, String email, String direccion ){
        this.id = id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenadas = new ArrayList<>();
    }
    
    public void anadirCoordenada(Coordenada c){
        this.coordenadas.add(c);
    }
    public Coordenada verCoordenada(int index){
        return coordenadas.get(index);
    }
    public int cantidadCoordenadas(){
        return coordenadas.size();
    }
    public void eliminarCoordenada(Coordenada c){
        this.coordenadas.remove(c);
    }
     public void eliminarCoordenada(int c){
        this.coordenadas.remove(c);
    }
      public String getDireccion(){
        return direccion;
    }
    public int getId()
   {
       return id;
   }
   public int getCuit()
   {
       return cuit;
   }
     public String getEmail(){
        return email;
    }
     
}
