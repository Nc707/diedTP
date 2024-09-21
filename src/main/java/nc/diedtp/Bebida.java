/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

public class Bebida extends ItemMenu {

    private float graduacionAlcoholica;
    private float tamaño; // tamaño en mL.
    private float peso;

    public Bebida(String nombre,Vendedor vendedor , float precio, float pes, int grado, float tam) {
        super(nombre, precio, vendedor);
        this.graduacionAlcoholica = grado;
        this.peso = peso(pes);
        this.tamaño = tam;
        this.categorias.add(Categoria.categoriaBebidas);
    }
    
    private float peso(float weight) {
        if (graduacionAlcoholica == 0) {
            weight = (float) ((tamaño * 1.04) + (weight * 0.20));

        } else {
            weight = (float) ((tamaño * 0.99) + (weight * 0.20));

        }
        return weight;
    }

    public float getPeso() {
        return peso;
    }

    public float getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public void setGraduacionAlcoholica(int graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    public float getTamaño() {
        return tamaño;
    }

    public void setTamaño(float tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean aptoVegano() {
        return this.hasTag("Vegano");
    }

    @Override
    public boolean esBebida() {
        return true;
    }
    @Override
    public boolean addCategoria(String categoria){
        Categoria cat = Categoria.getCategoria(categoria);
        if(cat!=Categoria.categoriaPlatos){//Posible punto de exepcion
            this.categorias.add(cat);
            return true;
        }
        return false;
    }
    @Override
    public boolean removeCategoria(String tag){
        Categoria aux = Categoria.getCategoria(tag);
        return aux != null && Categoria.categoriaBebidas !=aux && this.categorias.remove(aux);
    }


}
