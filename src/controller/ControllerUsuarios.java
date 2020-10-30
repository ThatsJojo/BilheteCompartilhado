package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.Companhia;
import model.Passageiro;

public class ControllerUsuarios {
    private final HashMap<Integer, Passageiro> passageiros;
    private final HashMap<Integer, Companhia> companhias;
    private static ControllerUsuarios instance;
    
    public static synchronized ControllerUsuarios getInstance(){
        if(instance == null)
            instance = new ControllerUsuarios();
        return instance;
    }
    

    private ControllerUsuarios() {
        passageiros = new HashMap();
        companhias = new HashMap();
    }
    
    public Companhia addCompanhia(String nome){
        Companhia c = new Companhia(nome);
        companhias.put(c.getId(),c);
        return c;
    }
    
    public Passageiro addPassageiro(String nome){
        Passageiro p = new Passageiro(nome);
        passageiros.put(p.getIdPassageiro(), p);
        return p;
    }
    
    public Passageiro getPassageiro(int id){
        return passageiros.get(id);
    }
}
