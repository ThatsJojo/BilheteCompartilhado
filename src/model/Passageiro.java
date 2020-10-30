package model;

import java.util.HashMap;
import java.util.LinkedList;

public class Passageiro {
    private final int idPassageiro;
    private final String nome;
    private final HashMap<Integer, Viagem> viagens;
    private static int count = 0;

    private static  synchronized int genID(){
        return count++;
    }

    public Passageiro(String nome) {
        viagens= new HashMap();
        this.idPassageiro = Passageiro.genID();
        this.nome = nome;
    }

    public int getIdPassageiro() {
        return this.idPassageiro;
    }

    public String getNome() {
        return nome;
    }

    public Viagem getViagens(int idViagem) {
        return viagens.get(idViagem);
    }

    public void addViagem(Viagem v) {
        viagens.put(v.getId(), v);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idPassageiro;
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
        final Passageiro other = (Passageiro) obj;
        return this.idPassageiro == other.idPassageiro;
    }
    
    
}
