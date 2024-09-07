/*
 Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package nc.diedtp;

import java.util.ArrayList;
import java.util.Scanner;
import nc.diedtp.Vendedor.TipoItem;

public class Main {

    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        ArrayList<Vendedor> vendedores;
        vendedores = new ArrayList<>();

        clientes.add(new Cliente("roberta", 34558, "robertafernandez@gmail.com", "San Martin 6165", 0.0, 0.0));
        clientes.add(new Cliente("pablo", 58487, "pablo.perez@hotmail.com", "Calchines 1562", 0.0, 0.0));
        clientes.add(new Cliente("segundo", 45872, "segundo@gmail.com", "Francia 2022", 0.0, 0.0));

        vendedores.add(new Vendedor("Jeremias", "Belgrano 9624", 4.0, 5.2));
        vendedores.add(new Vendedor("Luis", "Tucuman 8080", 2.1, -4.0));
        vendedores.add(new Vendedor("Juan", "San Jeronimo 2654", 1.0, -1.12));

        //Categorias
        Categoria alcoholica = new Categoria(TipoItem.BEBIDAS_CON_ALCOHOL, "Bebida con graduacion alcoholica mayor que 0");
        Categoria sinalcohol = new Categoria(TipoItem.BEBIDAS_SIN_ALCOHOL, "Bebida sin graduacion alcoholica");
        Categoria vegana = new Categoria(TipoItem.COMIDAS_VEGANAS, "Comida aptas para veganas");
        Categoria celiaca = new Categoria(TipoItem.COMIDAS_APTOCELIACOS, "Comida sin gluten");
        Categoria comida = new Categoria(TipoItem.COMIDAS, "Producto comestible");

        //bebidas sin alcohol
        Bebida lataCocaCola = new Bebida(0, 500, 250);
        Bebida lataSprite = new Bebida(0, 500, 250);
        Bebida sprite = new Bebida(0, 1000, 500);
        Bebida agua = new Bebida(0, 500, 500);

        //bebidas con alcohol
        Bebida lataHeineken = new Bebida(4, 750, 350);
        Bebida lataMiller = new Bebida(3, 500, 250);
        Bebida quilmes = new Bebida(3, 1000, 500);

        vendedores.get(0).addItem(sprite);
        vendedores.get(1).addItem(lataMiller);
        vendedores.get(1).addItem(lataCocaCola);
        vendedores.get(2).addItem(agua);

        //Plato aptos veganos
        Plato ensaladaSinHuevo = new Plato(211, 500, true, true);
        Plato hamburguesaDeLentejas = new Plato(450, 300, true, false);
        Plato milanesaDeLenteja = new Plato(400, 363, true, false);
        vendedores.get(1).addItem(ensaladaSinHuevo);
        vendedores.get(2).addItem(milanesaDeLenteja);

        //Plato aptos celiacos
        Plato browniesDeAlmendra = new Plato(200, 500, true, true);
        Plato polloAlHorno = new Plato(800, 750, false, true);
        vendedores.get(0).addItem(polloAlHorno);

        //Plato comida
        Plato hamburguesaCompleta = new Plato(2000.8f, 500, false, false);
        Plato hamburguesaSimple = new Plato(1800.5f, 300, false, false);
        Plato papasFritas = new Plato(1200, 363, false, false);
        Plato papasAlHorno = new Plato(900, 500, false, false);
        Plato milanesaACaballo = new Plato(3000, 300, false, false);
        Plato milanesaAlaPizza = new Plato(2250.35f, 363, false, false);
        vendedores.get(2).addItem(papasFritas);

        Scanner entrada = new Scanner(System.in);
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
        return null;
    }

}
