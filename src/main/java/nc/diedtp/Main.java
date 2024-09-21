/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package nc.diedtp;

import java.util.ArrayList;
import java.util.Scanner;

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

        makeItems(vendedores, items);

        //ETAPA 2
        ArrayList<ItemMenu> lista;
        for (Vendedor vendedor : vendedores){
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
        
        //ETAPA 1
       /* Scanner entrada = new Scanner(System.in);
        listarVendedores(vendedores);
        Vendedor v_encontrado = buscarVendedor(vendedores, entrada);
        if (v_encontrado != null) {
            System.out.println("Vendedor encontrado: " + v_encontrado.getNombre());
        } else {
            System.out.println("Vendedor no encontrado.");
        }
        v_encontrado = null;
        v_encontrado = eliminarVendedor(vendedores, entrada);

        if (v_encontrado != null) {
            System.out.println("Vendedor eliminado: " + v_encontrado.getNombre());
        } else {
            System.out.println("Vendedor no encontrado ni eliminado.");
        }
        listarVendedores(vendedores);
        listarClientes(clientes);
        // Llamar al método buscarCliente
        Cliente encontrado = buscarCliente(clientes, entrada);

        // Verificar si se encontró el cliente
        if (encontrado != null) {
            System.out.println("Cliente encontrado: " + encontrado.getNombre());
        } else {
            System.out.println("Cliente no encontrado.");
        }

        // Llamar al método buscarCliente
        Cliente c1 = eliminarCliente(clientes, entrada);

        // Verificar si se encontró el cliente
        if (c1 != null) {
            System.out.println("Cliente eliminado: " + c1.getNombre());
        } else {
            System.out.println("Cliente no encontrado ni eliminado.");
        }
        entrada.close();
        listarClientes(clientes);
    }

    public static Cliente buscarCliente(ArrayList<Cliente> clientes, Scanner entrada) {
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea buscar");
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
        return null;
    }

    public static void listarClientes(ArrayList<Cliente> clientes) {
        System.out.println("Lista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public static void listarVendedores(ArrayList<Vendedor> vendedores) {
        System.out.println("Lista de Vendedores:");
        for (Vendedor vendedor : vendedores) {
            System.out.println(vendedor);
        }
    }

    public static Cliente eliminarCliente(ArrayList<Cliente> clientes, Scanner entrada) {
        String clie;
        System.out.println("Ingrese el nombre o id del cliente que desea eliminar");
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
        return null;
    }

    public static Vendedor eliminarVendedor(ArrayList<Vendedor> vendedores, Scanner entrada) {
        String clie;
        System.out.println("Ingrese el nombre o id del vendedor que desea eliminar");
        clie = entrada.nextLine();
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equals(clie)) {
                vendedores.remove(vendedor);
                return vendedor;
            }
            try {
                if (vendedor.getId() == Integer.parseInt(clie)) {
                    vendedores.remove(vendedor);
                    return vendedor;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepccion. esta se puede dar por el mismo motivo q en la busqueda
            }
        }
        return null;
    }

    public static Vendedor buscarVendedor(ArrayList<Vendedor> vendedores, Scanner entrada) {
        String vend;
        System.out.println("Ingrese el nombre o id del vendedor que desea buscar");
        vend = entrada.nextLine();
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equals(vend)) {
                return vendedor;
            }
            try {
                if (vendedor.getId() == Integer.parseInt(vend)) {
                    return vendedor;
                }
            } catch (NumberFormatException e) {
                // Ignorar la excepcion
            }
        }
        return null; */
    }
    private static void makeItems(ArrayList<Vendedor> vendedores, ArrayList<ItemMenu> items){
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
    
}
