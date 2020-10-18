
package util;

import java.util.ArrayList;
import java.util.Objects;



public class Grafo <V,P>{
    
    private final ArrayList<V> vertices;
    private final ArrayList<Aresta> arestas;

    public Grafo() {
        vertices = new ArrayList();
        arestas = new ArrayList();
    }
    
    public void addVertice(V vertice) throws NotVerticeException{
        if(vertice == null)
            throw new NotVerticeException();
        if(vertices.contains(vertice))
            return;
        vertices.add(vertice);
    }
    
    public boolean containsVertice(V vertice){
        return vertices.contains(vertice);
    }
    
    //Metodo para adicionar arrestas na lista de arestas
    public void addAresta(V origem, V destino, P peso) throws NotVerticeException{
        int indexOrigem = vertices.indexOf(origem);
        int indexDestino = vertices.indexOf(destino);
        
        if(indexOrigem < 0 || indexDestino < 0)
            throw new NotVerticeException();
        
        Aresta a = new Aresta(vertices.get(indexOrigem), vertices.get(indexDestino), peso);
        
        arestas.add(a);         
    }
    
    

    //Metodo que irá retornar uma lista de adjacencias apartir de uma origem
    public ArrayList<V> adjacentesDe(V origem) throws NotVerticeException{
        int indexOrigem = vertices.indexOf(origem);
        
        if(indexOrigem < 0)
            throw new NotVerticeException();
        
        ArrayList<V> ret = new ArrayList();
        
        for(Aresta a : arestas){
            if(a.origem.equals(origem)){
                if(!ret.contains(a.destino))
                    ret.add(a.destino);
            }
        }
        return ret.isEmpty() ? null : ret;
    }

    public ArrayList<Aresta> arestasEntre(V origem, V destino){
        ArrayList<Aresta> ret = new ArrayList();
        arestas.forEach((a)->{
            if(a.origem.equals(origem) && a.destino.equals(destino) )
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
        
        for(Aresta a : arestas){
            if( a.origem.equals(origem)&& a.destino.equals(destino) ){
                return true;
            }
        }
        return false;
    }

    public int grauDoVertice(V vertice) throws NotVerticeException{
 
        if(!vertices.contains(vertice))
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

// Método não funcional em multigrafos
   //
   // 
//    void setarPeso(Vertice origem, Vertice destino, P peso) throws NotVerticeException{
//        int indexOrigem = vertices.indexOf(origem);
//        int indexDestino = vertices.indexOf(destino);
//        
//        if(indexOrigem < 0 || indexDestino < 0)
//            throw new NotVerticeException();
//        
//        for(Aresta a : arestas){
//            if(a.origem.equals(origem) && a.destino.equals(destino)){
//                a.setPeso(peso);
//            }
//        }
//    }

    public ArrayList<V> vertices(){
        ArrayList<V> ret = new ArrayList();
        vertices.forEach((v) -> {
            ret.add(v);
        });
        return ret.isEmpty()?null:ret;
    }
    
    public class Aresta {
        
        private V origem;
        private V destino;
        private P peso;

        public Aresta(V origem, V destino, P peso) {
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

        public P getPeso() {
            return peso;
        }

        public void setPeso(P peso) {
            this.peso = peso;
        }
        
       
    }
    
    
}
