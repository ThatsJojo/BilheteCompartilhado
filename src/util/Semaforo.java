package util;

import java.util.HashMap;

public class Semaforo{
    private final  HashMap<Aresta, Integer> totalResources;
    private final  HashMap<Aresta, Integer> resources;
    private static Semaforo uniqueInstance;
    
    private Semaforo(){
        this.resources = new HashMap();
        this.totalResources = new HashMap();
    }
    
    public static synchronized Semaforo getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Semaforo();
        }
        return uniqueInstance;
    }
    
    public void cadastrarRecurso(Aresta A, int resource){
        resources.put(A, resource);
        totalResources.put(A, resource);
    }
   
    public synchronized boolean down(Aresta A){
        int flag = resources.get(A);
        if(resources.get(A)>0){
            resources.replace(A, resources.get(A)-1);
            //System.out.println("Flag antes: "+flag+"   flag depois: "+ resources.get(A));
            return true;
        }
        return false;
    }
    
    public synchronized void up(Aresta A){
        Integer flag = resources.get(A);
        resources.replace(A, resources.get(A)+1);
    }
    
}
