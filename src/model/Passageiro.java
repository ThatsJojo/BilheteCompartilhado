package model;

import java.util.LinkedList;

public class Passageiro {
	
	private String nome;
	private LinkedList<Passagem> passagensP;
	
	public Passageiro(String nome) {
		this.nome = nome;
		this.passagensP = new LinkedList<Passagem>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinkedList<Passagem> getPassagensP() {
		return passagensP;
	}

	public void setPassagensP(LinkedList<Passagem> passagensP) {
		this.passagensP = passagensP;
	}
	
	
}
