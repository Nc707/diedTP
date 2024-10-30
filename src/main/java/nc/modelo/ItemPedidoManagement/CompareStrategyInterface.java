/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nc.modelo.ItemPedidoManagement;

import nc.modelo.ItemPedido;

/**
 *
 * @author nicol
 */
public interface CompareStrategyInterface {
    public int compareTo(ItemPedido otherPedido);
}
