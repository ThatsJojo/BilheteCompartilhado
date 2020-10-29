package model;

import java.util.LinkedList;
import util.Aresta;

public class Voo implements Aresta<Aeroporto>{
	private Companhia dona;
	private final Aeroporto origem;
	private final Aeroporto destino;
	private int id; 
	private int numeroDePassageiros;
	private LinkedList<Passageiro> passageiros;
	private LinkedList<Passagem> passagens;
	private final double precoBase;
	private String companhia;

	public Voo(Aeroporto origem, Aeroporto destino, int id, int numeroDePassageiros, double precoBase) {
		this.origem = origem;
		this.destino = destino;
		this.id = id;
		this.numeroDePassageiros = numeroDePassageiros;
		this.passageiros = new LinkedList<>();
		this.passagens = new LinkedList<>();
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

	public LinkedList<Passageiro> getPassageiros() {
		return passageiros;
	}
        
        public void cadastrarPassageiro(Passageiro e){
            passageiros.add(e);
        }

	public LinkedList<Passagem> getPassagens() {
		return passagens;
	}
        
        public void cadastrarPassagem(Passagem p){
            passagens.add(p);
        }

	public double getPrecoBase() {
		return precoBase;
	}

    @Override
    public Double getPeso() {
        return this.precoBase-this.numeroDePassageiros;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
	
}
