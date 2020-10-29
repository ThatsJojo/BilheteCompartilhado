package bilhetecompartilhado;

import java.util.ArrayList;
import model.Aeroporto;
import model.Passageiro;
import model.Voo;
import util.Aresta;
import util.Contador;
import util.NotPathException;
import util.NotVerticeException;

public class BilheteCompartilhado {
    public static void main(String[] args ){
        Facade f = Facade.getInstance();
        Contador clock = Contador.getInstance();
        Aeroporto v0 = new Aeroporto("0");
        Aeroporto v1 = new Aeroporto("1");
        Aeroporto v2 = new Aeroporto("2");
        Aeroporto v3 = new Aeroporto("3");
        Aeroporto v4 = new Aeroporto("4");
        Aeroporto v5 = new Aeroporto("5");
        Aeroporto v6 = new Aeroporto("6");
        Aeroporto v7 = new Aeroporto("7");
        Aeroporto v8 = new Aeroporto("8");
        
        
        for(int i = 0; i<15; i++){
            f.cadastrarUsuario("Passageiro "+i, 3);
            f.realizarCompra("Passageiro "+i, v0, v4);
            //System.out.println(p.getNome() + "  " + p.getIdPassageiro());
        }
        try{
            f.cadastrarAeroporto(v0);
            f.cadastrarAeroporto(v1);
            f.cadastrarAeroporto(v2);
            f.cadastrarAeroporto(v3);
            f.cadastrarAeroporto(v4);
            f.cadastrarAeroporto(v5);
            f.cadastrarAeroporto(v6);
            f.cadastrarAeroporto(v7);
            f.cadastrarAeroporto(v8);
        }catch(NotVerticeException e){
            System.out.println("Deu ruim, meu bom.");
        }
        Voo peso01 = new Voo(v0, v1, 1, 5, 4);
        Voo peso07 = new Voo(v0, v7, 2, 5, 8);
        Voo peso12 = new Voo(v1, v2, 3, 5, 8);
        Voo peso17 = new Voo(v1, v7, 4, 5, 11);
        Voo peso78 = new Voo(v7, v8, 5, 5, 7);
        Voo peso76 = new Voo(v7, v6, 6, 5, 1);
        Voo peso68 = new Voo(v6, v8, 7, 5, 6);
        Voo peso28 = new Voo(v2, v8, 8, 5, 2);
        Voo peso25 = new Voo(v2, v5, 9, 5, 4);
        Voo peso65 = new Voo(v6, v5, 10, 5, 2);
        Voo peso23 = new Voo(v2, v3, 11, 5, 7);
        Voo peso53 = new Voo(v5, v3, 12, 5, 14);
        Voo peso34 = new Voo(v3, v4, 13, 5, 9);
        Voo peso54 = new Voo(v5, v4, 14, 5, 10);


        try{
            f.cadastrarVoo(v0, v1, peso01);
            f.cadastrarVoo(v0, v7, peso07);
            f.cadastrarVoo(v1, v2, peso12);
            f.cadastrarVoo(v1, v7, peso17);
            f.cadastrarVoo(v7, v8, peso78);
            f.cadastrarVoo(v7, v6, peso76);
            f.cadastrarVoo(v6, v8, peso68);
            f.cadastrarVoo(v2, v8, peso28);
            f.cadastrarVoo(v2, v5, peso25);
            f.cadastrarVoo(v6, v5, peso65);
            f.cadastrarVoo(v2, v3, peso23);
            f.cadastrarVoo(v5, v3, peso53);
            f.cadastrarVoo(v3, v4, peso34);
            f.cadastrarVoo(v5, v4, peso54);
        }catch(NotVerticeException e){
            System.out.println("Deu ruim no dois.");
        }
        ArrayList<Aresta<Aeroporto>> caminho = null;
        try{
             caminho =  f.menorCaminho(v0, v4);
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
