/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;



public class Vendedor {
    private static int next_id = 0;
    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coordenada;

    public Vendedor(String nombre, String direccion, double cx, double cy) {
        this.id = next_id;
        next_id++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = new Coordenada(cx, cy);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nId: " + Integer.toString(id) + "\nDireccion: " + direccion;
    }

    private static final double R = 6371; // radio de la tierra en km

    public double distancia(Cliente cliente) {
        double lat1Rad = Math.toRadians(this.getCoordenada().getLatitude());
        double lon1Rad = Math.toRadians(this.getCoordenada().getLongitude());
        double lat2Rad = Math.toRadians(cliente.getCoordenada().getLatitude());
        double lon2Rad = Math.toRadians(cliente.getCoordenada().getLongitude());

        // lat2 - lat1
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // devuelve la distnacia en km entre los dos puntos
        return R * c;
    }

}
