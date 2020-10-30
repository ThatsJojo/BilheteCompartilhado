/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author csacl
 */
public class GrafoTest {
    
    public GrafoTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Iniciando testes na estrutura de Grafo");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finalizando testes na estrutura de Grafo");
    }

    @Test
    public void testVertices() throws Exception {
        Grafo<String> grafo = new Grafo();
        String v1 = "Vertice1";
        
        assertEquals(0, grafo.numeroDeVertices());
        
        grafo.addVertice(v1);
        
        assertEquals(1, grafo.numeroDeVertices());
        
        ArrayList<String> vertices = grafo.vertices();
        assertTrue(vertices.contains(v1));
        String v2 = "Vertice2";
        grafo.addVertice(v2);
        
        assertEquals(2, grafo.numeroDeVertices());
        
        vertices = grafo.vertices();
        
        assertTrue(vertices.contains(v1));
        
        grafo.addVertice(v1);
        
        assertTrue(grafo.containsVertice(v1));
        
        grafo.addVertice(v2);
        
        assertTrue(grafo.containsVertice(v2));
    }

    /**
     * Test of addAresta method, of class Grafo.
     */
    @Test
    public void testArestas() throws Exception {
        Grafo<String> grafo = new Grafo();
        String origem = "Vertice1";
        String destino = "Vertice2";
        TesteClass<String> peso1 = new TesteClass<String>(origem, destino, new Double(0));
        grafo.addVertice(origem);
        grafo.addVertice(destino);
        
        assertEquals(0, grafo.numeroDeArestas());
        
        grafo.addAresta(peso1);
        
        assertEquals(1, grafo.numeroDeArestas());
        assertTrue(grafo.existeAresta(origem, destino));
        assertTrue(grafo.adjacentesDe(origem).contains(destino));
        assertEquals(1, grafo.adjacentesDe(origem).size());
        assertEquals(null, grafo.adjacentesDe(destino));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).equals(peso1));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getDestino().equals(destino));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getOrigem().equals(origem));
        assertEquals(1, grafo.arestasEntre(origem, destino).size());
        
    }

    /**
     * Test of grauDoVertice method, of class Grafo.
     */
    @Test
    public void testGrauDoVertice() throws Exception {
        Grafo grafo = new Grafo();
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
        TesteClass<String> peso01 = new TesteClass(v0, v1, new Double(4));
        TesteClass<String> peso07 = new TesteClass(v0, v7, new Double(8));
        TesteClass<String> peso12 = new TesteClass(v1, v2, new Double(8));
        TesteClass<String> peso17 = new TesteClass(v1, v7, new Double(11));
        TesteClass<String> peso78 = new TesteClass(v7, v8, new Double(7));
        TesteClass<String> peso76 = new TesteClass(v7, v6, new Double(1));
        TesteClass<String> peso68 = new TesteClass(v6, v8, new Double(6));
        TesteClass<String> peso28 = new TesteClass(v2, v8, new Double(2));
        TesteClass<String> peso25 = new TesteClass(v2, v5, new Double(4));
        TesteClass<String> peso65 = new TesteClass(v6, v5, new Double(2));
        TesteClass<String> peso23 = new TesteClass(v2, v3, new Double(7));
        TesteClass<String> peso53 = new TesteClass(v5, v3, new Double(14));
        TesteClass<String> peso34 = new TesteClass(v3, v4, new Double(9));
        TesteClass<String> peso54 = new TesteClass(v5, v4, new Double(10));
        
        grafo.addAresta(peso01);
        grafo.addAresta(peso07);
        grafo.addAresta(peso12);
        grafo.addAresta(peso17);
        grafo.addAresta(peso78);
        grafo.addAresta(peso76);
        grafo.addAresta(peso68);
        grafo.addAresta(peso28);
        grafo.addAresta(peso25);
        grafo.addAresta(peso65);
        grafo.addAresta(peso23);
        grafo.addAresta(peso53);
        grafo.addAresta(peso34);
        grafo.addAresta(peso54);
        ArrayList<Aresta> caminho =  grafo.menorCaminho(v0, v4);
        
        for(int i = caminho.size()-1; i>=0;i--){
            Aresta<String> a = caminho.get(i);
            System.out.println("Origem: "+a.getOrigem()+" Destino: "+a.getDestino()+" peso:"+a.getPeso());
        }
    }
    
    @Test
    public void testMenorCaminho() throws Exception{
        Grafo<String> grafo = new Grafo();
        
    }
    
}
