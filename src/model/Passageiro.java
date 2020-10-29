package model;

import bilhetecompartilhado.Facade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import util.Aresta;
import util.Contador;
import util.NotPathException;

public class Passageiro extends Thread {
	int idPassageiro;
	private final String nome;
	private final LinkedList<Passagem> passagens;
        private final HashMap<Integer, Aeroporto> origemDesejo;
        private final HashMap<Integer, Aeroporto> destinoDesejo;
        private static int count = 0;
        private final int horarioCompra;
        
        private int compraAtual;

        private static  synchronized int genID(){
            return count++;
        }
        
        public void realizarCompra(Aeroporto origemDesejado, Aeroporto destinoDesejado){
            origemDesejo.put(compraAtual, origemDesejado);
            destinoDesejo.put(compraAtual, destinoDesejado);
            System.out.println("REalizando Compra");
            this.start();
        }
        
        
        public Passageiro(String nome, int horarioCompra) {
            this.passagens = new LinkedList();
            this.idPassageiro = Passageiro.genID();
            this.nome = nome;
            origemDesejo = new HashMap();
            destinoDesejo = new HashMap();
            compraAtual = 0;
            this.horarioCompra = horarioCompra;
        }

        public int getIdPassageiro() {
            return this.idPassageiro;
        }

	public String getNome() {
            return nome;
	}

	public LinkedList<Passagem> getPassagens() {
            return passagens;
	}

	public void addPassagem(Passagem p) {
            passagens.add(p);
	}
        
        @Override
        public void run(){
            while(Contador.getInstance().getAbsoluteTime()<horarioCompra){
                //System.out.println("Tentando");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {}
            }
            Facade f = Facade.getInstance();
            int x = compraAtual;
            Aeroporto origem = origemDesejo.get(compraAtual);
            Aeroporto destino = destinoDesejo.get(compraAtual);
            ArrayList<Aresta<Aeroporto>> caminho = null;
            try {
                caminho = f.menorCaminho(origem, destino);
            } catch (NotPathException ex) {
                System.out.println("Passageiro "+this.nome+"não encontrou caminho entre os Aeroportos "+origem + " e "+destino);
                this.stop();
            }
            for(Aresta<Aeroporto> v: caminho){
                passagens.add(f.criarPassagem(this, (Voo) v));
                System.out.println("Passageiro "+this.nome+" comprou uma passagem entre "+v.getOrigem().toString()+" e "+v.getDestino()+
                        "Com sucesso.");
            }
            System.out.println("Passageiro "+this.nome+" finalizou a compra de passagens entre "+origem.toString()+" e "+destino.toString());
            compraAtual++;
        }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Passageiro other = (Passageiro) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
        
        
	
	
}
