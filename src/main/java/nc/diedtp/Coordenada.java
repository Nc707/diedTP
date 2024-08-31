/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

/**
 *
 * @author lucia
 */
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
            
    public double distance(Coordenada coord) {
        // convertir de grados a radianes
        double lat1Rad = Math.toRadians(this.lat);
        double lon1Rad = Math.toRadians(this.lng);
        double lat2Rad = Math.toRadians(coord.lat);
        double lon2Rad = Math.toRadians(coord.lng);

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
