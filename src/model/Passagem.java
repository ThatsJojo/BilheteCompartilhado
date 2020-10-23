package model;

public class Passagem {
	
	private Voo voo;
	private Passageiro passageiro;
	private double preco;
	private String classe;
	
	public Passagem(Voo voo, Passageiro passageiro,String classe) {
		this.voo = voo;
		this.passageiro = passageiro;
		this.preco = voo.getPrecoBase();
		this.classe = classe;
		
		switch (classe) {
		case "luxo" : 
			this.preco = voo.getPrecoBase() + 200;
			break; 
		
		case "executiva" :	
			this.preco  = voo.getPrecoBase() + 100;
			break;
		
		case "comum" :
			this.preco  = voo.getPrecoBase() + 50;
			break;
			
		default :
			this.preco = voo.getPrecoBase();
		}
	}
	
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
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
