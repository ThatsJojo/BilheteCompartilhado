
package util;

import java.util.ArrayList;


public class Grafo <V>{
    
    private final ArrayList<V> vertices;
    private final ArrayList<Aresta> arestas;
    private final ArrayList<ArrayList<Aresta>> bucketArestas;

    public Grafo() {
        vertices = new ArrayList();
        arestas = new ArrayList();
        bucketArestas = new ArrayList();
    }
    
    public void addVertice(V vertice) throws NotVerticeException{
        if(vertice == null)
            throw new NotVerticeException();
        if(vertices.contains(vertice))
            return;
        vertices.add(vertice);
        bucketArestas.add(new ArrayList());
    }
    
    public boolean containsVertice(V vertice){
        return vertices.contains(vertice);
    }
    
    public ArrayList<V> vertices(){
        ArrayList<V> ret = new ArrayList();
        vertices.forEach((v) -> {
            ret.add(v);
        });
        return ret.isEmpty()?null:ret;
    }
    
    //Metodo para adicionar arrestas na lista de arestas
    public void addAresta(V origem, V destino, Comparable2 peso) throws NotVerticeException{
        int indexOrigem = vertices.indexOf(origem);
        int indexDestino = vertices.indexOf(destino);
        if(indexOrigem < 0 || indexDestino < 0)
            throw new NotVerticeException();
        Aresta a = new Aresta(vertices.get(indexOrigem), vertices.get(indexDestino), peso);
        arestas.add(a);
        bucketArestas.get(indexOrigem).add(a);
    }    
    
    //Metodo que irá retornar uma lista de adjacencias apartir de uma origem
    public ArrayList<V> adjacentesDe(V origem) throws NotVerticeException{
        int indexOrigem = vertices.indexOf(origem);
        if(indexOrigem < 0)
            throw new NotVerticeException();
        ArrayList<V> ret = new ArrayList();
        for(Aresta a : bucketArestas.get(indexOrigem)){
            ret.add(a.destino);
        }
        return ret.isEmpty() ? null : ret;
    }

    public ArrayList<Aresta> arestasEntre(V origem, V destino) throws NotVerticeException{
        ArrayList<Aresta> ret = new ArrayList();
        int indexOrigem = vertices.indexOf(origem);
        int indexDestino = vertices.indexOf(destino);
        if(indexOrigem<0 || indexDestino<0)
            throw new NotVerticeException();
        bucketArestas.get(indexOrigem).forEach((a)->{
            if(a.destino.equals(destino) )
                ret.add(a);
        });
        return ret.isEmpty()?null:ret;
    }

    //Método que verifica se extiste uma aresta entre os vertices
    public boolean existeAresta(V origem, V destino) throws NotVerticeException{ 
        int indexOrigem = vertices.indexOf(origem);
        int indexDestino = vertices.indexOf(destino);
        if(indexOrigem < 0 || indexDestino < 0)
            throw new NotVerticeException();
        for(Aresta a : bucketArestas.get(indexOrigem)){
            if( a.origem.equals(origem)&& a.destino.equals(destino) ){
                return true;
            }
        }
        return false;
    }

    public int grauDoVertice(V vertice) throws NotVerticeException{
        int indexOrigem = vertices.indexOf(vertice);
        if(indexOrigem<0)
            throw new NotVerticeException();
        int num = 0;
        for(Aresta a: arestas){
            if(a.destino.equals(vertice)){
                num++;
            }
        }
        return num;
    }

    public int numeroDeArestas(){
        return arestas.size();
    }

    public int numeroDeVertices(){
        return vertices.size();
    }
    
    public ArrayList<ArrayList<Aresta>> caminhos(V origem, V destino){
        return null;
    }
    

    public class Aresta {
        
        private V origem;
        private V destino;
        private Comparable2 peso;

        public Aresta(V origem, V destino, Comparable2 peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public V getOrigem() {
            return origem;
        }

        public void setOrigem(V origem) {
            this.origem = origem;
        }

        public V getDestino() {
            return destino;
        }

        public void setDestino(V destino) {
            this.destino = destino;
        }

        public Comparable2 getPeso() {
            return peso;
        }

        public void setPeso(Comparable2 peso) {
            this.peso = peso;
        }
        
       
    }
    
    
}
