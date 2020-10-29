
package controller;

import java.util.ArrayList;
import model.Aeroporto;
import model.Passageiro;
import model.Passagem;
import model.Voo;
import util.Aresta;
import util.Grafo;
import util.NotPathException;
import util.NotVerticeException;
import util.Semaforo;


public class ControladorDeVoos {
    private static ControladorDeVoos controladorDeVoos;
    private final Grafo<Aeroporto> grafo;
    
    private ControladorDeVoos() {
        this.grafo = new Grafo();
    }
    
    public static synchronized ControladorDeVoos getInstance(){
        if (controladorDeVoos == null)
            controladorDeVoos = new ControladorDeVoos();
        return controladorDeVoos;  
    }
    
   
    public void cadastrarAeroporto(Aeroporto aeroporto) throws NotVerticeException{
        grafo.addVertice(aeroporto);
    }

    public void cadastrarVoo(Aeroporto a1, Aeroporto a2, Voo v) throws NotVerticeException{
        grafo.addAresta(v);
        Semaforo.getInstance().cadastrarRecurso(v, v.getNumeroDePassageiros());
    }

    public ArrayList<Aresta<Aeroporto>> menorCaminho(Aeroporto origem, Aeroporto destino) throws NotPathException{
        return grafo.menorCaminho(origem, destino);
    }
   
    public Passagem criarPassagem(Passageiro p, Voo v){
        Semaforo.getInstance().down(v);
        Passagem m = new Passagem(v, p);
        v.cadastrarPassageiro(p);
        v.cadastrarPassagem(m);
        return m;
    }
}
