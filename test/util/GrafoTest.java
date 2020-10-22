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
        TesteClass peso1 = new TesteClass("Peso1");
        grafo.addVertice(origem);
        grafo.addVertice(destino);
        
        assertEquals(0, grafo.numeroDeArestas());
        
        grafo.addAresta(origem, destino, peso1);
        
        assertEquals(1, grafo.numeroDeArestas());
        assertTrue(grafo.existeAresta(origem, destino));
        assertTrue(grafo.adjacentesDe(origem).contains(destino));
        assertEquals(1, grafo.adjacentesDe(origem).size());
        assertEquals(null, grafo.adjacentesDe(destino));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getPeso().equals(peso1));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getDestino().equals(destino));
        assertTrue(grafo.arestasEntre(origem, destino).get(0).getOrigem().equals(origem));
        assertEquals(1, grafo.arestasEntre(origem, destino).size());
        
    }

    /**
     * Test of grauDoVertice method, of class Grafo.
     */
    @Test
    public void testGrauDoVertice() throws Exception {
        Grafo<String> grafo = new Grafo();
        String origem = "Vertice1";
        String destino = "Vertice2";
        TesteClass peso1 = new TesteClass("Peso1");
        grafo.addVertice(origem);
        grafo.addVertice(destino);
        grafo.addAresta(origem, destino, peso1);
        assertEquals(1, grafo.grauDoVertice(destino));
        assertEquals(0, grafo.grauDoVertice(origem));
    }
    
}
