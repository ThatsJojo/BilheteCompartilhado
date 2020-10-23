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
import util.Grafo.*;

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
        TesteClass peso1 = new TesteClass("Peso1", new Double(0));
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
        TesteClass peso01 = new TesteClass("Peso1", new Double(4));
        TesteClass peso07 = new TesteClass("Peso1", new Double(8));
        TesteClass peso12 = new TesteClass("Peso1", new Double(8));
        TesteClass peso17 = new TesteClass("Peso1", new Double(11));
        TesteClass peso78 = new TesteClass("Peso1", new Double(7));
        TesteClass peso76 = new TesteClass("Peso1", new Double(1));
        TesteClass peso68 = new TesteClass("Peso1", new Double(6));
        TesteClass peso28 = new TesteClass("Peso1", new Double(2));
        TesteClass peso25 = new TesteClass("Peso1", new Double(4));
        TesteClass peso65 = new TesteClass("Peso1", new Double(2));
        TesteClass peso23 = new TesteClass("Peso1", new Double(7));
        TesteClass peso53 = new TesteClass("Peso1", new Double(14));
        TesteClass peso34 = new TesteClass("Peso1", new Double(9));
        TesteClass peso54 = new TesteClass("Peso1", new Double(10));
        grafo.addAresta(v0, v1, peso01);
        grafo.addAresta(v0, v7, peso07);
        grafo.addAresta(v1, v2, peso12);
        grafo.addAresta(v1, v7, peso17);
        grafo.addAresta(v7, v8, peso78);
        grafo.addAresta(v7, v6, peso76);
        grafo.addAresta(v6, v8, peso68);
        grafo.addAresta(v2, v8, peso28);
        grafo.addAresta(v2, v5, peso25);
        grafo.addAresta(v6, v5, peso65);
        grafo.addAresta(v2, v3, peso23);
        grafo.addAresta(v5, v3, peso53);
        grafo.addAresta(v3, v4, peso34);
        grafo.addAresta(v5, v4, peso54);
        ArrayList<Aresta> caminho =  grafo.menorCaminho(v1, v5);
        System.out.println(caminho.get(0).getDestino());
        for(int i = 0; i<caminho.size();i++){
            System.out.println(caminho.get(i).getOrigem());
        }
    }
    
    @Test
    public void testMenorCaminho() throws Exception{
        Grafo<String> grafo = new Grafo();
        
    }
    
}
