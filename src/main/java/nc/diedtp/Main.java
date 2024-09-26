/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package nc.diedtp;

import ItemPedidoManagement.ItemPedidoDAO.tipoFiltrado;
import ItemPedidoManagement.ItemPedidoDAO.tipoFiltradoRango;
import ItemPedidoManagement.ItemPedidoDAO.tipoOrdenamiento;
import ItemPedidoManagement.ItemPedidoMemory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import nc.diedtp.excepciones.CategoriaIncompatibleException;
import nc.diedtp.excepciones.ItemNoEncontradoException;
import nc.diedtp.excepciones.PedidoIncorrectoException;
import nc.diedtp.excepciones.PedidoNoEncontradoException;
import nc.diedtp.excepciones.VendedorIncorrectoException;

public class Main {

    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        ArrayList<Vendedor> vendedores;
        vendedores = new ArrayList<>();
        
        
        ArrayList<ItemMenu> items = new ArrayList<>();

        clientes.add(new Cliente("roberta", 34558, "robertafernandez@gmail.com", "San Martin 6165", 0.0, 0.0));
        clientes.add(new Cliente("pablo", 58487, "pablo.perez@hotmail.com", "Calchines 1562", 0.0, 0.0));
        clientes.add(new Cliente("segundo", 45872, "segundo@gmail.com", "Francia 2022", 0.0, 0.0));

        vendedores.add(new Vendedor("Jeremias", "Belgrano 9624", 4.0, 5.2));
        vendedores.add(new Vendedor("Luis", "Tucuman 8080", 2.1, -4.0));
        vendedores.add(new Vendedor("Juan", "San Jeronimo 2654", 1.0, -1.12));
        try{
        makeItems(vendedores, items);
        }catch(CategoriaIncompatibleException error){
           System.out.println("No fué posible agregar uno de los items debido a que su categoria era incompatible con su tipo");
        }
        //ETAPA 2
        for (Vendedor vendedor : vendedores){
            ArrayList<ItemMenu> lista = new ArrayList<>();
            System.out.println("Menu del vendedor "+vendedor.getNombre());
            System.out.println("BEBICAS ACLOHOLICAS: ");
            lista = Vendedor.getItems("Bebidas Alcoholicas",vendedor, items);
            for(ItemMenu item: lista)   System.out.println(item);
            System.out.println(" ");
            System.out.println("BEBIDAS SIN ALCOHOL: ");
            lista = Vendedor.getItems("Bebidas sin alcohol",vendedor, items);
            for(ItemMenu item: lista)   System.out.println(item);
            System.out.println(" ");
            System.out.println("COMIDAS VEGANAS:");
            lista = Vendedor.getItems("Vegano", vendedor, items);
            for(ItemMenu item: lista)   System.out.println(item);
            System.out.println(" ");
            System.out.println("COMIDAS APTAS PARA CELIACOS:");
            lista = Vendedor.getItems("Celiaco", vendedor, items);
            for(ItemMenu item: lista)   System.out.println(item);
            System.out.println(" ");
            System.out.println("PLATOS COMUNES:");
            lista = Vendedor.getItemsWithOnly("plato", vendedor, items);
            for(ItemMenu item: lista)   System.out.println(item);
            System.out.println("-----------------------");
        }
        //ETAPA 3
         ArrayList<ItemPedido> itemP1 = new ArrayList<>();
         ArrayList<ItemPedido> itemP2 = new ArrayList<>();
         ArrayList<ItemPedido> itemP3 = new ArrayList<>();
         ArrayList<ItemPedido> itemP4 = new ArrayList<>();
        Pedido p1 = new Pedido(vendedores.get(0), clientes.get(0));
        Pedido p2 = new Pedido(vendedores.get(0), clientes.get(1));
        Pedido p3 = new Pedido(vendedores.get(1), clientes.get(2));
        Pedido p4 = new Pedido(vendedores.get(2), clientes.get(0));
        
        Random rand = new Random(new Date().getTime());
        createRandomPedido(p1,rand.nextInt(2, 15), items, itemP1);
        createRandomPedido(p2,rand.nextInt(2, 15), items, itemP2);
        createRandomPedido(p3,rand.nextInt(2, 15), items, itemP3);
        createRandomPedido(p4,rand.nextInt(2, 15), items, itemP4);
        ItemPedidoMemory pedidos = new ItemPedidoMemory();
        pedidos.addPedido(itemP1);
        pedidos.addPedido(itemP2);
        pedidos.addPedido(itemP3);
        pedidos.addPedido(itemP4);
        ArrayList<ItemPedido> lista;
        try{
        System.out.println("-----------------------");
        System.out.println("FILTRADO POR PRECIO DE PEDIDO PISO: 0 MAXIMO: 100.000, ORDENAMIENTO PRECIO PEDIDO ASCENDENTE ");
        lista = (ArrayList<ItemPedido>) pedidos.filtrarRango(tipoFiltradoRango.PRECIO_PEDIDO , 0.0f, 100000.0f, tipoOrdenamiento.PRECIO_PEDIDO, true);
        for(ItemPedido item: lista) System.out.println(item);
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        try{
         System.out.println("-----------------------");
        System.out.println("FILTRADO POR PRECIO DE PEDIDO PISO: 50.000 MAXIMO: 100.000, ORDENAMIENTO PRECIO ITEMPEDIDO DESCENDENTE ");
        lista = (ArrayList<ItemPedido>) pedidos.filtrarRango(tipoFiltradoRango.PRECIO_PEDIDO , 50000.0f, 100000.0f, tipoOrdenamiento.PRECIO_ITEMPEDIDO, false);
        for(ItemPedido item: lista) System.out.println(item);
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        try{
         System.out.println("-----------------------");
        System.out.println("FILTRADO POR PRECIO PISO: 0 MAXIMO: 50.000, ORDENAMIENTO ID PEDIDO ASCENDENTE ");
        lista = (ArrayList<ItemPedido>) pedidos.filtrarRango(tipoFiltradoRango.PRECIO_PEDIDO , 0.0f, 50000.0f, tipoOrdenamiento.PEDIDO_ID, true);
        for(ItemPedido item: lista) System.out.println(item);
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        
        /*try{
            System.out.println("-----------------------");
            System.out.println("FILTRADO POR VENDEDOR: " + vendedores.get(0));
            lista = pedidos.busquedaPorVendedor(vendedores.get(0).getId());
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
            System.out.println("FILTRADO POR VENDEDOR: " + vendedores.get(1));
            lista = pedidos.busquedaPorVendedor(vendedores.get(1).getId());
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
           System.out.println("FILTRADO POR VENDEDOR: " + vendedores.get(2));
            lista = pedidos.busquedaPorVendedor(vendedores.get(2).getId());
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        
        try{
            System.out.println("FILTRADO POR CLIENTE: " + vendedores.get(0));
            lista = pedidos.busquedaPorCliente(clientes.get(0).getId());
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
            System.out.println("FILTRADO POR CLIENTE: " + vendedores.get(1));
            lista = pedidos.busquedaPorCliente(clientes.get(1).getId());
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
           System.out.println("FILTRADO POR CLIENTE: " + vendedores.get(2));
            lista = pedidos.busquedaPorCliente(clientes.get(2).getId());
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        try{
            System.out.println("FILTRADO POR PEDIDO: 0");
            lista = pedidos.OrdenarPorCantidadASC(0);
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
            System.out.println("FILTRADO POR PEDIDO: 1");
            lista = pedidos.OrdenarPorCantidadASC(1);
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
            System.out.println("FILTRADO POR PEDIDO: 2");
            lista = pedidos.OrdenarPorCantidadASC(2);
            for(ItemPedido item: lista) System.out.println(item);
             System.out.println("-----------------------");
            System.out.println("FILTRADO POR PEDIDO: 3");
            lista = pedidos.OrdenarPorCantidadASC(3);
            for(ItemPedido item: lista) System.out.println(item);
        }catch(PedidoNoEncontradoException e){
            System.out.println(e);
        }*/
    }
    private static void makeItems(ArrayList<Vendedor> vendedores, ArrayList<ItemMenu> items) throws CategoriaIncompatibleException{
        //bebidas sin alcohol
        
        Bebida lataCocaCola = new Bebida("Lata Cocacola",vendedores.get(0),100,0, 500, 250);
        Bebida lataSprite = new Bebida("Lata Sprite",vendedores.get(1),100,0, 500, 250);
        Bebida coca = new Bebida("Botella Cocacola",vendedores.get(2),100,0, 500, 500);
        Bebida sprite = new Bebida("Botella Sprite",vendedores.get(1),100,0, 500, 500);
        Bebida sprite1 = new Bebida("Botella Sprite",vendedores.get(2),100,0, 500, 500);
        Bebida agua = new Bebida("Botella Agua",vendedores.get(0),100,0, 500, 500);
        
        lataCocaCola.addCategoria("Bebidas sin alcohol");
        lataSprite.addCategoria("Bebidas sin alcohol");
        coca.addCategoria("Bebidas sin alcohol");
        sprite.addCategoria("Bebidas sin alcohol");
        sprite1.addCategoria("Bebidas sin alcohol");
        agua.addCategoria("Bebidas sin alcohol");
        
        items.add(agua);
        items.add(lataCocaCola);
        items.add(lataSprite);
        items.add(coca);
        items.add(sprite);
        items.add(sprite1);
        //bebidas con alcohol
        Bebida lataHeineken = new Bebida("Lata Heineken",vendedores.get(1),499.99f ,350, 4, 750);
        Bebida lataMiller = new Bebida("Lata Miller",vendedores.get(2),499.99f ,250, 3, 250);
        Bebida quilmes = new Bebida("Botella de Quilmes",vendedores.get(0),499.99f ,500, 3, 1000);
        lataHeineken.addCategoria("Bebidas Alcoholicas");
        lataMiller.addCategoria("Bebidas Alcoholicas");
        quilmes.addCategoria("Bebidas Alcoholicas");
        
        items.add(lataHeineken);
        items.add(lataMiller);
        items.add(quilmes);

        //Plato aptos veganos
        Plato ensaladaSinHuevo = new Plato("Ensalada de Lechuga, Tomate y Repollo",vendedores.get(1), 1500 , 500 ,211);
        ensaladaSinHuevo.addCategoria("Vegano");
        ensaladaSinHuevo.addCategoria("Celiaco");
        Plato hamburguesaDeLentejas = new Plato("Hamburguesa de Lentejas",vendedores.get(2), 799, 450, 300);
        hamburguesaDeLentejas.addCategoria("Vegano");
        Plato milanesaDeLenteja = new Plato("Milanesa de Lentejas",vendedores.get(0), 1499, 363, 400);
        milanesaDeLenteja.addCategoria("Vegano");
        
        items.add(ensaladaSinHuevo);
        items.add(hamburguesaDeLentejas);
        items.add(milanesaDeLenteja);

        //Plato aptos celiacos
        Plato browniesDeAlmendra = new Plato("Milanesa de Lentejas",vendedores.get(1), 3652, 500, 200);
        browniesDeAlmendra.addCategoria("Celiaco");
        browniesDeAlmendra.addCategoria("Vegano");
        Plato polloAlHorno = new Plato("Milanesa de Lentejas",vendedores.get(2), 3500, 750, 800);
        polloAlHorno.addCategoria("Celiaco");
        
        items.add(browniesDeAlmendra);
        items.add(polloAlHorno);
        //Plato comida
        Plato hamburguesaCompleta = new Plato("Hambusguesa Completa", vendedores.get(0),4000.0f, 500, 2000.8f);
        Plato hamburguesaSimple = new Plato("Hamburquesa simple",vendedores.get(1), 3500, 300, 1800.5f);
        Plato papasFritas = new Plato("Papas Fritas",vendedores.get(2), 2500, 363, 1200);
        Plato papasAlHorno = new Plato("Papas al horno", vendedores.get(0),3500, 500, 900);
        Plato milanesaACaballo = new Plato("Milanesa a caballo",vendedores.get(1), 3500, 300, 3000);
        Plato milanesaAlaPizza = new Plato("Milanesa a la pizza",vendedores.get(2), 3500, 363, 2250.35f);
        
        items.add(hamburguesaCompleta);
        items.add(hamburguesaSimple);
        items.add(papasFritas);
        items.add(papasAlHorno);
        items.add(milanesaACaballo);
        items.add(milanesaAlaPizza);
    }
    private static void createRandomPedido(Pedido p, int cantidad,  ArrayList<ItemMenu> menu, ArrayList<ItemPedido> itemP){
        Random rGen = new Random(new Date().getTime());
        for(int i = 0; i<cantidad;i++){
            ItemMenu item = menu.get(rGen.nextInt(0, menu.size()));
            
            if(p.getVendedor()== item.getVendedor()){
                ItemPedido ped = new ItemPedido(item, rGen.nextInt(1,10),p);
                try{
                p.addItem(ped);
                }catch(PedidoIncorrectoException | VendedorIncorrectoException e){
                    System.out.println(e);
                }
                itemP.add(ped);
            }else
                i--;
        }
    }
    
}
