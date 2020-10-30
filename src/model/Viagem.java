package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Viagem {
    private final int id;
    private final Passageiro dono;
    private final HashMap<Integer, Passagem> passagens;
    private final Aeroporto origem;
    private final Aeroporto destino;
    private ArrayList<Voo> rota;
    private static int count = 0;
    
    private static int getID(){
        return count++;
    }

    public Viagem(Passageiro dono, Aeroporto origem, Aeroporto destino) {
        this.id = Viagem.getID();
        this.dono = dono;
        this.origem = origem;
        this.destino = destino;
        passagens = new HashMap();
    }

    public void setRota(ArrayList<Voo> rota) {
        this.rota = rota;
    }

    public int getId() {
        return id;
    }

    public Passageiro getDono() {
        return dono;
    }

    public HashMap<Integer, Passagem> getPassagens() {
        return passagens;
    }
    
    public void addPassagem (Passagem p){
        passagens.put(p.getId(), p);
    }
    
    public void removePassagem(Passagem p){
        passagens.remove(p.getId());
    }
    
    public Aeroporto getOrigem() {
        return origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public ArrayList<Voo> getRota() {
        return rota;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Viagem other = (Viagem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public boolean containsTrecho(Aeroporto origem, Aeroporto destino) {
        final boolean ret[] = {false};
        passagens.forEach((Integer t, Passagem u) -> {
            if(u.getVoo().getOrigem().equals(origem)&&u.getVoo().getDestino().equals(destino))
                ret[0] = true;
        });
        return ret[0];
    }
    
    public Passagem getPassagem(Aeroporto origem, Aeroporto destino) {
        final Passagem ret[] = new Passagem[1];
        passagens.forEach((Integer t, Passagem u) -> {
            if(u.getVoo().getOrigem().equals(origem)&&u.getVoo().getDestino().equals(destino))
                ret[0] = u;
        });
        return ret[0];
    }
    
    
    
}