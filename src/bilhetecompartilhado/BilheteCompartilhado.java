package bilhetecompartilhado;

import controller.ControladorDeVoos;
import java.util.ArrayList;
import model.Aeroporto;
import model.Voo;
import util.Aresta;
import util.Grafo;
import util.NotPathException;
import util.NotVerticeException;

public class BilheteCompartilhado {
    public static void main(String[] args ){
    	ControladorDeVoos gerenciadorVoos = new ControladorDeVoos();
        
        
        Aeroporto v0 = new Aeroporto("0");
        Aeroporto v1 = new Aeroporto("1");
        Aeroporto v2 = new Aeroporto("2");
        Aeroporto v3 = new Aeroporto("3");
        Aeroporto v4 = new Aeroporto("4");
        Aeroporto v5 = new Aeroporto("5");
        Aeroporto v6 = new Aeroporto("6");
        Aeroporto v7 = new Aeroporto("7");
        Aeroporto v8 = new Aeroporto("8");
        try{
            gerenciadorVoos.cadastrarAeroporto(v0);
            gerenciadorVoos.cadastrarAeroporto(v1);
            gerenciadorVoos.cadastrarAeroporto(v2);
            gerenciadorVoos.cadastrarAeroporto(v3);
            gerenciadorVoos.cadastrarAeroporto(v4);
            gerenciadorVoos.cadastrarAeroporto(v5);
            gerenciadorVoos.cadastrarAeroporto(v6);
            gerenciadorVoos.cadastrarAeroporto(v7);
            gerenciadorVoos.cadastrarAeroporto(v8);
        }catch(NotVerticeException e){
            System.out.println("Deu ruim, meu bom.");
        }
        Voo peso01 = new Voo(v0, v1, 1, 0, 4);
        Voo peso07 = new Voo(v0, v7, 1, 0, 8);
        Voo peso12 = new Voo(v1, v2, 1, 0, 8);
        Voo peso17 = new Voo(v1, v7, 1, 0, 11);
        Voo peso78 = new Voo(v7, v8, 1, 0, 7);
        Voo peso76 = new Voo(v7, v6, 1, 0, 1);
        Voo peso68 = new Voo(v6, v8, 1, 0, 6);
        Voo peso28 = new Voo(v2, v8, 1, 0, 2);
        Voo peso25 = new Voo(v2, v5, 1, 0, 4);
        Voo peso65 = new Voo(v6, v5, 1, 0, 2);
        Voo peso23 = new Voo(v2, v3, 1, 0, 7);
        Voo peso53 = new Voo(v5, v3, 1, 0, 14);
        Voo peso34 = new Voo(v3, v4, 1, 0, 9);
        Voo peso54 = new Voo(v5, v4, 1, 0, 10);


        try{
            gerenciadorVoos.cadastrarVoo(v0, v1, peso01);
            gerenciadorVoos.cadastrarVoo(v0, v7, peso07);
            gerenciadorVoos.cadastrarVoo(v1, v2, peso12);
            gerenciadorVoos.cadastrarVoo(v1, v7, peso17);
            gerenciadorVoos.cadastrarVoo(v7, v8, peso78);
            gerenciadorVoos.cadastrarVoo(v7, v6, peso76);
            gerenciadorVoos.cadastrarVoo(v6, v8, peso68);
            gerenciadorVoos.cadastrarVoo(v2, v8, peso28);
            gerenciadorVoos.cadastrarVoo(v2, v5, peso25);
            gerenciadorVoos.cadastrarVoo(v6, v5, peso65);
            gerenciadorVoos.cadastrarVoo(v2, v3, peso23);
            gerenciadorVoos.cadastrarVoo(v5, v3, peso53);
            gerenciadorVoos.cadastrarVoo(v3, v4, peso34);
            gerenciadorVoos.cadastrarVoo(v5, v4, peso54);
        }catch(NotVerticeException e){
            System.out.println("Deu ruim no dois.");
        }
        ArrayList<Aresta<Aeroporto>> caminho = null;
        try{
             caminho =  gerenciadorVoos.menorCaminho(v0, v4);
        }catch(NotPathException e){
            System.out.println("Não há caminhos entre "+ v0.toString() +" e "+v4.toString());
        }
        
        if(caminho!=null){
            for(int i = caminho.size()-1; i>=0;i--){
                Voo a = (Voo) caminho.get(i);
                System.out.println("Origem: "+a.getOrigem()+" Destino: "+a.getDestino()+" peso:"+a.getPeso());
            }
        }
       
    }
    
}
