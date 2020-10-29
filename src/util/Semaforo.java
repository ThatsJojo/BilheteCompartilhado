package util;

import java.util.HashMap;

public class Semaforo{
    private final  HashMap<Aresta, Integer> resources;
    private static Semaforo uniqueInstance;
    
    private Semaforo(){
        this.resources = new HashMap();
    }
    
    public static synchronized Semaforo getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Semaforo();
        }
        return uniqueInstance;
    }
    
    public void cadastrarRecurso(Aresta A, int resource){
        resources.put(A, resource);
    }
    
    public void down(Aresta A){
        while(true){
            System.out.println("Down");
            if(resources.get(A)>0){
                resources.replace(A, resources.get(A)-1);
                //System.out.println("Flag antes: "+flag+"   flag depois: "+ resources.get(A));
                return;
            }try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {}
        }
    }
    
    private synchronized void diminuir(Aresta A, int i){
        resources.replace(A, i);
    }
    
    public void up(Aresta A){
        Integer flag = resources.get(A);
        resources.replace(A, flag++);
    }
    
}
