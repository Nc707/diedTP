/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package nc.diedtp;

import ItemPedidoManagement.ItemPedidoDAO.tipoFiltrado;
import ItemPedidoManagement.ItemPedidoDAO.tipoFiltradoRango;
import ItemPedidoManagement.ItemPedidoDAO.tipoOrdenamiento;
import ItemPedidoManagement.Carrito;
import ItemPedidoManagement.ItemPedidoMemory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.NoSuchElementException;
import nc.diedtp.excepciones.CantidadItemInvalidaException;
import nc.diedtp.excepciones.CategoriaIncompatibleException;
import nc.diedtp.excepciones.ItemNoEncontradoException;
import nc.diedtp.excepciones.PedidoCerradoException;
import nc.diedtp.excepciones.PedidoIncorrectoException;
import nc.diedtp.excepciones.PedidoNoEncontradoException;
import nc.diedtp.excepciones.VendedorIncorrectoException;
import nc.itemMenuManagement.ItemMenuDAO;
import nc.itemMenuManagement.ItemMenuMemory;

public class Main {

    public static void main(String[] args) throws ItemNoEncontradoException {
        ArrayList<Cliente> clientes = new ArrayList<>();

        ArrayList<Vendedor> vendedores;
        vendedores = new ArrayList<>();
        
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
           System.out.println("No fué posible agregar uno de los items debido a que su categoria era incompatible con su tipo");
        }
        //ETAPA 2



        
        
        
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
        createRandomPedido(p1,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP1);
        createRandomPedido(p2,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP2);
        createRandomPedido(p3,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP3);
        createRandomPedido(p4,rand.nextInt(2, 15), (ArrayList<ItemMenu>) items.getAll(), itemP4);
        System.out.println(p1);
       ItemPedidoMemory pedidos = ItemPedidoMemory.getItemPedidoMemory();
        pedidos.addPedido(itemP1);
        pedidos.addPedido(itemP2);
        pedidos.addPedido(itemP3);
        pedidos.addPedido(itemP4);

        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione su rol:");
        System.out.println("1. Cliente");
        System.out.println("2. Vendedor");
        System.out.print("Ingrese el número de su rol: ");
        int rol = sc.nextInt();

        if (rol == 1) {
            // BEGIN CLIENTE SIDE
            System.out.println("----------------------VENDEDORES REGISTRADOS----------------------");
            for (int i=0; i<vendedores.size(); i++){
                System.out.println(i+ "- " + vendedores.get(i).getNombre());
            }
            System.out.print("SELECCIONE POR NUMERO EL VENDEDOR QUE DESEA VER EL MENU:  ");
            int indexVend = sc.nextInt();
            mostrarMenu(vendedores.get(indexVend), items);
            Cliente yoCliente = new Cliente("utn", 123456, "utn@frsf.utn", "Lavaise 610", -31.62, -60.7);
            clientes.add(yoCliente);
            
            Carrito carrito = null;
            System.out.print("DESEA GENERAR UN CARRITO CON ESTE VENDEDOR? (s/n): ");
            String opt = sc.next();
            
            if (opt.equalsIgnoreCase("s")) {
                Scanner sc2 = new Scanner(System.in);
                try {
                    while (true) {
                        System.out.print("ESCRIBA EL ID ITEM QUE DESEA AGREGAR AL CARRITO, o un caracter invalido para ver el total: ");
                        int indexItem = sc2.nextInt();
                        System.out.print("INGRESE LA CANTIDAD DE ITEMS QUE DESEA AGREGAR DEL PRODUCTO " + items.getItem(indexItem).getNombre() + ": ");
                        int cantidad = sc2.nextInt();
                        try {
                            if(carrito == null){
                                carrito = yoCliente.crearCarrito(yoCliente, items.getItem(indexItem), cantidad, pedidos);
                            } else
                            carrito.agregarItem(items.getItem(indexItem), cantidad);
                            
                        }catch (CantidadItemInvalidaException |VendedorIncorrectoException | PedidoIncorrectoException | PedidoCerradoException e) {
                            System.out.println(e);
                        }
                            
                    }
                } catch (NoSuchElementException e) {
                        System.out.println("Entrada finalizada (Ctrl+Z detectado). Mostrando el total del carrito.");         
                    }
                    //yoCliente.informar();
                if(carrito!=null){
                    System.out.println(carrito);
                    System.out.println("Con  que método de pago desea abonar su compra?\nMercadoPago: Escriba mp\nTransferencia: Escriba t\nEn caso de seleccionar un caracter invalido se asumirá MercadoPago");
                    opt = sc.next();
                    if(opt.equalsIgnoreCase("t")){
                        System.out.println("Por favor ingrese su CBU:");
                        String cbu = sc.next();
                        System.out.println("Por favor ingrese su CUIT:");
                        int cuit = sc.nextInt();
                        carrito.setTransferencia(cbu, cuit);
                    }else{
                        System.out.println("Por favor ingrese su alias:");
                        String alias = sc.next();
                        carrito.setMercadoPago(alias);
                    }
                    System.out.println("Desea confirmar su compra?(s/n)");
                    opt = sc.next();
                    if(opt.equalsIgnoreCase("s")){
                        carrito.cerrarPedido();
                        System.out.println("El total es: "+carrito.verPrecio());
                    }
                }
                
                
            }
         }
        else if (rol == 2) {

            System.out.println("----------------------VENDEDORES REGISTRADOS----------------------");
            for (int i=0; i<vendedores.size(); i++){
                System.out.println(i+ "- " + vendedores.get(i).getNombre());
            }
            System.out.print("Ingrese su id: ");
            int idVendor = sc.nextInt();
            Vendedor vendedor = vendedores.get(idVendor);
            System.out.println(
                "1. Ver menu\n" +
                "2. Ver pedidos\n" +
                "3. Ver pedidos por estado\n " +//+
                "4. Cambiar estado del Pedido"
                //"5. Ver pedidos por cliente\n" +
                //"6. Ver pedidos por fecha\n"
            );
            System.out.print("Ingrese el número de la opción que desea: ");
            switch (sc.nextInt()) {
                case 1:
                mostrarMenu(vendedor, items);
                    break;
                case 2:
                    System.out.println("Pedidos:");
                    for (Pedido pedido : vendedor.getPedidos()) {
                        System.out.println(pedido);
                    }
                    break;
                case 3:
                   System.out.println("Filtrando pedidos por estado...");
                for (Pedido pedido : vendedor.getPedidos()) {
                    if (pedido.getEstado() == Pedido.EstadoPedido.EN_CARRITO) {
                        System.out.println("Pedido en carrito: " + pedido);
                    }
                    if (pedido.getEstado() == Pedido.EstadoPedido.RECIBIDO) {
                        System.out.println("Pedido recibido: " + pedido);
                    }
                    if (pedido.getEstado() == Pedido.EstadoPedido.EN_ENVIO) {
                        System.out.println("Pedido en envío: " + pedido);
                    }
                }
                    break;
                case 4:
                    Scanner c = new Scanner(System.in);
                    System.out.println("Ingresar Id del pedido que desea cambiar: ");
                     int idPedido = c.nextInt();
                      for (Pedido pedido : vendedor.getPedidos()) {
                          if(idPedido == pedido.getId()){
                              cambiarEstado(pedido);
                          }
                      } 
                    
                default:
                    System.out.println("Opción no válida");
                    break;
            }
         }
        
        else {
            System.out.println("Opción no válida");
        }

            
        
        
        System.out.println("(etapa3) desea ejecutar y mostrar filtros? (s/n)");
        String etp3 = sc.next();

        if(etp3.equals("s")){
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
        try{
         System.out.println("-----------------------");
        System.out.println("FILTRADO POR PRECIO TOPE DE ITEMPEDIDO, ORDENAMIENTO PRECIO DE ITEMPEDIDO ");
        lista = (ArrayList<ItemPedido>) pedidos.filtrarPor(tipoFiltrado.PRECIO_TOPE_ITEMMENU , 750.0f, tipoOrdenamiento.PRECIO_ITEMPEDIDO, true);
        for(ItemPedido item: lista) System.out.println(item);
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        
        try{
         System.out.println("-----------------------");
        System.out.println("FILTRADO POR PRECIO MINIMO DE ITEMPEDIDO, ORDENAMIENTO PRECIO DE ITEMPEDIDO ");
        lista = (ArrayList<ItemPedido>) pedidos.filtrarPor(tipoFiltrado.PRECIO_MINIMO_ITEMMENU , 750.0f, tipoOrdenamiento.PRECIO_ITEMPEDIDO, false);
        for(ItemPedido item: lista) System.out.println(item);
        }catch(ItemNoEncontradoException e){
            System.out.println(e);
        }
        }

        
        sc.close();
        
    }
    private static void makeItems(ArrayList<Vendedor> vendedores, ItemMenuMemory items) throws CategoriaIncompatibleException{
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
        Plato browniesDeAlmendra = new Plato("Brownies de Almendra",vendedores.get(1), 3652, 500, 200);
        browniesDeAlmendra.addCategoria("Celiaco");
        browniesDeAlmendra.addCategoria("Vegano");
        Plato polloAlHorno = new Plato("Pollo al Horno",vendedores.get(2), 3500, 750, 800);
        polloAlHorno.addCategoria("Celiaco");
        
        items.add(browniesDeAlmendra);
        items.add(polloAlHorno);
        //Plato comida
        Plato hamburguesaCompleta = new Plato("Hamburguesa Completa", vendedores.get(0),4000.0f, 500, 2000.8f);
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
                }catch(PedidoIncorrectoException | VendedorIncorrectoException | PedidoCerradoException e){
                    System.out.println(e);
                }
                itemP.add(ped);
            }else
                i--;
        }
    }


    private static void mostrarMenu(Vendedor vendedor, ItemMenuMemory items){
            ArrayList<ItemMenu> lista;
            ArrayList<nc.itemMenuManagement.ItemMenuDAO.tipoFiltrado> tiposFiltros = new ArrayList();
            ArrayList filtros = new ArrayList();
            System.out.println("Menu del vendedor "+vendedor.getNombre());
            System.out.println("BEBICAS ACLOHOLICAS: ");
            tiposFiltros.add(ItemMenuDAO.tipoFiltrado.VENDEDOR);
            tiposFiltros.add(ItemMenuDAO.tipoFiltrado.CATEGORIA);
            filtros.add(vendedor);
            filtros.add(Categoria.getCategoria("Bebidas Alcoholicas"));
            try{
                lista = (ArrayList<ItemMenu>) items.filtroMultiple(tiposFiltros, filtros);
                for(ItemMenu item: lista)   System.out.println("ID:"+item.getId() +" - "+ item);
            }catch(ItemNoEncontradoException e){System.out.println(e);}
            System.out.println(" ");
            System.out.println("BEBIDAS SIN ALCOHOL: ");
            filtros.set(1,Categoria.getCategoria("Bebidas sin alcohol"));
            try{
                lista = (ArrayList<ItemMenu>) items.filtroMultiple(tiposFiltros, filtros);
                for(ItemMenu item: lista)   System.out.println("ID:"+item.getId() +" - "+ item);
            }catch(ItemNoEncontradoException e){System.out.println(e);}
            System.out.println(" ");
            System.out.println("COMIDAS VEGANAS:");
            filtros.set(1,Categoria.getCategoria("Vegano"));
            try{
                lista = (ArrayList<ItemMenu>) items.filtroMultiple(tiposFiltros, filtros);
                for(ItemMenu item: lista)   System.out.println("ID:"+item.getId() +" - "+ item);
            }catch(ItemNoEncontradoException e){System.out.println(e);}
            System.out.println(" ");
            System.out.println("COMIDAS APTAS PARA CELIACOS:");
            filtros.set(1,Categoria.getCategoria("Celiaco"));
            try{
                lista = (ArrayList<ItemMenu>) items.filtroMultiple(tiposFiltros, filtros);
                for(ItemMenu item: lista)   System.out.println("ID:"+item.getId() +" - "+ item);
            }catch(ItemNoEncontradoException e){System.out.println(e);}
            System.out.println(" ");
            System.out.println("PLATOS COMUNES:");
            tiposFiltros.set(1,ItemMenuDAO.tipoFiltrado.CATEGORIA_EXCLUYENTE); 
            filtros.set(1,Categoria.getCategoria("plato"));
            try{
                lista = (ArrayList<ItemMenu>) items.filtroMultiple(tiposFiltros, filtros);
                for(ItemMenu item: lista)   System.out.println("ID:"+item.getId() +" - "+ item);
            }catch(ItemNoEncontradoException e){System.out.println(e);}
            System.out.println("-----------------------");
            try{
                lista = (ArrayList<ItemMenu>) items.filtrarPor(nc.itemMenuManagement.ItemMenuDAO.tipoFiltrado.CATEGORIA_EXCLUYENTE, Categoria.getCategoria("Celiaco"));
                for(ItemMenu item: lista)   System.out.println("ID:"+item.getId() +" - "+ item);
            }catch(ItemNoEncontradoException e){System.out.println(e);}
        
    }
    private static void cambiarEstado(Pedido pedido){
       if(pedido.getEstado()== Pedido.EstadoPedido.RECIBIDO ){
        //EXECPTION: NO DEBERIA PODER CAMBIAR DE RECIBIDO A OTRA COSA, AL MENOS QUE CONSIDEREMOS QUE EL ENVIO FALLO.
       }
       else if(pedido.getEstado()== Pedido.EstadoPedido.EN_ENVIO){
               pedido.cambioEstado(Pedido.EstadoPedido.RECIBIDO);
       }
       else{ pedido.cambioEstado(Pedido.EstadoPedido.EN_ENVIO);}
    }
    
}
