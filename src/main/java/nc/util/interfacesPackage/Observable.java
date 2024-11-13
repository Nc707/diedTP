/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nc.util.interfacesPackage;

public interface Observable {
    void addSubscriptor(Observer cliente);
    //void removeSubscriptor(Observer cliente);
    void notificarSubs();
}
