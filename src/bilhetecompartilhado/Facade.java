package bilhetecompartilhado;

import controller.ControladorDeVoos;
import java.util.ArrayList;
import model.Aeroporto;
import model.Passageiro;
import model.Passagem;
import model.Voo;
import util.Aresta;
import util.NotPathException;
import util.NotVerticeException;

public class Facade {
    private static Facade facade;
    private final ArrayList<Passageiro> usuarios;
    private final ControladorDeVoos controladorDeVoos;
    
    
    private Facade() {
        usuarios = new ArrayList();
        controladorDeVoos = ControladorDeVoos.getInstance();
    }
    
    public static synchronized Facade getInstance(){
        if (facade == null)
            facade = new Facade();
        return facade;  
    }
    
    public void cadastrarUsuario(String nome, int horarioCompra){
        usuarios.add(new Passageiro(nome, horarioCompra));
    }
    
    public void realizarCompra(String nome, Aeroporto origem, Aeroporto destino){
        Passageiro atual = null;
        for(Passageiro p: usuarios){
            if(p.getNome().equals(nome)){
                atual = p;
                System.out.println("Comparando: "+p.getNome()+" e: "+nome);
            }
        }
        if(atual == null)
            return;
        atual.realizarCompra(origem, destino);
    }
    
    public void cadastrarVoo(Aeroporto a1, Aeroporto a2, Voo v) throws NotVerticeException{
        controladorDeVoos.cadastrarVoo(a1, a2, v);
    }
    
    public void cadastrarAeroporto(Aeroporto aeroporto) throws NotVerticeException{
       controladorDeVoos.cadastrarAeroporto(aeroporto);
    }
    
    public ArrayList<Aresta<Aeroporto>> menorCaminho(Aeroporto origem, Aeroporto destino) throws NotPathException{
       return controladorDeVoos.menorCaminho(origem, destino);
    }
    
    public Passagem criarPassagem(Passageiro p, Voo v){
        return controladorDeVoos.criarPassagem(p, v);
    }
    
}
