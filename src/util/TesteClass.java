package util;

public class TesteClass<V> implements Aresta{
    
    private V origem;
    private V destino;
    private final Double peso;

    public TesteClass(V origem, V destino, Double peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }



    @Override
    public int compareTo(Object t) {
        if(!( t instanceof TesteClass))
            return Integer.MIN_VALUE;
        return (int) (this.peso-((TesteClass)t).peso);
    }

    @Override
    public Object getOrigem() {
        return origem;
    }

    @Override
    public Object getDestino() {
        return destino;
    }

    @Override
    public Double getPeso() {
        return peso;
    }
}
