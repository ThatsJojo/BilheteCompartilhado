package util;

public class TesteClass implements Weighable{
    
    private final Comparable conteudo;
    private final Double peso;

    public TesteClass(Comparable conteudo, Double peso) {
        this.conteudo = conteudo;
        this.peso = peso;
    }
    
    
    @Override
    public Double peso() {
        return peso;
    }

    @Override
    public int compareTo(Object t) {
        return conteudo.compareTo(t);
    }
    
}
