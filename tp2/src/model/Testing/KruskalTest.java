package model.Testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Grafo;
import model.Kruskal;

public class KruskalTest {
	Grafo grafoPDF;
	Grafo grafoPDFAGM;
	Grafo grafoCompleto;
	Grafo grafoCompletoAGM;
	Grafo grafoNoCompleto;
	Grafo grafoNoCompletoAGM;

	@Before
	public void crearGrafosYHallarAGM() {
		inicializaciónGrafo1();
		inicializarGrafoCompleto();
		inicializarGrafoNoCompleto();
		
		grafoPDFAGM = Kruskal.obtenerArbolGeneradorMinimo(grafoPDF);
		grafoCompletoAGM = Kruskal.obtenerArbolGeneradorMinimo(grafoCompleto);
		grafoNoCompletoAGM = Kruskal.obtenerArbolGeneradorMinimo(grafoNoCompleto);
	}

	@Test(expected = IllegalArgumentException.class)
    public void grafoVacioTest() {
        Grafo grafoVacio = new Grafo(0);
        Kruskal.obtenerArbolGeneradorMinimo(grafoVacio);
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		Grafo grafoNoConexo = new Grafo(3);
		grafoNoConexo.agregarArista(0, 1, 0.2);
		Kruskal.obtenerArbolGeneradorMinimo(grafoNoConexo);
	}
	
	
	
	@Test														//Este test es del pdf visto en clase
	public void grafoPDFTest() {	
	    assertTrue(grafoPDFAGM.existeArista(7, 6));  
	    assertTrue(grafoPDFAGM.existeArista(8, 2));  
	    assertTrue(grafoPDFAGM.existeArista(5, 6));
	    assertTrue(grafoPDFAGM.existeArista(2, 5));  
	    assertTrue(grafoPDFAGM.existeArista(0, 1));  
	    assertTrue(grafoPDFAGM.existeArista(2, 3)); 
	    assertTrue((grafoPDFAGM.existeArista(1, 2) || grafoPDFAGM.existeArista(0, 7))); 
	    assertTrue(grafoPDFAGM.existeArista(3, 4));  
	    
	}
	
	@Test
	public void grafoPDFCantCorrectaAristasTest() {
		assertEquals(8, grafoPDFAGM.getCantidadDeAristas()); 
	}
	
	@Test
	public void grafoConexoCompletoTest() {  
	    assertTrue(grafoCompletoAGM.existeArista(0, 1));
	    assertTrue(grafoCompletoAGM.existeArista(0, 2));
	    assertTrue(grafoCompletoAGM.existeArista(0, 3));
	}
	
	@Test
	public void grafoConexoCompletoAristasQueNoPertenecenAlAGMTest() {
	    assertFalse(grafoCompletoAGM.existeArista(1, 2));  
	}
	
	@Test
	public void grafoConexoCompletoCantCorrectaAristasTest() {
		assertEquals(3, grafoCompletoAGM.getCantidadDeAristas()); 
	}
	
	@Test
	public void grafoNoCompletoTest() {
		assertTrue(grafoNoCompletoAGM.existeArista(0, 1));
	    assertTrue(grafoNoCompletoAGM.existeArista(1, 2));
	    assertTrue(grafoNoCompletoAGM.existeArista(1, 3));
	}
	
	@Test
	public void grafoNoCompletoAristasQueNoPertenecenAlAGMTest() {
	    assertFalse(grafoNoCompletoAGM.existeArista(0, 2));  
	}
	
	@Test
	public void grafoNoCompletoCantCorrectaAristasTest() {
		assertEquals(3, grafoNoCompletoAGM.getCantidadDeAristas()); 
	}
	
	private void inicializaciónGrafo1() {
		grafoPDF = new Grafo(9);
		
	    grafoPDF.agregarArista(0, 1, 0.04);
        grafoPDF.agregarArista(0, 7, 0.08);
        grafoPDF.agregarArista(1, 2, 0.08);
        grafoPDF.agregarArista(1, 7, 0.12);
        grafoPDF.agregarArista(2, 3, 0.06);
        grafoPDF.agregarArista(2, 8, 0.03);
        grafoPDF.agregarArista(2, 5, 0.04);
        grafoPDF.agregarArista(3, 4, 0.09);
        grafoPDF.agregarArista(3, 5, 0.13);
        grafoPDF.agregarArista(4, 5, 0.10);
        grafoPDF.agregarArista(5, 6, 0.03);
        grafoPDF.agregarArista(6, 8, 0.05);
        grafoPDF.agregarArista(6, 7, 0.01);
        grafoPDF.agregarArista(7, 8, 0.06);
	}
	
	private void inicializarGrafoCompleto() {
		grafoCompleto = new Grafo(4);
		
	    grafoCompleto.agregarArista(0, 1, 0.01);
	    grafoCompleto.agregarArista(0, 2, 0.02);
	    grafoCompleto.agregarArista(0, 3, 0.03);
	    grafoCompleto.agregarArista(1, 2, 0.04);
	    grafoCompleto.agregarArista(1, 3, 0.05);
	    grafoCompleto.agregarArista(2, 3, 0.06);
	}
	
	private void inicializarGrafoNoCompleto() {
		grafoNoCompleto = new Grafo(4);
		
		grafoNoCompleto.agregarArista(0, 1, 0.1);
    	grafoNoCompleto.agregarArista(0, 2, 0.2);
    	grafoNoCompleto.agregarArista(1, 2, 0.05); 
    	grafoNoCompleto.agregarArista(1, 3, 0.3);
	}
}
