/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;


public abstract class ItemMenu {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Categoria categoria;
    public abstract float peso();
}
