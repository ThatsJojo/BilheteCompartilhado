package view;

import Exceptions.NotPassException;
import Exceptions.NotPassageiroException;
import facade.Facade;
import java.util.ArrayList;
import model.Aeroporto;
import model.Passageiro;
import model.Passagem;
import model.Voo;
import Exceptions.NotVerticeException;
import Exceptions.NotVooException;
import java.util.Random;
import model.PiveteDosDeadLock;
import model.Viagem;
import util.Contador;

public class ViewPassageiro extends Thread {
    private final Passageiro passageiro;
    private int idViagemAtual;
    private final int horarioCompra;
    
    public ViewPassageiro(String nome, int horarioCompra) {
        this.passageiro = Facade.getInstance().cadastrarPassageiro(nome);
        this.horarioCompra = horarioCompra;
    }

    public Viagem getViagem() {
        return passageiro.getViagens(idViagemAtual);
    }
    
    
    public void realizarCompra(Aeroporto origemDesejado, Aeroporto destinoDesejado){
        Viagem v = Facade.getInstance().criarViagem(this.passageiro.getIdPassageiro(), origemDesejado, destinoDesejado);
        this.idViagemAtual = v.getId();
        this.start();
    }
    
    @Override
    public void run(){
        while(Contador.getInstance().getAbsoluteTime()<horarioCompra){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {}
        }
        Facade f = Facade.getInstance();
        Viagem viagem = this.passageiro.getViagens(idViagemAtual);
        ArrayList<ArrayList<Aeroporto>> rotas = new ArrayList();
        try {
            rotas = f.getRotas(viagem.getOrigem(), viagem.getDestino());
            /*for(int i = 0; i<rotas.size(); i++){
                System.out.println("Rota "+i+": ");
                rotas.get(i).forEach((j) -> {
                    System.out.println(j.toString());
                });
            }*/
        } catch (NotVerticeException ex) {
            System.out.println("Erro interno. Não há voos entre os Aeroportos tomados. Não deveria acontecer.");
            this.stop();
        }
        for(int k = 0; k < rotas.size(); k++){
            System.out.println(this.passageiro.getNome()+" está viajando pela rota: "+k);
            boolean rotaLivre = true;
            ArrayList<Aeroporto> rota = rotas.get(k);
            ArrayList<Double> trechosRestantes = new ArrayList();
            for(int i = 0; i<rota.size()-1;i++){
                trechosRestantes.add(new Double(i));
            }
            for(int i = 0; rotaLivre&&!trechosRestantes.isEmpty();i++){
                int random = new Random().nextInt(trechosRestantes.size());
                int index = trechosRestantes.get(random).intValue();
                trechosRestantes.remove(random);
                Aeroporto origem = rota.get(index);
                Aeroporto destino = rota.get(index+1);
                ArrayList<Voo> voos = new ArrayList();
                Voo voo = null;
                try {
                    voos = f.getVoos(origem, destino);
                } catch (NotVerticeException ex) {
                    System.out.println("Erro interno. Não há voos entre os Aeroportos tomados. Não deveria acontecer.");
                    this.stop();
                } catch (NotVooException ex) {
                    System.out.println("Não foram encontrados voos entre "+origem+" e "+destino);
                    System.out.println("Erro interno. Não há voos entre os Aeroportos tomados. Não deveria acontecer.");
                    this.stop();
                }
                Passagem p = null;
                boolean comprando = true;
                boolean conseguiuComprar = false;
                for(int j = 0; comprando&&j<voos.size(); j++){
                    voo = voos.get(j);
                    if(!viagem.containsTrecho(voo.getOrigem(), voo.getDestino())){
                        try {
                            p = f.realizarCompra(this.passageiro.getIdPassageiro(), voo, viagem);
                            System.out.println(this.passageiro.getNome()+" comprou passagem para voo "+voo.getId()+" entre "+p.getVoo().getOrigem()+" e "+p.getVoo().getDestino()+" da companhia "+voo.getCompanhia().getNome()+" ID da passagem: "+p.getId());
                            comprando = false;
                            conseguiuComprar = true;
                        } catch (NotPassageiroException ex) {
                            System.out.println("Passageiro não cadastrado.");
                        } catch (NotPassException ex) {
                            System.out.println(this.passageiro.getNome()+" não conseguiu comprar passagem para o voo" +voo.getId()+" Entre "+voo.getOrigem()+" e "+voo.getDestino()+" da companhia "+voo.getCompanhia().getNome()+".");
                            conseguiuComprar=false;
                        }
                    }else{
                        comprando = false;
                        conseguiuComprar = true;
                        System.out.println(this.passageiro.getNome()+" já possui passagem para voo entre"+origem+" e "+destino+".");
                    }
                }
                if(!conseguiuComprar){
                    System.out.println(this.passageiro.getNome()+" está esperando por recursos travados por deadlock. Origem do voo desejado: "+origem+" destino:  "+destino);
                    PiveteDosDeadLock.getInstance().cadastrarProblematico(passageiro, viagem);
                    voo = PiveteDosDeadLock.getInstance().roubarRecursos(origem, destino, this.passageiro);
                    if(voo == null){
                        System.out.println(this.passageiro.getNome()+" não conseguiu recursos travados por DeadLock" );
                        final ArrayList<Aeroporto> proxRota = k<rotas.size()-1?rotas.get(k+1):null;
                        ArrayList<Passagem> passagensRemovidas = new ArrayList();
                        if(proxRota==null) {
                            viagem.getPassagens().forEach((Integer t, Passagem u) -> {
                                passagensRemovidas.add(u);
                            });
                        }else{
                            viagem.getPassagens().forEach((Integer t, Passagem u) -> {
                                boolean contains = false;
                                Aeroporto o = u.getVoo().getOrigem();
                                Aeroporto d = u.getVoo().getDestino();
                                for(int m = 0; m<proxRota.size();m++){
                                    if(o.equals(proxRota.get(m))&&d.equals(proxRota.get(m+1)))
                                        contains = true;
                                }
                                if(!contains){
                                    passagensRemovidas.add(u);
                                }
                            });
                        }
                        passagensRemovidas.forEach((u)->{
                            f.devolverPassagem(u, idViagemAtual);
                            System.out.println(this.passageiro.getNome()+" devolveu a passagem "+u.getId()+" para o voo" +u.getVoo().getId()+" Entre "+u.getVoo().getOrigem()+" e "+u.getVoo().getDestino()+" da companhia "+u.getVoo().getCompanhia().getNome()+".");
                        });
                        rotaLivre = false;
                    }else{
                        try {
                            p = f.realizarCompra(this.passageiro.getIdPassageiro(), voo, viagem);
                            System.out.println(this.passageiro.getNome()+" recebeu um voo que estava travado por deadLock. ID "+voo.getId()+" entre "+voo.getOrigem()+" e "+voo.getDestino()+" da companhia "+voo.getCompanhia().getNome()+" ID da passagem: "+p.getId());
                        } catch (NotPassageiroException | NotPassException ex){
                            rotaLivre = false;
                        }
                    }
                        
                }
            }
            if(rotaLivre){
                System.out.println("O passageiro "+this.passageiro.getNome()+" finalizou suas compras de passagens.");
                this.stop();
            }
        }
        System.out.println("O passageiro "+this.passageiro.getNome()+" não conseguiu realizar sua compra.");
    }
}
