package model.Testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.BFS;
import model.Grafo;

public class BFSTest 
{
	@Test(expected=IllegalArgumentException.class)
	public void grafoNullTest() 
	{
		BFS.esConexo(null);
	}
	
	/*
	 * ---- GRAFO UN VÉRTICE ----
	 */
	
	@Test
	public void grafoUnVerticeTest() 
	{
		assertTrue(BFS.esConexo(new Grafo(1)));
	}
	
	/*
	 * ---- GRAFOS DOS VÉRTICES ----
	 */
	
	@Test
	public void grafoDosVerticesAisladosTest() 
	{
		assertFalse(BFS.esConexo(new Grafo(2)));
	}
	
	@Test
	public void grafoDosVerticesConexoTest() 
	{
		Grafo g = new Grafo(2);
		g.agregarArista(0, 1, 0.5);
		assertTrue(BFS.esConexo(g));
	}
	
	/*
	 * ---- GRAFO COMPLETO ----
	 */
	
	@Test
	public void grafoCompletoTest() 
	{
		Grafo g = inicializarGrafoCompleto();
		assertTrue(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesGrafoCompletoTest() 
	{
		Grafo g = inicializarGrafoCompleto();
		
		int[] esperado = {0, 1, 2, 3};
		Assert.conjuntosIguales(esperado, BFS.alcanzables(g, 0));
	}

	/*
	 * ---- GRAFO INCONEXO ----
	 */
	
	@Test
	public void grafoInconexoTest() 
	{
		Grafo g = inicializarGrafoInconexo();
		
		assertFalse(BFS.esConexo(g));
	}
	
	@Test
	public void alcanzablesInconexoTest() 
	{
		Grafo g = inicializarGrafoInconexo();
		
		int[] esperado = {0, 1, 2, 3, 4};
		Assert.conjuntosIguales(esperado, BFS.alcanzables(g, 0));
	}
	
	/*
	 * ---- INICIALIZACION DE GRAFOS ----
	 */
	
	private Grafo inicializarGrafoInconexo() 
	{
		Grafo g = new Grafo(7);
		g.agregarArista(0, 1, 0.5);
		g.agregarArista(0, 2, 0.5);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(1, 3, 0.5);
		g.agregarArista(2, 4, 0.5);
		g.agregarArista(3, 4, 0.5);
		g.agregarArista(5, 6, 0.5);
		
		return g;		
	}
	
	private Grafo inicializarGrafoCompleto() 
	{
		Grafo g = new Grafo(4);
		g.agregarArista(0, 1, 0.5);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(2, 3, 0.5);
		g.agregarArista(0, 3, 0.5);
		g.agregarArista(0, 2, 0.5);
		g.agregarArista(1, 3, 0.5);
		
		return g;		
	}
}