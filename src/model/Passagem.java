package model;

public class Passagem {
	
    private Voo voo;
    private Passageiro passageiro;
    private final double preco;
    private final int id;
    private static int count = 0;
    private static int getID(){
        return count++;
    }

    public Passagem(Voo voo, Passageiro passageiro) {
            this.voo = voo;
            this.passageiro = passageiro;
            this.preco = voo.getPrecoBase();
            this.id = Passagem.getID();
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

    public int getId() {
        return id;
    }
}
