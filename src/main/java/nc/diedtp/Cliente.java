/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;
import java.util.ArrayList;
import java.util.Scanner;

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
    private String nombre;
    //CORDENADAS;
    public Cliente(){} //constructor generico para poder instanciar un cliente sin parametros
    public Cliente( String nombre, int id,int cuit, String email, String direccion ){
        this.id = id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenadas = new ArrayList<>();
        this.nombre = nombre;
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
    public String getNombre(){
        return nombre;
    }

    //habia una excepcion que , si yo busco por nombre por ej , y no lo encuentra, inmediatamente lo quiere 
    //parsear a int, entonces si el nombre no es un numero, tira una excepcion. por eso el try catch
    public Cliente buscarCliente(ArrayList<Cliente> clientes) {
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea buscar");
        Scanner entrada = new Scanner(System.in);
        clie = entrada.nextLine();
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(clie)) {
                return cliente;
            }
            try {
                if (cliente.getId() == Integer.parseInt(clie)) {
                    return cliente;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepcion 
            }
        }
        entrada.close();
        return null;
    }
    
    public void mostrarCliente(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Id: "+id);
        System.out.println("Cuit: "+cuit);
        System.out.println("Email: "+email);
        System.out.println("Direccion: "+direccion + "\n");
    }
    public void listarClientes(ArrayList<Cliente> clientes){
        System.out.println("Lista de Clientes:");
        for(Cliente cliente : clientes){
            cliente.mostrarCliente();
        }
    }
    public Cliente eliminarCliente(ArrayList<Cliente> clientes){
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea eliminar");
        Scanner entrada = new Scanner(System.in);
        clie = entrada.nextLine();
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(clie)) {
                clientes.remove(cliente);
                return cliente;
            }
            try {
                if (cliente.getId() == Integer.parseInt(clie)) {
                    clientes.remove(cliente);
                    return cliente;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepccion. esta se puede dar por el mismo motivo q en la busqueda
            }
        }
        entrada.close();
        return null;
    }
    // public Cliente buscarCliente(ArrayList<Cliente> clientes){
    //     String clie;
    //     System.out.println("Ingrese el nombre o id del cliente que desea buscar");
    //     Scanner entrada = new Scanner (System.in);
    //     clie = entrada.next(); 
    //     for(int i=0; i<clientes.size();i++){
    //         if (clientes.get(i).nombre.equals(clie) || clientes.get(i).getId() == Integer.parseInt(clie)){
    //                 return clientes.get(i);
    //         }
    //         }
    //     entrada.close();
    //     return null;
    //     }
     
}
