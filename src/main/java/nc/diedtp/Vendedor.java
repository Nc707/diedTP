/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;
import java.util.ArrayList;

public class Vendedor {
    public enum TipoItem{
        BEBIDAS_SIN_ALCOHOL,
        COMIDAS,
        COMIDAS_VEGANAS,
        COMIDAS_APTOCELIACOS,
        BEBIDAS_CON_ALCOHOL
    } 
    
    private static int next_id = 0;
    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coordenada;
    private ArrayList<ItemMenu> menu;
    public void addItem(ItemMenu item){
        menu.add(item);
    }
    
    public ArrayList<ItemMenu> getItems(TipoItem tipo){
        ArrayList<ItemMenu> aux = new ArrayList();
        for(ItemMenu item: menu){
            if(tipo == item.getCategoria().getTipo()){
                aux.add(item);
            }
        }
        return aux;
    }
    public ArrayList<ItemMenu> getBebidas() {
        ArrayList<ItemMenu> aux = new ArrayList();
        aux.addAll(getItems(TipoItem.BEBIDAS_SIN_ALCOHOL));
        aux.addAll(getItems(TipoItem.BEBIDAS_CON_ALCOHOL));
        return aux;
    }

    public ArrayList<ItemMenu> getComidas() {
        ArrayList<ItemMenu> aux = new ArrayList();
        aux.addAll(getItems(TipoItem.COMIDAS));
        aux.addAll(getItems(TipoItem.COMIDAS_VEGANAS));
        aux.addAll(getItems(TipoItem.COMIDAS_APTOCELIACOS));
        return aux;
    }
    
    public ArrayList<ItemMenu> getComidasVeganas() {
        return getItems(TipoItem.COMIDAS_VEGANAS);
    }

    public ArrayList<ItemMenu> getBebidasSinAlcohol() {
        return getItems(TipoItem.BEBIDAS_SIN_ALCOHOL);
    }
    
    public Vendedor(String nombre, String direccion, double cx, double cy){
        this.id = next_id;
        next_id++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = new Coordenada(cx, cy);
        this.menu =new ArrayList<>();// aca se inicializa la lista del menu;
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
    @Override
    public String toString(){
        return "Nombre: "+nombre+"\nId: "+Integer.toString(id)+"\nDireccion: "+direccion;
    }

   private static final double R = 6371; // radio de la tierra en km

    public double distancia (Cliente cliente) {
        double lat1Rad = Math.toRadians(this.getCoordenada().getLatitude());
        double lon1Rad = Math.toRadians(this.getCoordenada().getLongitude());
        double lat2Rad = Math.toRadians(cliente.getCoordenada().getLatitude());
        double lon2Rad = Math.toRadians(cliente.getCoordenada().getLongitude());

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
    
   
   
    
   
    
}
