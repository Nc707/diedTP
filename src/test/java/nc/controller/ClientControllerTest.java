/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package nc.controller;

import java.util.ArrayList;
import java.util.List;
import nc.controller.ClientController;
import nc.dao.memory.ClienteMemory;
import nc.modelo.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pc
 */
public class ClientControllerTest {
    
    public ClientControllerTest() {
    }

  /*  String nombre = "Tester";
    int cuit = 123456789;
    String email = "tester@example.com";
    String direccion = "Calle del tester 123";
    double latitud = -34.6037;
    double longitud = -58.3816;
    */
    
    @Test
    public void testLoadData() {
    }

    @Test
    public void testGetCliente() {
   String nombre = "Tester";
    int cuit = 123456789;
    String email = "tester@example.com";
    String direccion = "Calle del tester 123";
    double latitud = -34.6037;
    double longitud = -58.3816;
    
        ClientController controller = new ClientController();
    int testID = 1;
    
    Cliente cliente = controller.crear(nombre,cuit,email, direccion, latitud, longitud);
    cliente.setId(testID);
    List<Object> clientData = controller.getCliente(testID);
        assertNotNull(clientData, "Los datos del cliente no deben ser nulos");
        assertEquals(7, clientData.size(), "Se esperan 7 elementos en la lista");
        assertEquals(testID, clientData.get(0), "El ID debe coincidir");
        assertEquals(nombre, clientData.get(1), "El nombre debe coincidir");
        assertEquals(direccion, clientData.get(2), "La dirección debe coincidir");
        assertEquals(email, clientData.get(3), "El email debe coincidir");
        assertEquals(cuit, clientData.get(4), "El CUIT debe coincidir");
        assertEquals(latitud, clientData.get(5), "La latitud debe coincidir");
        assertEquals(longitud, clientData.get(6), "La longitud debe coincidir");
    }

    @Test
    public void testCrear() {
        String nombre = "Tester";
        int cuit = 123456789;
        String email = "tester@example.com";
        String direccion = "Calle del tester 123";
        double latitud = -34.6037;
        double longitud = -58.3816;
  
        ClientController controller = new ClientController();
        Cliente cliente = controller.crear(nombre , cuit, email , direccion , latitud,longitud);
        assertNotNull(cliente, "El cliente no debe ser nulo");
        assertEquals(nombre, cliente.getNombre(), "El nombre del cliente debe coincidir");
        assertEquals(cuit, cliente.getCuit(), "El CUIT debe coincidir");
        assertEquals(email, cliente.getEmail(), "El email debe coincidir");
        assertEquals(direccion, cliente.getDireccion(), "La dirección debe coincidir");
        assertEquals(latitud, cliente.getCoordenada().getLatitude(), "La latitud debe coincidir");
        assertEquals(longitud, cliente.getCoordenada().getLongitude(), "La longitud debe coincidir");
        assertTrue(ClienteMemory.getInstancia().listar().contains(cliente), "El cliente debería estar en memoria");
    
        
        
} 
    

    @Test
    public void testModificarCliente() {
    String nombreOriginal = "Tester";
    int cuitOriginal = 123456789;
    String emailOriginal = "tester@example.com";
    String direccionOriginal = "Calle del tester 123";
    double latitudOriginal = -34.6037;
    double longitudOriginal = -58.3816;

    ClientController controller = new ClientController();
    Cliente cliente = controller.crear(nombreOriginal, cuitOriginal, emailOriginal, direccionOriginal, latitudOriginal, longitudOriginal);
    cliente.setId(1);           
        String nuevoNombre = "testerNuevo";
        int nuevoCuit=123456789;
        String nuevoEmail= "nuevoTester@gmail.com";
        String nuevaDireccion ="direcTester 312";
        double nuevaLatitud = 100;
        double nuevaLongitud = 10000;
        
    controller.modificarCliente(1, nuevoNombre, nuevaDireccion, nuevoEmail, nuevoCuit, nuevaLatitud, nuevaLongitud);

    assertEquals(nuevoNombre, cliente.getNombre(), "El nombre debería haber sido actualizado");
    assertEquals(nuevaDireccion, cliente.getDireccion(), "La dirección debería haber sido actualizada");
    assertEquals(nuevoEmail, cliente.getEmail(), "El email debería haber sido actualizado");
    assertEquals(nuevoCuit, cliente.getCuit(), "El CUIT debería haber sido actualizado");
    assertEquals(nuevaLatitud, cliente.getCoordenada().getLatitude(), "La latitud debería haber sido actualizada");
    assertEquals(nuevaLongitud, cliente.getCoordenada().getLongitude(), "La longitud debería haber sido actualizada");
       }
        


    @Test
    public void testAgregarProductoAlCarrito() {
    }

    @Test
    public void testEliminarProducto() {
    }

    @Test
    public void testGetContenidoCarrito() {
    }

    @Test
    public void testModificarItemCarrito() {
    }

    @Test
    public void testSetMetodoPago() {
    }

    @Test
    public void testGetVendedorCarrito() {
    }
}
