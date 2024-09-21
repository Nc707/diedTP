/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

public class Plato extends ItemMenu {

    private float calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegano;
    private float peso;

    public Plato(String nombre, Vendedor vendedor, float precio,float peso, float calorias) {
        super(nombre, precio, vendedor);
        this.setCalorias(calorias);
        this.setPeso(peso);
        this.categorias.add(Categoria.categoriaPlatos);
    }
    private void setCalorias(float calorias) {
        this.calorias = calorias;
    }
    private void setPeso(float peso){
        this.peso = peso * 0.10f;
    }
    
    // public float pesoItem(float peso) {
    //    peso = (float) (peso * 0.10);
    //    return peso;
    //}
    public float getPeso() {
        return peso;
    }
    public float getCalorias() {
        return calorias;
    }

    public boolean isAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    public boolean isAptoVegano() {
        return aptoVegano;
    }

    public void setAptoVegetariano(boolean aptoVegetariano) {
        this.aptoVegano = aptoVegetariano;
    }
    @Override
    public boolean addCategoria(String categoria){
        Categoria cat = Categoria.getCategoria(categoria);
        if(cat!=Categoria.categoriaBebidas){//Posible punto de exepcion
            this.categorias.add(cat);
            return true;
        }
        return false;
    }
    @Override
    public boolean removeCategoria(String tag){
        Categoria aux = Categoria.getCategoria(tag);
        return aux != null && Categoria.categoriaPlatos != aux && this.categorias.remove(aux);
    }
    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean aptoVegano() {
        return aptoVegano;
    }

    @Override
    public boolean esBebida() {
        return false;
    }
}
