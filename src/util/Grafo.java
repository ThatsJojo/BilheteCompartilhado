package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


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
    public void addAresta(V origem, V destino, Weighable peso) throws NotVerticeException{
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
    
    public ArrayList<Aresta> menorCaminho(V origem, V destino){
        if(!this.containsVertice(origem)||!this.containsVertice(destino))
            return null;
        HashMap<V, Double> distanceMap = new HashMap<>();
        HashSet<V> passados = new HashSet();
        HashSet<V> naoPassados = new HashSet();
        distanceMap.put(origem, new Double(0));
        for(V vertice: vertices){
            naoPassados.add(vertice);
            if(!vertice.equals(origem))
                distanceMap.put(vertice, Double.MAX_VALUE);
        }
        
        HashMap<V, Aresta> caminhoNegado = new HashMap<>();
        
        while(!naoPassados.isEmpty()){
            V maisProximo = verticeMaisProximo(naoPassados, distanceMap);
            System.out.println("MaisProximo: "+maisProximo);
            passados.add(maisProximo);
            naoPassados.remove(maisProximo);
            calcularDistancias(maisProximo,passados,distanceMap,caminhoNegado);
            
        }
        ArrayList<Aresta> ret = new ArrayList<>();
        System.out.println("\n******************************************\n");
        V o = destino;
        while(!o.equals(origem)){
            Aresta a = caminhoNegado.get(o);
            ret.add(a);
            o = a.origem;
        }
        return ret;
    }
    
    private V verticeMaisProximo(HashSet<V> naoPassados, HashMap<V, Double> distanceMap){
        V ret = null;
        Iterator i = naoPassados.iterator();
        while(i.hasNext()){
            if(ret == null)
                ret = (V) i.next();
            else{
                V vertice = (V) i.next();
                if(distanceMap.get(vertice)<distanceMap.get(ret))
                    ret = vertice;
            } 
        }
        return ret;
    }
    
    private void calcularDistancias(V vertice, HashSet<V> passados, HashMap<V, Double> distanceMap, HashMap<V, Aresta> caminhoNegado){
        ArrayList<Aresta> arestasVizinhos = bucketArestas.get(vertices.indexOf(vertice));
        HashSet<V> passadosAgora = new HashSet<>();
        Double distanciaBase = distanceMap.get(vertice);
        for(Aresta a: arestasVizinhos){
            if(!passados.contains(a.destino)){
                if(distanceMap.get(a.destino)>a.peso.peso()+distanciaBase){
                    distanceMap.replace(a.destino, distanciaBase+a.peso.peso());
                    caminhoNegado.put(a.destino, a);
                }
                System.out.println("Vim de "+a.origem+" "+vertice+" e Passei em: "+ a.destino+ " Distância: " +(a.getPeso().peso()+distanciaBase) +" DistânciaNoMapa: "+ distanceMap.get(a.destino));
                    
            }
        }
    }
    
    private Aresta arestaMaisProxima(V origem, V destino){
        if(origem == null)
            return null;
        ArrayList<Aresta> candidatas = bucketArestas.get(vertices.indexOf(origem));
        Aresta closer = null;
        for(Aresta a: candidatas){
            if(a.destino.equals(destino)){
                if(closer == null)
                    closer = a;
                else if(a.getPeso().compareTo(closer)<0)
                    closer = a;
            }
        }
        return closer;
    }
    
    
    
    public class Aresta {
        
        private V origem;
        private V destino;
        private Weighable peso;

        public Aresta(V origem, V destino, Weighable peso) {
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

        public Weighable getPeso() {
            return peso;
        }

        public void setPeso(Weighable peso) {
            this.peso = peso;
        }
    }
}
