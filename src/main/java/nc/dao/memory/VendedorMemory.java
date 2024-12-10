package nc.dao.memory;

import nc.dao.VendedorDAO;
import java.util.ArrayList;
import nc.modelo.Vendedor;

/**
 *
 * @author lucia
 */
public class VendedorMemory implements VendedorDAO{

    ArrayList<Vendedor> vendedores = new ArrayList<>();
    private static VendedorMemory uniqueInstance;

    public static VendedorMemory getInstancia(){
        if(uniqueInstance == null){
            uniqueInstance = new VendedorMemory();
        }
        return uniqueInstance;
    }
    private VendedorMemory(){}
    @Override
    public ArrayList<Vendedor> listar() {
       return vendedores;
    }

    @Override
    public void add(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    @Override
    public void actualizar(Vendedor vendedor) {
        vendedores.stream()
                  .filter(v -> v.getId() == vendedor.getId())
                  .findFirst()
                  .ifPresent(v -> {
                      int index = vendedores.indexOf(v);
                      vendedores.set(index, vendedor);
                  });
    }

    @Override
    public void eliminar(int id) {
        vendedores.removeIf(vendedor -> vendedor.getId() == id);
    }

    @Override
    public Vendedor buscar(int id) {
        return vendedores.stream()
                         .filter(vendedor -> vendedor.getId() == id)
                         .findFirst()
                         .orElse(null);
    }

    public Vendedor crear(String nombre, String direccion, double cx, double cy){
        Vendedor vendedor = new Vendedor(nombre, direccion, cx, cy);
        add(vendedor);
        return vendedor;
    }
}
