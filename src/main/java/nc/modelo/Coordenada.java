/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo;


public class Coordenada {
    public static final double R = 6371; // radio de la tierra en km
    
    private double lat;
    private double lng;
    public Coordenada(double lat,double lng)
    {
    this.lat = lat;
    this.lng = lng;
    }
    @Override
    public String toString(){
        return "Latitude: "+Double.toString(lat)+"\nLongitude: "+Double.toString(lng);
    }
    public double getLatitude(){
        return this.lat;
    }
    public double getLongitude(){
        return this.lng;
    }    
}
