/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.diedtp;


public class Bebida extends ItemMenu{
    private int graduacionAlcoholica;
    private float tamaño; // tamaño en mL.
    private float peso;

    public Bebida(int grado,float tam, float pes){
        this.graduacionAlcoholica = grado;
        this.peso = pes;
        this.tamaño = tam;
    }
    
    public float pesoItem(float peso) {
        if(graduacionAlcoholica == 0){
            peso =(float)(( tamaño * 1.04) + (peso *0.20));
            
        }else{
            peso =(float)(( tamaño * 0.99)+ (peso *0.20));
          
        }
        return peso;
        }

    public int getGraduacionAlcoholica() {
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
