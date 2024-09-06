/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;


public class Plato extends ItemMenu{
    private float calorias;
    private boolean aptoCeliaco;
    private boolean aptoVegano;
    private float peso;

    public Plato(float calo, float pes, boolean b1, boolean b2){
        this.calorias = calo;
        this.peso = pes;
        this.aptoVegano = b1;
        this.aptoCeliaco=b2;
    }
    public float pesoItem(float peso) {
        peso =  (float) (peso * 0.10);
        return peso ;
    }

    public float getCalorias(){
        return  calorias;
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

    public boolean isAptoVegano() {
        return aptoVegano;
    }

    public void setAptoVegetariano(boolean aptoVegetariano) {
        this.aptoVegano = aptoVegetariano;
    }
 
    public boolean esComida() {
        return true;
    }
    
    public boolean aptoVegano() {
        return aptoVegano;
    }

    public boolean esBebida() {
        return false;
    }
}
