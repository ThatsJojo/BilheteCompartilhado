package model;

import java.util.LinkedList;

public class Voo {
	
	private String origem;
	private String destino;
	private int id; 
	private int numeroDePassageiros;
	private LinkedList<Passageiro> passageiros;
	private LinkedList<Passagem> passagens;
	private double precoBase;
	private String companhia;

	public Voo(String origem, String destino, int id, int numeroDePassageiros, double precoBase) {
		this.origem = origem;
		this.destino = destino;
		this.id = id;
		this.numeroDePassageiros = numeroDePassageiros;
		this.passageiros = new LinkedList<Passageiro>();
		this.passagens = new LinkedList<Passagem>();
		this.precoBase = precoBase;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
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
	
}
