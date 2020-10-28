package model;

import java.util.LinkedList;

public class Aeroporto {
    private final String id;
    private final LinkedList<Companhia> companhias;
    private final LinkedList<Passageiro> passageiros;

    public Aeroporto(String id) {
        this.id = id;
        companhias = new LinkedList();
        passageiros = new LinkedList();
    }
    
    @Override
    public String toString(){
        return "Aeroporto"+id;
    }
    
    public void addCompanhia(Companhia c){
        companhias.add(c);
    }
    
    public void addPassageiro(Passageiro p){
        passageiros.add(p);
    }
    
    public LinkedList<Companhia> getListaCompanhias() {
        return companhias;
    }

    public LinkedList<Passageiro> getListaPassageiros() {
        return passageiros;
    }
}
