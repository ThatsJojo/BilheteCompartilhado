package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import util.Aresta;

public class Voo implements Aresta<Aeroporto>{
    private final Aeroporto origem;
    private final Aeroporto destino;
    private int id; 
    private int numeroDePassageiros;
    private final HashMap<Integer, Passageiro> passageiros;
    private final HashMap<Integer, Passagem> passagens;
    private final double precoBase;
    private final Companhia companhia;
    private static int count = 0;
    
    private static int getID(){
        return count++;
    }
    
    public Voo(Aeroporto origem, Aeroporto destino, int numeroDePassageiros, double precoBase, Companhia companhia) {
            this.origem = origem;
            this.companhia = companhia;
            this.destino = destino;
            this.id = Voo.getID();
            this.numeroDePassageiros = numeroDePassageiros;
            this.passageiros = new HashMap();
            this.passagens = new HashMap();
            this.precoBase = precoBase;
    }

    @Override
    public Aeroporto getOrigem() {
            return origem;
    }

    @Override
    public Aeroporto getDestino() {
            return destino;
    }

    public Companhia getCompanhia() {
        return companhia;
    }
    
    
    
    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public int getNumeroDePassageiros() {
            return numeroDePassageiros;
    }

    public void setNumeroDePassageiros(int numeroDePassageiros) {
            this.numeroDePassageiros = numeroDePassageiros;
    }

    public HashMap<Integer, Passageiro> getPassageiros() {
            return passageiros;
    }

    public void cadastrarPassageiro(Passageiro e){
        passageiros.put(e.getIdPassageiro(), e);
    }

    public HashMap<Integer, Passagem> getPassagens() {
            return passagens;
    }

    public void cadastrarPassagem(Passagem p){
        passagens.put(p.getId(), p);
    }

    public double getPrecoBase() {
            return precoBase;
    }

    @Override
    public Double getPeso() {
        return this.precoBase;
    }

    @Override
    public int compareTo(Object t) {
        if (! (t instanceof Voo))
            return Integer.MIN_VALUE ;
        return this.id - ((Voo)t).id;
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Voo other = (Voo) obj;
        return this.id == other.id;
    }

    public void removerPassgaem(Passagem p, int idViagemAtual) {
        passageiros.remove(p.getPassageiro().getIdPassageiro());
        p.getPassageiro().getViagens(idViagemAtual).removePassagem(p);
        passagens.remove(p.getId());
    }
    
    
    
	
}
