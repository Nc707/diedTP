package nc.dao.memory;

import nc.dao.ClienteDAO;
import java.util.ArrayList;
import nc.modelo.Cliente;

/**
 *
 * @autor lucia
 */
public class ClienteMemory implements ClienteDAO {
    
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private static ClienteMemory uniqueInstance;
   
    @Override
    public ArrayList<Cliente> listar() {
       return clientes;
    }
    
    public static ClienteMemory getInstancia(){
        if(uniqueInstance == null){
            uniqueInstance = new ClienteMemory();
        }
        return uniqueInstance;
    }
    private ClienteMemory(){}
    
    @Override
    public void add(Cliente cliente) {
       clientes.add(cliente);
    }

    @Override
    public void actualizar(Cliente cliente) {
        clientes.stream()
                .filter(c -> c.getId() == cliente.getId())
                .findFirst()
                .ifPresent(c -> {
                    int index = clientes.indexOf(c);
                    clientes.set(index, cliente);
                });
    }

    @Override
    public void eliminar(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }

    @Override
    public Cliente buscar(int id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
