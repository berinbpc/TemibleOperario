package model.Testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Grafo;
import model.Prim;

public class PrimTest {
	
	Grafo grafo1;
	Grafo grafo1agm;
	Grafo grafo2;
	Grafo grafo2agm;
	Grafo grafo0s;
	Grafo grafo0sagm;
	Grafo grafo1s;
	Grafo grafo1sagm;
	
	@Before
	public void crearGrafosYHallarAGM() {
		inicializaciónGrafo1();
		inicializaciónGrafo2();
		inicializaciónGrafoTodasProbabilidadesCero();
		inicializaciónGrafoTodasProbabilidadesUno();
		
		grafo1agm = Prim.obtenerArbolGeneradorMinimo(grafo1);
		grafo2agm = Prim.obtenerArbolGeneradorMinimo(grafo2);
		grafo0sagm = Prim.obtenerArbolGeneradorMinimo(grafo0s);
		grafo1sagm = Prim.obtenerArbolGeneradorMinimo(grafo1s);	
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		Grafo g = new Grafo(3);
		g.agregarArista(0, 1, 0.02);
		Prim.obtenerArbolGeneradorMinimo(g);
	}
	

	@Test(expected = IllegalArgumentException.class)
    public void testGrafoVacio() {
        // Crear un grafo vacío
        Grafo grafoVacio = new Grafo(0);
        Prim.obtenerArbolGeneradorMinimo(grafoVacio);
	}
	

	/*
	 * ---- GRAFO 1 ----
	 */
	@Test
	public void grafo1AristasQuePertenecenAlAGMTest() {		//este test es del pdf de Arbol Generador Minimo que vimos en clase
	    assertTrue(grafo1agm.existeArista(0, 1));  
	    assertTrue(grafo1agm.existeArista(1, 2));  
	    assertTrue(grafo1agm.existeArista(7, 6));
	    assertTrue(grafo1agm.existeArista(6, 5));  
	    assertTrue(grafo1agm.existeArista(5, 2));  
	    assertTrue(grafo1agm.existeArista(2, 8)); 
	    assertTrue(grafo1agm.existeArista(2, 3));  
	    assertTrue(grafo1agm.existeArista(3, 4));  
	    
	}
	
	@Test
	public void grafo1CantCorrectaAristasTest() {
		assertEquals(8, grafo1agm.getCantidadDeAristas());  
	}
	
	/*
	 * ---- GRAFO 2 ----
	 */
	
	@Test
	public void grafo2AristasQuePertenecenAlAGMTest() {
	    assertTrue(grafo2agm.existeArista(0, 1));  
	    assertTrue(grafo2agm.existeArista(1, 4));
	    assertTrue(grafo2agm.existeArista(2, 4));
	    assertTrue(grafo2agm.existeArista(3, 5)); 
	    assertTrue(grafo2agm.existeArista(4, 5));

	    assertFalse(grafo2agm.existeArista(0, 2));  
	    assertFalse(grafo2agm.existeArista(1, 3)); 
	}
	
	@Test
	public void grafo2AristasQueNoPertenecenAlAGMTest() {
	    assertFalse(grafo2agm.existeArista(0, 2));  
	    assertFalse(grafo2agm.existeArista(1, 3)); 
	}
	
	@Test
	public void grafo2CantCorrectaAristasTest() {
		assertEquals(5, grafo2agm.getCantidadDeAristas()); 
	}

	/*
	 * ---- CASOS BORDE ----
	 */
	
	@Test
	public void grafoTodasProbabilidadesCeroCantCorrectaAristasTest() {
		assertEquals(4, grafo0sagm.getCantidadDeAristas()); 
	}
	
	@Test
	public void grafoTodasProbabilidadesUnoCantCorrectaAristasTest() {
		assertEquals(4, grafo0sagm.getCantidadDeAristas()); 
	}
	
	/*
	 * ---- INICIALIZACION DE GRAFOS ----
	 */
	
	private void inicializaciónGrafo1() {
		grafo1 = new Grafo(9);
		
	    grafo1.agregarArista(0, 1, 0.04);
        grafo1.agregarArista(0, 7, 0.08);
        grafo1.agregarArista(1, 2, 0.08);
        grafo1.agregarArista(1, 7, 0.12);
        grafo1.agregarArista(2, 3, 0.06);
        grafo1.agregarArista(2, 8, 0.03);
        grafo1.agregarArista(2, 5, 0.04);
        grafo1.agregarArista(3, 4, 0.09);
        grafo1.agregarArista(3, 5, 0.13);
        grafo1.agregarArista(4, 5, 0.10);
        grafo1.agregarArista(5, 6, 0.03);
        grafo1.agregarArista(6, 8, 0.05);
        grafo1.agregarArista(6, 7, 0.01);
        grafo1.agregarArista(7, 8, 0.06);
	}
	
	private void inicializaciónGrafo2() {
		grafo2 = new Grafo(6);
	
		grafo2.agregarArista(0, 1, 0.01);
		grafo2.agregarArista(0, 2, 0.45);
		grafo2.agregarArista(1, 3, 0.90);
		grafo2.agregarArista(1, 4, 0.30);
		grafo2.agregarArista(2, 4, 0.17);
		grafo2.agregarArista(3, 4, 0.11);
		grafo2.agregarArista(3, 5, 0.03);
		grafo2.agregarArista(4, 5, 0.08);
	}
	
	private void inicializaciónGrafoTodasProbabilidadesCero () {
		grafo0s = new Grafo(5);
		grafo0s.agregarArista(0, 1, 0);
		grafo0s.agregarArista(0, 2, 0);
		grafo0s.agregarArista(2, 1, 0);
		grafo0s.agregarArista(2, 3, 0);
		grafo0s.agregarArista(3, 4, 0);
		grafo0s.agregarArista(1, 4, 0);
	}
	
	private void inicializaciónGrafoTodasProbabilidadesUno () {
		grafo1s = new Grafo(5);
		grafo1s.agregarArista(0, 1, 1);
		grafo1s.agregarArista(0, 2, 1);
		grafo1s.agregarArista(2, 1, 1);
		grafo1s.agregarArista(2, 3, 1);
		grafo1s.agregarArista(3, 4, 1);
		grafo1s.agregarArista(1, 4, 1);
	}
}
