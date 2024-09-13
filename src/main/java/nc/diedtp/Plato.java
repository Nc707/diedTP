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

    public Plato(String nombre,float precio,float peso, float calorias) {
        super(nombre, precio);
        this.setCalorias(calorias);
        this.setPeso(peso);
        this.addTag(Tag.tagComidas);
    }
    public Plato(String nombre,float precio,float peso, float calorias, Tag tags){
        this(nombre, precio, peso, calorias);
        
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
    public boolean addTag(String tag){
        Tag aux = Tag.getTag(tag);
        if(aux != null && aux.getEsComida()){
            this.tags.add(aux);
            return true;
        }else return false;
    }
    private boolean addTag(Tag tag){
        if(tag != null && tag.getEsComida()){
            this.tags.add(tag);
            return true;
        }else return false;
    }
    @Override
    public boolean removeTag(String tag){
        Tag aux = Tag.getTag(tag);
        return aux != null && Tag.tagComidas != aux && this.tags.remove(aux);
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
