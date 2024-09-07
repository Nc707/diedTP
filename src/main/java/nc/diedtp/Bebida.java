/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;

public class Bebida extends ItemMenu {

    private float graduacionAlcoholica;
    private float tamaño; // tamaño en mL.
    private float peso;

    public Bebida(int grado, float tam, float pes) {
        this.graduacionAlcoholica = grado;
        this.peso = peso(pes);
        this.tamaño = tam;
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

    public boolean esComida() {
        return false;
    }

    public boolean aptoVegano() {
        return false;
    }

    public boolean esBebida() {
        return true;
    }
}
