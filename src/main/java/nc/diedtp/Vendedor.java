/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;
import java.util.Scanner;
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
    private Coordenada coordenada;
  
    
    public Vendedor(String nombre, String direccion, double cx, double cy){
        this.id = next_id;
        next_id++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = new Coordenada(cx, cy);
        
    }
   public Coordenada getCoordenada(){
       return coordenada;
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

   private static final double R = 6371; // radio de la tierra en km

    public double distancia (Cliente cliente) {
        // convertir de grados a radianes
        // double lat1 = coordenada.lat;
        // double lon1 = coordenada.lng;
        // double lat2 = c.getCoordenada().lat;
        // double lon2 = c.getCoordenada().lng;
        double lat1Rad = Math.toRadians(this.getCoordenada().lat);
        double lon1Rad = Math.toRadians(this.getCoordenada().lng);
        double lat2Rad = Math.toRadians(cliente.getCoordenada().lat);
        double lon2Rad = Math.toRadians(cliente.getCoordenada().lng);
        // double lat1Rad = Math.toRadians(lat1);
        // double lon1Rad = Math.toRadians(lon1);
        // double lat2Rad = Math.toRadians(lat2);
        // double lon2Rad = Math.toRadians(lon2);

        // lat2 - lat1
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // devuelve la distnacia en km entre los dos puntos
        return R * c;
    }
   public Vendedor buscarVendedor(ArrayList<Vendedor> vendedores){
        String vend;
        Scanner entrada = new Scanner (System.in);
      vend = entrada.next(); 
      for(int i=0; i<vendedores.size();i++){
           if (vendedores.get(i).nombre.equals(vend) || vendedores.get(i).getId() == Integer.parseInt(vend)){
                return vendedores.get(i);
          }
        }
      entrada.close();
      return null;
      }
}
