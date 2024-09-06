/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;


public class Plato extends ItemMenu{
    private int calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegetariano;
    private float peso;

    public Plato(int calo, float pes){
        this.calorias = calo;
        this.peso = pes;
    }
    public float pesoItem(float peso) {
        peso =  (float) (peso * 0.10);
        return peso ;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public boolean isAptoCeliaco() {
        return aptoCeliaco;
    }

    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    public boolean isAptoVegetariano() {
        return aptoVegetariano;
    }

    public void setAptoVegetariano(boolean aptoVegetariano) {
        this.aptoVegetariano = aptoVegetariano;
    }
 
    public boolean esComida() {
        return true;
    }
    
    public boolean aptoVegano() {
        return aptoVegetariano;
    }

    public boolean esBebida() {
        return false;
    }
}
