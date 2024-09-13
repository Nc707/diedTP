/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

public class Bebida extends ItemMenu {

    private float graduacionAlcoholica;
    private float tamaño; // tamaño en mL.
    private float peso;

    public Bebida(String nombre, float precio, float pes, int grado, float tam) {
        super(nombre, precio);
        this.graduacionAlcoholica = grado;
        this.peso = peso(pes);
        this.tamaño = tam;
        this.addTag(Tag.tagBebidas);
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
        return false;
    }

    @Override
    public boolean esBebida() {
        return true;
    }
    @Override
    public boolean addTag(String tag){
        Tag aux = Tag.getTag(tag);
        if(aux != null && !aux.getEsComida()){
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
        return aux != null && Tag.tagBebidas !=aux && this.tags.remove(aux);
    }


}
