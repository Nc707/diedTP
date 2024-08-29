/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

/**
 *
 * @author lucia
 */
public class Vendedor {
    private int id;
    private String nombre;
    private String direccion;
    //CORDENADAS;
  
    
    public Vendedor( int id, String nombre, String direccion ){
        setId(id);
        setNombre(nombre);
        setDireccion(direccion);
    }
   public void setId(int id)
   {this.id = id;
   }
   public void setNombre(String nombre)
   {this.nombre = nombre;
   }
    public void setDireccion(String direccion)
   {this.direccion = direccion;
   }
}
