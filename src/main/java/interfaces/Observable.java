package interfaces;

import nc.diedtp.Cliente;

public interface Observable {
    void addSubscriptor(Observer cliente);
    void notificarSubs();
}
