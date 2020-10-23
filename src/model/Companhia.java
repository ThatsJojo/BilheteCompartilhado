package model;
import java.util.LinkedList;

public class Companhia {
	
	private String nome;
	private LinkedList<Voo> listaVoos;
	
	public Companhia(String nome, LinkedList<Voo> listaVoos) {
		this.nome = nome;
		this.listaVoos = listaVoos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinkedList<Voo> getListaVoos() {
		return listaVoos;
	}

	public void setQtdVoos(LinkedList<Voo> listaVoos) {
		this.listaVoos = listaVoos;
	}
	
}
