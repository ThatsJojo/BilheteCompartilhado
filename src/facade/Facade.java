package facade;

import controller.ControladorDeVoos;
import controller.ControllerUsuarios;
import java.util.ArrayList;
import model.Aeroporto;
import model.Companhia;
import model.Passageiro;
import model.Passagem;
import model.Voo;
import Exceptions.NotPassException;
import Exceptions.NotPassageiroException;
import Exceptions.NotPathException;
import Exceptions.NotVerticeException;
import Exceptions.NotVooException;
import model.Viagem;

public class Facade {
    private static Facade facade;
    private final ArrayList<Passageiro> usuarios;
    private final ControladorDeVoos controladorDeVoos;
    private final ControllerUsuarios controllerUsuarios;
    
    
    private Facade() {
        usuarios = new ArrayList();
        controladorDeVoos = ControladorDeVoos.getInstance();
        controllerUsuarios = ControllerUsuarios.getInstance();
    }
    
    public static synchronized Facade getInstance(){
        if (facade == null)
            facade = new Facade();
        return facade;  
    }
    
    public Passageiro cadastrarPassageiro(String nome){
        return controllerUsuarios.addPassageiro(nome);
    }
    
    public Companhia cadastrarCompanhia(String nome){
        return controllerUsuarios.addCompanhia(nome);
    }
    
    public ArrayList<ArrayList<Aeroporto>> getRotas(Aeroporto origem, Aeroporto destino) throws NotVerticeException{
        return controladorDeVoos.getRotas(origem, destino);
    }
    
    public Viagem criarViagem(int idPassageiro, Aeroporto origem, Aeroporto destino){
        Passageiro p = controllerUsuarios.getPassageiro(idPassageiro);
        Viagem v = new Viagem(p, origem, destino);
        p.addViagem(v);
        return v;
    }
    
    public Passagem realizarCompra(int idPassageiro, Voo v, Viagem vi) throws NotPassageiroException, NotPassException{
        Passageiro p = controllerUsuarios.getPassageiro(idPassageiro);
        if(p == null)
            throw new NotPassageiroException();
        Passagem passagem = controladorDeVoos.criarPassagem(p, v);
        vi.addPassagem(passagem);
        return passagem;
    }
    
    public void devolverPassagem(Passagem p, int idViagemAtual){
        controladorDeVoos.devolverPassagem(p, idViagemAtual);
        
    }
    
    public ArrayList<Voo> getVoos(Aeroporto a1, Aeroporto a2) throws NotVerticeException, NotVooException{
        return controladorDeVoos.getVoos(a1, a2);
    }
    
    public Voo cadastrarVoo(Aeroporto a1, Aeroporto a2, int numeroDePassageiros, double precoBase, Companhia companhia) throws NotVerticeException{
        return controladorDeVoos.cadastrarVoo(a1, a2, numeroDePassageiros, precoBase, companhia);
    }
    
    public Aeroporto cadastrarAeroporto(String nome) throws NotVerticeException{
       return controladorDeVoos.cadastrarAeroporto(nome);
    }
    
    public ArrayList<Voo> menorCaminho(Aeroporto origem, Aeroporto destino) throws NotPathException{
       return controladorDeVoos.menorCaminho(origem, destino);
    }
}
