package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BFS {
	private static List<Integer> L;
	private static boolean[] marcados;
	private static boolean[] agregados;
	
	public static boolean esConexo(Grafo g) {
		if (g == null) {
			throw new IllegalArgumentException("El grafo no puede ser null.");
		}
		/*
		 * Grafo sin vertices o con uno solo, es conexo
		 */
		if (g.getCantVertices() <= 1) {
			return true;
		}
		
		return alcanzables(g, 0).size() == g.getCantVertices();
	}
	
	public static Set<Integer> alcanzables(Grafo g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		
		inicializarBusqueda(g, origen);
		
		while (!L.isEmpty()) {
			int i = seleccionarYMarcarVertice();
			ret.add(i);
			agregarVecinosNoMarcados(g, i);
			removerSeleccionado();
		}
		return ret;
	}
	
	private static void removerSeleccionado() {
		agregados[L.get(0)] = false;
		L.remove(0);
	}

	private static void agregarVecinosNoMarcados(Grafo g, int vertice) {		
		for (int vecino : g.getVecinos(vertice)) {
			if (!marcados[vecino] && !agregados[vecino])
				L.add(vecino);
		}
	}

	private static int seleccionarYMarcarVertice() {
		int seleccionado = L.get(0);
		marcados[seleccionado] = true;
		agregados[seleccionado] = true;
		return seleccionado;
	}

	private static void inicializarBusqueda(Grafo g, int origen) {
		L = new LinkedList<Integer>();
		marcados = new boolean[g.getCantVertices()];
		agregados = new boolean[g.getCantVertices()];
		L.add(origen);
	}
	
	/*
	 * Otro recorrido de BFS sobre aristas.
	 * (Se usa LinkedList para que mantenga el orden de las aristas)
	 */
	
	public static LinkedList<Arista> recorridoSobreAristas(Grafo g, int verticeOrigen) {
		LinkedList<Arista> aristas = new LinkedList<Arista>();
		inicializarBusqueda(g, verticeOrigen);
		
		while(! L.isEmpty()) {
			int i = seleccionarYMarcarVertice();
			for (int vecino : g.getVecinos(i)) {
				if (! marcados[vecino]) {
					for (Arista arista : g.getAristas()) {
						if (g.esAristaBuscada(i, vecino, arista)) {
							aristas.addLast(arista);
						}
					}
				}
			}
			agregarVecinosNoMarcados(g, i);
			removerSeleccionado();	
		}
		return aristas;
	}
}
