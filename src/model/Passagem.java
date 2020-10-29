package model;

public class Passagem {
	
	private Voo voo;
	private Passageiro passageiro;
	private double preco;
	
	public Passagem(Voo voo, Passageiro passageiro) {
		this.voo = voo;
		this.passageiro = passageiro;
		this.preco = voo.getPrecoBase();
	}
        
	public Voo getVoo() {
		return voo;
	}
	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	public Passageiro getPassageiro() {
		return passageiro;
	}
	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}
	public double getPreco() {
		return preco;
	}
	
}
