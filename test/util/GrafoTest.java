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
    public void testaddContainsVertice() throws Exception {
        Grafo<String,String> grafo = new Grafo();
        System.out.println("addVertice");
        String v1 = "Vertice1";
        
        assertEquals(grafo.numeroDeVertices(),0);
        
        grafo.addVertice(v1);
        
        assertEquals(grafo.numeroDeVertices(),1);
        
        ArrayList<String> vertices = grafo.vertices();
        assertTrue(vertices.contains(v1));
        String v2 = "Vertice2";
        grafo.addVertice(v2);
        
        assertEquals(grafo.numeroDeVertices(),2);
        
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
        Grafo<String,String> grafo = new Grafo();
        System.out.println("addAresta");
        String origem = "Vertice1";
        String destino = "Vertice2";
        String peso1 = "PesoAresta1";
        grafo.addVertice(origem);
        grafo.addVertice(destino);
        
        assertEquals(grafo.numeroDeArestas(),0);
        
        grafo.addAresta(origem, destino, peso1);
        
        assertEquals(grafo.numeroDeArestas(),1);
        assertTrue(grafo.existeAresta(origem, destino));
        assertTrue(grafo.adjacentesDe(origem).contains(destino));
        assertEquals(grafo.adjacentesDe(origem).size(),1);
        assertEquals(grafo.adjacentesDe(destino),null);
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getPeso().equals(peso1));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getDestino().equals(destino));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getOrigem().equals(origem));
        assertEquals(grafo.arestasEntre(origem, destino).size(),1);
        
    }

    /**
     * Test of grauDoVertice method, of class Grafo.
     */
    @Test
    public void testGrauDoVertice() throws Exception {
        Grafo<String,String> grafo = new Grafo();
        System.out.println("addAresta");
        String origem = "Vertice1";
        String destino = "Vertice2";
        String peso1 = "PesoAresta1";
        grafo.addVertice(origem);
        grafo.addVertice(destino);
        grafo.addAresta(origem, destino, peso1);
        assertEquals(grafo.grauDoVertice(destino), 1);
        assertEquals(grafo.grauDoVertice(origem), 0);
    }
    
}
