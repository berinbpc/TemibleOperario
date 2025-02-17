package model.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Grafo;

public class GrafoTest {

    private Grafo grafo;
    @Before
    public void setUp() {
        grafo = new Grafo(5);
    }
    
    
    @Test
    public void testCrearGrafoConCantidadDeVertices() {
    	Grafo g = new Grafo(4);
    	
    	assertEquals(4, g.getCantVertices());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCantVerticesInvalido()	{
    	new Grafo(-1);
    }

    @Test
    public void testAgregarArista() {
        grafo.agregarArista(0, 1, 0.4);
        assertTrue(grafo.getVecinos(0).contains(1));
        assertTrue(grafo.getVecinos(1).contains(0));
        
        assertEquals(0.4, grafo.getProbabilidad(0, 1), 0.001);
        assertEquals(0.4, grafo.getProbabilidad(1, 0), 0.001);
    }
    
    @Test
    public void testAgregarAristaAgregaProbabilidad() {
    	 grafo.agregarArista(0, 1, 0.1);
    	 
         assertEquals(0.1, grafo.getProbabilidad(0, 1), 0.001);
         assertEquals(0.1, grafo.getProbabilidad(1, 0), 0.001);  	 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAgregarAristaFueraDeRango() {
    	grafo.agregarArista(4, 5, 0.5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAgregarAristaQueGeneraLoop() {
    	grafo.agregarArista(4, 4, 0.5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAgregarProbabilidadInvalida() {
    	grafo.agregarArista(0, 1, -2);
    }

    @Test
    public void testEliminarArista() {
        grafo.agregarArista(0, 1, 0.4);
        grafo.eliminarArista(0, 1);

        assertFalse(grafo.getVecinos(0).contains(1));
        assertFalse(grafo.getVecinos(1).contains(0));
    }

    @Test
    public void testObtenerVecinos() {
        grafo.agregarArista(0, 1, 0.3);
        grafo.agregarArista(0, 2, 0.5);
        grafo.agregarArista(0, 3, 0.2);
     
        assertTrue(grafo.getVecinos(0).contains(1));
        assertTrue(grafo.getVecinos(0).contains(2));
        assertTrue(grafo.getVecinos(0).contains(3));        
    }
    
    @Test
    public void testObtenerVecinosNoExistentes() {	
        grafo.agregarArista(0, 1, 0.3);
        
    	assertFalse(grafo.getVecinos(0).contains(2));
    }
    
    @Test
    public void testAgregarVertice() {
        // Agregar espías
        grafo.agregarVertice("Espía 1");
        grafo.agregarVertice("Espía 2");
        grafo.agregarVertice("Espía 3");
        grafo.agregarVertice("Espía 4");
        grafo.agregarVertice("Espía 5");

    
        assertEquals(5, grafo.getCantVertices());


        assertEquals("Espía 1", grafo.obtenerNombrePorVertice(0));
        assertEquals("Espía 2", grafo.obtenerNombrePorVertice(1));
        assertEquals("Espía 3", grafo.obtenerNombrePorVertice(2));
        assertEquals("Espía 4", grafo.obtenerNombrePorVertice(3));
        assertEquals("Espía 5", grafo.obtenerNombrePorVertice(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarVerticeDuplicado() {
        grafo.agregarVertice("Espía 1");
        grafo.agregarVertice("Espía 1");
    }

    @Test(expected = IllegalStateException.class)
    public void testAgregarMasEspiasQueVertices() {
        grafo.agregarVertice("Espía 1");
        grafo.agregarVertice("Espía 2");
        grafo.agregarVertice("Espía 3");
        grafo.agregarVertice("Espía 4");
        grafo.agregarVertice("Espía 5");
        grafo.agregarVertice("Espía 6");
    }
}

