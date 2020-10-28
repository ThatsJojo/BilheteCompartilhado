package model;

import java.util.LinkedList;
import util.Aresta;
import util.Weighable;

public class Voo implements Aresta<Aeroporto>{
	private Companhia dona;
	private Aeroporto origem;
	private Aeroporto destino;
	private int id; 
	private int numeroDePassageiros;
	private LinkedList<Passageiro> passageiros;
	private LinkedList<Passagem> passagens;
	private double precoBase;
	private String companhia;

	public Voo(Aeroporto origem, Aeroporto destino, int id, int numeroDePassageiros, double precoBase) {
		this.origem = origem;
		this.destino = destino;
		this.id = id;
		this.numeroDePassageiros = numeroDePassageiros;
		this.passageiros = new LinkedList<Passageiro>();
		this.passagens = new LinkedList<Passagem>();
		this.precoBase = precoBase;
	}

	public Aeroporto getOrigem() {
		return origem;
	}

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

	public void setPassageiros(LinkedList<Passageiro> passageiros) {
		this.passageiros = passageiros;
	}

	public LinkedList<Passagem> getPassagens() {
		return passagens;
	}

	public void setPassagens(LinkedList<Passagem> passagens) {
		this.passagens = passagens;
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
	
}
