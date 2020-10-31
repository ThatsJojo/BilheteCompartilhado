package view;

import facade.Facade;
import java.util.ArrayList;
import model.Aeroporto;
import model.Passageiro;
import model.Voo;
import util.Contador;
import Exceptions.NotPathException;
import Exceptions.NotVerticeException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Companhia;
import model.Viagem;

public class BilheteCompartilhado {
    public static void main(String[] args ){
        Facade f = Facade.getInstance();
        Companhia companhia = f.cadastrarCompanhia("Companhia Braba");
        Companhia companhia1 = f.cadastrarCompanhia("Companhia Barril");
        Companhia companhia2 = f.cadastrarCompanhia("Companhia Rajada");
        Contador clock = Contador.getInstance();
        final int TEMPOCOMPRA = 4;
        Aeroporto v0 = null, v1 = null, v2 = null, v3 = null, v4 = null, v5 = null, v6 = null, v7 = null, v8 = null;
        try {
            v0 = f.cadastrarAeroporto("0");
            v1 = f.cadastrarAeroporto("1");
            v2 = f.cadastrarAeroporto("2");
            v3 = f.cadastrarAeroporto("3");
            v4 = f.cadastrarAeroporto("4");
            v5 = f.cadastrarAeroporto("5");
            v6 = f.cadastrarAeroporto("6");
            v7 = f.cadastrarAeroporto("7");
            v8 = f.cadastrarAeroporto("8");
        } catch (NotVerticeException ex) {
            
        }
        for(int i = 0; i<15; i++){
            Passageiro p = f.cadastrarPassageiro("Passageiro "+i);
        }
        try {
            Voo peso01 = f.cadastrarVoo(v0, v1, 5, 4, companhia);
            Voo peso07 = f.cadastrarVoo(v0, v7, 1, 8, companhia);
            Voo peso12 = f.cadastrarVoo(v1, v2, 5, 8, companhia);
            Voo peso17 = f.cadastrarVoo(v1, v7, 5, 11, companhia);
            Voo peso78 = f.cadastrarVoo(v7, v8, 5, 7, companhia);
            Voo peso76 = f.cadastrarVoo(v7, v6, 1, 1, companhia);
            Voo peso68 = f.cadastrarVoo(v6, v8, 5, 6, companhia);
            Voo peso28 = f.cadastrarVoo(v2, v8, 5, 2, companhia);
            Voo peso25 = f.cadastrarVoo(v2, v5, 5, 4, companhia);
            Voo peso65 = f.cadastrarVoo(v6, v5, 1, 2, companhia);
            Voo peso23 = f.cadastrarVoo(v2, v3, 5, 7, companhia);
            Voo peso53 = f.cadastrarVoo(v5, v3, 5, 14, companhia);
            Voo peso34 = f.cadastrarVoo(v3, v4, 5, 9, companhia);
            Voo peso54 = f.cadastrarVoo(v5, v4, 1, 10, companhia);
            
            Voo peso01c1 = f.cadastrarVoo(v0, v1, 5, 4, companhia1);
            Voo peso07c1 = f.cadastrarVoo(v0, v7, 1, 8, companhia1);
            Voo peso12c1 = f.cadastrarVoo(v1, v2, 5, 8, companhia1);
            Voo peso17c1 = f.cadastrarVoo(v1, v7, 5, 11, companhia1);
            Voo peso78c1 = f.cadastrarVoo(v7, v8, 5, 7, companhia1);
            Voo peso76c1 = f.cadastrarVoo(v7, v6, 1, 1, companhia1);
            Voo peso68c1 = f.cadastrarVoo(v6, v8, 5, 6, companhia1);
            Voo peso28c1 = f.cadastrarVoo(v2, v8, 5, 2, companhia1);
            Voo peso25c1 = f.cadastrarVoo(v2, v5, 5, 4, companhia1);
            Voo peso65c1 = f.cadastrarVoo(v6, v5, 1, 2, companhia1);
            Voo peso23c1 = f.cadastrarVoo(v2, v3, 5, 7, companhia1);
            Voo peso53c1 = f.cadastrarVoo(v5, v3, 5, 14, companhia1);
            Voo peso34c1 = f.cadastrarVoo(v3, v4, 5, 9, companhia1);
            Voo peso54c1 = f.cadastrarVoo(v5, v4, 1, 10, companhia1);
            
            
            Voo peso01c2 = f.cadastrarVoo(v0, v1, 5, 4, companhia2);
            Voo peso07c2 = f.cadastrarVoo(v0, v7, 1, 8, companhia2);
            Voo peso12c2 = f.cadastrarVoo(v1, v2, 5, 8, companhia2);
            Voo peso17c2 = f.cadastrarVoo(v1, v7, 5, 11, companhia2);
            Voo peso78c2 = f.cadastrarVoo(v7, v8, 5, 7, companhia2);
            Voo peso76c2 = f.cadastrarVoo(v7, v6, 1, 1, companhia2);
            Voo peso68c2 = f.cadastrarVoo(v6, v8, 5, 6, companhia2);
            Voo peso28c2 = f.cadastrarVoo(v2, v8, 5, 2, companhia2);
            Voo peso25c2 = f.cadastrarVoo(v2, v5, 5, 4, companhia2);
            Voo peso65c2 = f.cadastrarVoo(v6, v5, 1, 2, companhia2);
            Voo peso23c2 = f.cadastrarVoo(v2, v3, 5, 7, companhia2);
            Voo peso53c2 = f.cadastrarVoo(v5, v3, 5, 14, companhia2);
            Voo peso34c2 = f.cadastrarVoo(v3, v4, 5, 9, companhia2);
            Voo peso54c2 = f.cadastrarVoo(v5, v4, 1, 10, companhia2);
        } catch (NotVerticeException ex) {
            
        }
        
//        ArrayList<Voo> caminho = null;
//        try{
//             caminho =  f.menorCaminho(v0, v4);
//        }catch(NotPathException e){
//            System.out.println("Não há caminhos entre "+ v0.toString() +" e "+v4.toString());
//        }
//        
//        caminho.forEach((a) ->{
//            System.out.println("Origem: "+a.getOrigem()+" Destino: "+a.getDestino()+" peso:"+a.getPeso());
//        });
        try {
            ArrayList<ArrayList<Aeroporto>> rotas = f.getRotas(v0, v4);
            
            for(int i = 0; i<rotas.size(); i++){
                System.out.println("Rota "+i+": ");
                ArrayList<Aeroporto> t = rotas.get(i);
                t.forEach((Aeroporto aeroporto) -> {
                    System.out.println("Aeroporto: "+aeroporto.toString());
                });
            }
        } catch (NotVerticeException ex) {}

        ViewPassageiro clientes[] = new ViewPassageiro[10];
        for(int i = 0; i<10; i++){
            clientes[i] = new ViewPassageiro("Cliente "+i, TEMPOCOMPRA+1);
            clientes[i].realizarCompra(v0, v4);
        }
        
        System.out.println("As compras serão iniciadas em: ");
        while(Contador.getInstance().getAbsoluteTime()<TEMPOCOMPRA+4){
            try {
                Thread.sleep(250);
                if(Contador.getInstance().getAbsoluteTime()<TEMPOCOMPRA){
                    System.out.print(""+(TEMPOCOMPRA-Contador.getInstance().getAbsoluteTime()));
                    for(int i = 0; i<3;i++){
                    Thread.sleep(250);
                        System.out.print(".");
                    }
                    System.out.println(" ");
                }else{
                    //System.out.println("\n");
                }
                
            } catch (InterruptedException ex) {}
        }
        System.out.println("\n \n****************************************");
        for(int i = 0; i<10; i++){
            Viagem v = clientes[i].getViagem();
            System.out.println("Compras do "+v.getDono().getNome());
            v.getPassagens().forEach((t, u) -> {
                System.out.println("ID passagem " +u.getId()+" ID Voo "+u.getVoo().getId()+" Origem "+u.getVoo().getOrigem()+" Destino "+u.getVoo().getDestino()+" Companhia ");
            });
            System.out.println("****************************************");
        }
        
        Contador.getInstance().stop();
    }
}
