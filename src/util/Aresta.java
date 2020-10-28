package util;

public interface Aresta<V> extends Comparable{
    public V getOrigem();
    public V getDestino();
    public Double getPeso();
}
