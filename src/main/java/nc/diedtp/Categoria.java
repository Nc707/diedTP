/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

import nc.diedtp.Vendedor.TipoItem;


public class Categoria {
    private static int next_id = 0;
    private int id;
    private String descripcion;
    private TipoItem tipo_item;
    public Categoria(TipoItem tipo, String descripcion){
        this.id = next_id;
        ++next_id;
        this.descripcion = descripcion;
        this.tipo_item = tipo;
    }
    
    public TipoItem getTipo(){
        return tipo_item;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void modifyDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
