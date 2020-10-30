package model;

import java.util.HashMap;
import facade.Facade;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PiveteDosDeadLock {
    private final HashMap<Passageiro, Viagem> viagens;
    private final HashMap<Passageiro, boolean[]> roubados;
    private final LinkedList<Passageiro> passageirosProblematicos;
    private static PiveteDosDeadLock piveteDosDeadLock;
    private boolean using;
    
    private PiveteDosDeadLock(){
        viagens = new HashMap();
        passageirosProblematicos = new LinkedList();
        roubados = new HashMap();
        using = false;
    }
    
    public static synchronized PiveteDosDeadLock getInstance(){
        if(piveteDosDeadLock == null)
            piveteDosDeadLock = new PiveteDosDeadLock();
        return piveteDosDeadLock;
    }
    
    private synchronized boolean getUsing(){
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {}
        return using;
    }

    public void cadastrarProblematico(Passageiro p, Viagem v){
        if(!getUsing()){
            using = true;
            passageirosProblematicos.addLast(p);
            viagens.put(p, v);
            boolean roubadoStatus[] = {false};
            roubados.put(p, roubadoStatus);
        }
        using = false;
    }
    
    public Voo roubarRecursos(Aeroporto origem, Aeroporto destino, Passageiro passageiro) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {}
        for(int i = 0;getUsing()||(!passageirosProblematicos.getFirst().equals(passageiro)&&!roubados.get(passageiro)[0]&&i<20);i++ ){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Erro no controlador de DeadLocks");
            }
        }
        using = true;
        if(roubados.get(passageiro)[0]){
            passageirosProblematicos.remove(passageiro);
            viagens.remove(passageiro);
            roubados.remove(passageiro);
            using = false;
            return null;
        }
        Iterator it = passageirosProblematicos.iterator();
        while(it.hasNext()) {
            Passageiro p = (Passageiro) it.next();
            if(!p.equals(passageiro)){
                Viagem v = viagens.get(p);
                Passagem passagem = v.getPassagem(origem, destino);
                if(passagem!=null){
                    passageirosProblematicos.remove(passageiro);
                    viagens.remove(passageiro);
                    roubados.remove(passageiro);
                    Facade.getInstance().devolverPassagem(passagem, v.getId());
                    boolean replace[] = {true};
                    roubados.replace(p, replace);
                    using = false;
                    return passagem.getVoo();
                }
            }
        }
        passageirosProblematicos.remove(passageiro);
        viagens.remove(passageiro);
        roubados.remove(passageiro);
        using = false;
        return null;
    }
    
    
}
