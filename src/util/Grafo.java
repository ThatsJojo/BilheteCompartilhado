package util;

import Exceptions.NotVerticeException;
import Exceptions.NotPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import model.Aeroporto;


public class Grafo <V>{
    
    private final ArrayList<V> vertices;
    private final ArrayList<Aresta<V>> arestas;
    private final HashMap<V, ArrayList<Aresta<V>>> bucketArestas;

    public Grafo() {
        vertices = new ArrayList();
        arestas = new ArrayList();
        bucketArestas = new HashMap();
    }
    
    public void addVertice(V vertice) throws NotVerticeException{
        if(vertice == null)
            throw new NotVerticeException();
        if(vertices.contains(vertice))
            return;
        vertices.add(vertice);
        bucketArestas.put(vertice, new ArrayList());
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
    public void addAresta(Aresta<V> aresta) throws NotVerticeException{
        V origem = aresta.getOrigem();
        V destino = aresta.getDestino();
        int indexOrigem = vertices.indexOf(origem);
        int indexDestino = vertices.indexOf(destino);
        if(indexOrigem < 0 || indexDestino < 0)
            throw new NotVerticeException();
        arestas.add(aresta);
        bucketArestas.get(origem).add(aresta);
    }    
    
    //Metodo que irá retornar uma lista de adjacencias apartir de uma origem
    public ArrayList<V> adjacentesDe(V origem) throws NotVerticeException{
        int indexOrigem = vertices.indexOf(origem);
        if(indexOrigem < 0)
            throw new NotVerticeException();
        ArrayList<V> ret = new ArrayList();
        for(Aresta<V> a : bucketArestas.get(origem)){
            ret.add(a.getDestino());
        }
        return ret.isEmpty() ? null : ret;
    }

    public ArrayList<Aresta<V>> arestasEntre(V origem, V destino) throws NotVerticeException{
        ArrayList<Aresta<V>> ret = new ArrayList();
        int indexOrigem = vertices.indexOf(origem);
        int indexDestino = vertices.indexOf(destino);
        if(indexOrigem<0 || indexDestino<0)
            throw new NotVerticeException();
        bucketArestas.get(origem).forEach((a)->{
            if(a.getDestino().equals(destino) )
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
        for(Aresta<V> a : bucketArestas.get(origem)){
            if( a.getOrigem().equals(origem)&& a.getDestino().equals(destino) ){
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
        for(Aresta<V> a: arestas){
            if(a.getDestino().equals(vertice)){
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
    
    public ArrayList<Aresta<V>> menorCaminho(V origem, V destino) throws NotPathException{
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
            passados.add(maisProximo);
            naoPassados.remove(maisProximo);
            calcularDistancias(maisProximo,passados,distanceMap,caminhoNegado);
            
        }
        ArrayList<Aresta<V>> ret = new ArrayList<>();
        V o = destino;
        while(!o.equals(origem)){
            Aresta<V> a = caminhoNegado.get(o);
            if(a == null)
                throw new NotPathException();
            ret.add(a);
            o = a.getOrigem();
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
        ArrayList<Aresta<V>> arestasVizinhos = bucketArestas.get(vertice);
        HashSet<V> passadosAgora = new HashSet<>();
        Double distanciaBase = distanceMap.get(vertice);
        for(Aresta<V> a: arestasVizinhos){
            if(!passados.contains(a.getDestino())){
                if(distanceMap.get(a.getDestino())>a.getPeso()+distanciaBase){
                    distanceMap.replace(a.getDestino(), distanciaBase+a.getPeso());
                    caminhoNegado.put(a.getDestino(), a);
                }
                //System.out.println("Vim de "+a.getOrigem()+" "+vertice+" e Passei em: "+ a.getDestino()+ " Distância: " +(a.getPeso()+distanciaBase) +" DistânciaNoMapa: "+ distanceMap.get(a.getDestino()));
                    
            }
        }
    }
    
    private Aresta arestaMaisProxima(V origem, V destino){
        if(origem == null)
            return null;
        ArrayList<Aresta<V>> candidatas = bucketArestas.get(origem);
        Aresta<V> closer = null;
        for(Aresta<V> a: candidatas){
            if(a.getDestino().equals(destino)){
                if(closer == null)
                    closer = a;
                else if(a.compareTo(closer)<0)
                    closer = a;
            }
        }
        return closer;
    }

    public ArrayList<ArrayList<V>> getRotas(V origem, V destino) throws NotVerticeException {
        if(!vertices.contains(origem)||!vertices.contains(destino)){
            throw new NotVerticeException();
        }
        HashMap<Double, ArrayList<V>> caminhos = new HashMap();
        HashSet<V> passados = new HashSet();
        HashSet<V> naoPassados = new HashSet();
        vertices.forEach((vertice)->{
            naoPassados.add(vertice);
        });
        
        ArrayList<Aresta<V>> adjacenciasOrigem = bucketArestas.get(origem);
        passados.add(origem);
        for(int i = 0; i<adjacenciasOrigem.size();i++){
            Aresta<V> aresta = adjacenciasOrigem.get(i);
            V adjacente = aresta.getDestino();
            ArrayList<V> caminhoAtual = new ArrayList();
            caminhoAtual.add(origem);
            buscaRecursiva(adjacente, destino, passados, naoPassados, caminhos, aresta.getPeso(),caminhoAtual);
        }
        ArrayList<ArrayList<V>> ret = new ArrayList();
        caminhos.forEach((Double u, ArrayList<V> t) -> {
            //System.out.println("Peso: "+u);
            ret.add(t);
        });
        return ret;
    }
    
    private ArrayList<V> copiaDe(ArrayList<V> caminhoAtual){
        ArrayList<V> ret = new ArrayList();
        for(int i =0; i<caminhoAtual.size(); i++){
            ret.add(caminhoAtual.get(i));
        }
        return ret;
    }
    
    public void buscaRecursiva(V origem, V destino, HashSet<V> passados, HashSet<V> naoPassados, HashMap<Double, ArrayList<V>> caminhos, Double tamanho, ArrayList<V> caminhoAtual){
        if(origem.equals(destino)){
            ArrayList<V> ret = copiaDe(caminhoAtual);
            ret.add(origem);
            caminhos.put(tamanho,ret);
            return;
        }
        passados.add(origem);
        caminhoAtual.add(origem);
        bucketArestas.get(origem).forEach((aresta)->{
            V prox = aresta.getDestino();
            if(!passados.contains(prox)){
                buscaRecursiva(prox, destino, passados, naoPassados,caminhos, tamanho+aresta.getPeso(), copiaDe(caminhoAtual));
            }
            passados.remove(origem);
        });
    }    
    
}
