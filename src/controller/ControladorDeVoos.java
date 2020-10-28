
package controller;

import java.util.ArrayList;
import model.Aeroporto;
import model.Voo;
import util.Aresta;
import util.Grafo;
import util.NotPathException;
import util.NotVerticeException;
import util.Weighable;


public class ControladorDeVoos {

    Grafo<Aeroporto> grafo;
    public ControladorDeVoos() {
        this.grafo = new Grafo();
    }
    
   
   public void cadastrarAeroporto(Aeroporto aeroporto) throws NotVerticeException{
       grafo.addVertice(aeroporto);
   }
   
   public void cadastrarVoo(Aeroporto a1, Aeroporto a2, Voo v) throws NotVerticeException{
       grafo.addAresta(v);
   }
   
   public ArrayList<Aresta<Aeroporto>> menorCaminho(Aeroporto origem, Aeroporto destino) throws NotPathException{
       return grafo.menorCaminho(origem, destino);
   }
       
}
