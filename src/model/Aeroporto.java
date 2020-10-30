package model;

import java.util.ArrayList;

public class Aeroporto {
    private final int id;
    private final String nome;
    private final ArrayList<Passageiro> passageiros;
    private static int count = 0;
    
    private static int getID(){
        return count++;
    }

    public Aeroporto(String nome) {
        this.id = Aeroporto.getID();
        this.nome = nome;
        passageiros = new ArrayList();
    }
    
    @Override
    public String toString(){
        return "Aeroporto"+id;
    }
    
    public void addPassageiro(Passageiro p){
        passageiros.add(p);
    }

    public ArrayList<Passageiro> getListaPassageiros() {
        return passageiros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aeroporto other = (Aeroporto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
