package model;

import java.util.LinkedList;

public class Aeroporto {

	private LinkedList<Companhia> listaCompanhias;
	private LinkedList<Passageiro> listaPassageiros;
	
	public Aeroporto() {
		this.listaCompanhias = listaCompanhias;
		this.listaPassageiros = listaPassageiros;
	}

	public LinkedList<Companhia> getListaCompanhias() {
		return listaCompanhias;
	}

	public void setListaCompanhias(LinkedList<Companhia> listaCompanhias) {
		this.listaCompanhias = listaCompanhias;
	}

	public LinkedList<Passageiro> getListaPassageiros() {
		return listaPassageiros;
	}

	public void setListaPassageiros(LinkedList<Passageiro> listaPassageiros) {
		this.listaPassageiros = listaPassageiros;
	}
}
