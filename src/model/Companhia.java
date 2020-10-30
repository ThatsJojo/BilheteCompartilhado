package model;
import java.util.ArrayList;

public class Companhia {
    private static int count = 0;
    private String nome;
    private final int id;
    private ArrayList<Voo> listaVoos;


    public Companhia(String nome) {
        listaVoos = new ArrayList();
        this.nome = nome;
        this.listaVoos = new ArrayList();
        this.id = Companhia.genID();
    }

    private static  synchronized int genID(){
        return count++;
    }

    public int getId() {
        return id;
    }



    public String getNome() {
            return nome;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public ArrayList<Voo> getListaVoos() {
            return listaVoos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Companhia other = (Companhia) obj;
        return this.id == other.id;
    }
}
