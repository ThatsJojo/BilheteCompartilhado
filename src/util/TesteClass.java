package util;

public class TesteClass implements Comparable2{
    
    private final Comparable conteudo;

    public TesteClass(Comparable conteudo) {
        this.conteudo = conteudo;
    }
    
    
    @Override
    public int absoluteCompare() {
        return 1;
    }

    @Override
    public int compareTo(Object t) {
        return conteudo.compareTo(t);
    }
    
}
