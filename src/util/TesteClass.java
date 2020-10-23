package util;

public class TesteClass implements Weighable{
    
    private final Comparable conteudo;

    public TesteClass(Comparable conteudo) {
        this.conteudo = conteudo;
    }
    
    
    @Override
    public double peso() {
        return 1;
    }

    @Override
    public int compareTo(Object t) {
        return conteudo.compareTo(t);
    }
    
}
