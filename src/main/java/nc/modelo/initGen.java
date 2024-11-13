/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package nc.modelo;


import nc.dao.memory.ItemPedidoMemory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import nc.dao.ClienteDAO;
import nc.dao.memory.ClienteMemory;

import nc.excepciones.CategoriaIncompatibleException;
import nc.excepciones.PedidoCerradoException;
import nc.excepciones.PedidoIncorrectoException;
import nc.excepciones.VendedorIncorrectoException;
import nc.dao.memory.ItemMenuMemory;
import nc.dao.VendedorDAO;
import nc.dao.memory.VendedorMemory;

public class initGen {

    public static void run() {
        ClienteDAO clientes = ClienteMemory.getInstancia();
        VendedorDAO vendedores = VendedorMemory.getInstancia();
        
        ItemMenuMemory items = ItemMenuMemory.getInstancia();

        clientes.add(new Cliente("roberta", 34558, "robertafernandez@gmail.com", "San Martin 6165", 0.0, 0.0));
        clientes.add(new Cliente("pablo", 58487, "pablo.perez@hotmail.com", "Calchines 1562", 0.0, 0.0));
        clientes.add(new Cliente("segundo", 45872, "segundo@gmail.com", "Francia 2022", 0.0, 0.0));

        vendedores.add(new Vendedor("Jeremias", "Belgrano 9624", 4.0, 5.2));
        vendedores.add(new Vendedor("Luis", "Tucuman 8080", 2.1, -4.0));
        vendedores.add(new Vendedor("Juan", "San Jeronimo 2654", 1.0, -1.12));
        try{
        makeItems(vendedores, items);
        }catch(CategoriaIncompatibleException error){
        }
        //ETAPA 2
        
        //ETAPA 3
         ArrayList<ItemPedido> itemP1 = new ArrayList<>();
         ArrayList<ItemPedido> itemP2 = new ArrayList<>();
         ArrayList<ItemPedido> itemP3 = new ArrayList<>();
         ArrayList<ItemPedido> itemP4 = new ArrayList<>();
        Pedido p1 = new Pedido(vendedores.listar().get(0), clientes.listar().get(0));
        Pedido p2 = new Pedido(vendedores.listar().get(0), clientes.listar().get(1));
        Pedido p3 = new Pedido(vendedores.listar().get(1), clientes.listar().get(2));
        Pedido p4 = new Pedido(vendedores.listar().get(2), clientes.listar().get(0));
        
        Random rand = new Random(new Date().getTime());
        createRandomPedido(p1,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP1);
        createRandomPedido(p2,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP2);
        createRandomPedido(p3,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP3);
        createRandomPedido(p4,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP4);
        p1.setPagoMercadoPago("5641651");
        p1.cambioEstado(Pedido.EstadoPedido.RECIBIDO);
        p2.setPagoTransferencia("51651165", 645515161);
        p2.cambioEstado(Pedido.EstadoPedido.RECIBIDO);
        p3.setPagoMercadoPago("5161ga");
        p3.cambioEstado(Pedido.EstadoPedido.RECIBIDO);
        p4.setPagoMercadoPago("15189");
        p4.cambioEstado(Pedido.EstadoPedido.RECIBIDO);
       ItemPedidoMemory pedidos = ItemPedidoMemory.getItemPedidoMemory();
        pedidos.addPedido(itemP1);
        pedidos.addPedido(itemP2);
        pedidos.addPedido(itemP3);
        pedidos.addPedido(itemP4);

    }
    
    private static void makeItems(VendedorDAO vendedores, ItemMenuMemory items) throws CategoriaIncompatibleException{
        //bebidas sin alcohol
        
        Bebida lataCocaCola = new Bebida("Lata Cocacola",vendedores.listar().get(0),100,0, 500, 250);
        Bebida lataSprite = new Bebida("Lata Sprite",vendedores.listar().get(1),100,0, 500, 250);
        Bebida coca = new Bebida("Botella Cocacola",vendedores.listar().get(2),100,0, 500, 500);
        Bebida sprite = new Bebida("Botella Sprite",vendedores.listar().get(1),100,0, 500, 500);
        Bebida sprite1 = new Bebida("Botella Sprite",vendedores.listar().get(2),100,0, 500, 500);
        Bebida agua = new Bebida("Botella Agua",vendedores.listar().get(0),100,0, 500, 500);
        
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
        Bebida lataHeineken = new Bebida("Lata Heineken",vendedores.listar().get(1),499.99f ,350, 4, 750);
        Bebida lataMiller = new Bebida("Lata Miller",vendedores.listar().get(2),499.99f ,250, 3, 250);
        Bebida quilmes = new Bebida("Botella de Quilmes",vendedores.listar().get(0),499.99f ,500, 3, 1000);
        lataHeineken.addCategoria("Bebidas Alcoholicas");
        lataMiller.addCategoria("Bebidas Alcoholicas");
        quilmes.addCategoria("Bebidas Alcoholicas");
        
        items.add(lataHeineken);
        items.add(lataMiller);
        items.add(quilmes);

        //Plato aptos veganos
        Plato ensaladaSinHuevo = new Plato("Ensalada de Lechuga, Tomate y Repollo",vendedores.listar().get(1), 1500 , 500 ,211);
        ensaladaSinHuevo.addCategoria("Vegano");
        ensaladaSinHuevo.addCategoria("Celiaco");
        Plato hamburguesaDeLentejas = new Plato("Hamburguesa de Lentejas",vendedores.listar().get(2), 799, 450, 300);
        hamburguesaDeLentejas.addCategoria("Vegano");
        Plato milanesaDeLenteja = new Plato("Milanesa de Lentejas",vendedores.listar().get(0), 1499, 363, 400);
        milanesaDeLenteja.addCategoria("Vegano");
        
        items.add(ensaladaSinHuevo);
        items.add(hamburguesaDeLentejas);
        items.add(milanesaDeLenteja);

        //Plato aptos celiacos
        Plato browniesDeAlmendra = new Plato("Brownies de Almendra",vendedores.listar().get(1), 3652, 500, 200);
        browniesDeAlmendra.addCategoria("Celiaco");
        browniesDeAlmendra.addCategoria("Vegano");
        Plato polloAlHorno = new Plato("Pollo al Horno",vendedores.listar().get(2), 3500, 750, 800);
        polloAlHorno.addCategoria("Celiaco");
        
        items.add(browniesDeAlmendra);
        items.add(polloAlHorno);
        //Plato comida
        Plato hamburguesaCompleta = new Plato("Hamburguesa Completa", vendedores.listar().get(0),4000.0f, 500, 2000.8f);
        Plato hamburguesaSimple = new Plato("Hamburquesa simple",vendedores.listar().get(1), 3500, 300, 1800.5f);
        Plato papasFritas = new Plato("Papas Fritas",vendedores.listar().get(2), 2500, 363, 1200);
        Plato papasAlHorno = new Plato("Papas al horno", vendedores.listar().get(0),3500, 500, 900);
        Plato milanesaACaballo = new Plato("Milanesa a caballo",vendedores.listar().get(1), 3500, 300, 3000);
        Plato milanesaAlaPizza = new Plato("Milanesa a la pizza",vendedores.listar().get(2), 3500, 363, 2250.35f);
        
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
                }catch(PedidoIncorrectoException | VendedorIncorrectoException | PedidoCerradoException e){
                    System.out.println(e);
                }
                itemP.add(ped);
            }else
                i--;
        }
    }
}
