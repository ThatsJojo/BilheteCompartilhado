package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import pbl.model.Conexao;
import pbl.util.Contador;

public class ThreadController {
    private static ArquivoController arquivoController;
    private static Conexao[] conexoes;
    private static LinkedList<Conexao> conexoesProntas;
    private static boolean[] filaPronta;
    private static ThreadController instance;
    
    public static synchronized ThreadController getInstance(){
        if(instance == null)
            instance = new ThreadController();
        return instance;
    }
    
    private ThreadController() {
        Random random = new Random();
        conexoesProntas = new LinkedList<>();
        int nconexoes = 3+random.nextInt(8);
        conexoes = new Conexao[nconexoes];
        filaPronta = new boolean[1];
        filaPronta[0] = false;
        for(int i =0; i<nconexoes;i++){
            conexoes[i] = new Conexao(i, random.nextInt(2), (5+random.nextInt(6)), (0+random.nextInt(10)));
        }
        adicionarThreads();
        inicializarThreads();
        try {
            arquivoController = ArquivoController.getInstance();
        } catch (IOException ex) {
            System.out.println("Problema na criação dos arquivos");
        }
    }
    
    private static void inicializarThreads(){
        new Thread(){
            @Override
            public void run(){
                Conexao ultima = null;
                while(!filaPronta[0]||!(conexoesProntas.isEmpty())){
                    try{    
                        Conexao a = conexoesProntas.removeFirst();
                        Thread.sleep(200);
                        a.start();
                        ultima = a;
                    }
                    catch(NoSuchElementException | InterruptedException ex){}
                }
                try {
                    Thread.sleep((ultima.getTempoExecucao()+4)*1000);
                    Contador.getInstance().interrupt();
                } catch (InterruptedException | NullPointerException ex) {}
            }
        }.start();
    }
    
    
    private static void adicionarThreads(){
        int nconexoes = conexoes.length;
        for(int i=0;i<nconexoes;i++){
            for(int j=0;j<nconexoes;j++){
                if(conexoes[j].compareTo(conexoes[i])>0){
                    Conexao temp = conexoes[i];
                    conexoes[i] = conexoes[j];
                    conexoes[j]=temp;
                }
            }
        }
        System.out.println("----------------------------------------------------------------------------------");
        for(Conexao c: conexoes){
            System.out.println("Conexão: "+c.getInternalID()+"   Tempo de chegada: "+c.getTempoInicio()+"     Tempo de execução: "+c.getTempoExecucao()+"     Escrita: "+c.getReadOrWrite());
        }
        System.out.println("----------------------------------------------------------------------------------\n");
        
        new Thread(){
            @Override
            public void run(){
                int idConexao=0;
                while((idConexao<nconexoes)&&(Contador.getInstance().getAbsoluteTime()<=conexoes[nconexoes-1].getTempoInicio())){
                    try {
                        do{
                            if(conexoes[idConexao].getTempoInicio() == Contador.getInstance().getAbsoluteTime()){
                                System.out.println("A Conexão: "+conexoes[idConexao]
                                        .getInternalID()+" foi  adicionada à fila de execução tempo: "
                                        +Contador.getInstance().getAbsoluteTime());
                                conexoesProntas.add(conexoes[idConexao]);
                                idConexao++;
                            }
                        }while((idConexao<nconexoes)&&conexoes[idConexao].getInternalID()==Contador.getInstance().getAbsoluteTime());
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        System.out.println("Erro ao inicializar conecções: "+ex);
                    }
                }
                filaPronta[0]=true;
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("                Todas as conexões foram adicionados à fila               ");
                System.out.println("----------------------------------------------------------------------------------");
            }
        }.start();
    }
}