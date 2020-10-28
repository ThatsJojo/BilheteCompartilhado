
package controller;

import util.Grafo;
import util.NotVerticeException;
import util.Weighable;


public class GrafoController {

    Grafo grafo;
    public GrafoController() {
        this.grafo = new Grafo();
    }
    
   public void inicializaGrafo () throws NotVerticeException{
        String v0 = "0";
        String v1 = "1";
        String v2 = "2";
        String v3 = "3";
        String v4 = "4";
        String v5 = "5";
        String v6 = "6";
        String v7 = "7";
        String v8 = "8";
        grafo.addVertice(v0);
        grafo.addVertice(v1);
        grafo.addVertice(v2);
        grafo.addVertice(v3);
        grafo.addVertice(v4);
        grafo.addVertice(v5);
        grafo.addVertice(v6);
        grafo.addVertice(v7);
        grafo.addVertice(v8);
//        TesteClass peso01 = new TesteClass("Peso1", new Double(4));
//        TesteClass peso07 = new TesteClass("Peso1", new Double(8));
//        TesteClass peso12 = new TesteClass("Peso1", new Double(8));
//        TesteClass peso17 = new TesteClass("Peso1", new Double(11));
//        TesteClass peso78 = new TesteClass("Peso1", new Double(7));
//        TesteClass peso76 = new TesteClass("Peso1", new Double(1));
//        TesteClass peso68 = new TesteClass("Peso1", new Double(6));
//        TesteClass peso28 = new TesteClass("Peso1", new Double(2));
//        TesteClass peso25 = new TesteClass("Peso1", new Double(4));
//        TesteClass peso65 = new TesteClass("Peso1", new Double(2));
//        TesteClass peso23 = new TesteClass("Peso1", new Double(7));
//        TesteClass peso53 = new TesteClass("Peso1", new Double(14));
//        TesteClass peso34 = new TesteClass("Peso1", new Double(9));
//        TesteClass peso54 = new TesteClass("Peso1", new Double(10));
//        
//        
//        
//        grafo.addAresta(v0, v1, peso01);
//        grafo.addAresta(v0, v7, peso07);
//        grafo.addAresta(v1, v2, peso12);
//        grafo.addAresta(v1, v7, peso17);
//        grafo.addAresta(v7, v8, peso78);
//        grafo.addAresta(v7, v6, peso76);
//        grafo.addAresta(v6, v8, peso68);
//        grafo.addAresta(v2, v8, peso28);
//        grafo.addAresta(v2, v5, peso25);
//        grafo.addAresta(v6, v5, peso65);
//        grafo.addAresta(v2, v3, peso23);
//        grafo.addAresta(v5, v3, peso53);
//        grafo.addAresta(v3, v4, peso34);
//        grafo.addAresta(v5, v4, peso54);
   }
}
