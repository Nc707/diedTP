package com.deso.etapa_final.model.interfaces;

public interface Observable {
        public void addObserver(Observer observer);
        public void removeObserver(Observer observer);
        public void notifyObservers();
}
