package model;

import java.util.HashSet;

public class Prim {
	private static HashSet<Integer> verticesMarcados;
	private static Grafo grafoAGM;
	
	public static Grafo obtenerArbolGeneradorMinimo(Grafo grafo) {  	
		verificarSiEsConexo(grafo);
        inicializarGrafoAGM(grafo);
        encontrarAGM(grafo);
        return grafoAGM;
    }
	
	/*
	 * ---
	 */
	
	private static void verificarSiEsConexo(Grafo grafo) {
		if (!BFS.esConexo(grafo)) {
			throw new IllegalArgumentException("El grafo no es conexo.");
		}
	}
	
	private static void inicializarGrafoAGM(Grafo grafo) {
		grafoAGM = new Grafo(grafo.getCantVertices());
        verticesMarcados = new HashSet<>();
        verticesMarcados.add(grafo.getVertice(0));
	}
	
	private static void encontrarAGM(Grafo grafo) {
		while (verticesMarcados.size() < grafo.getCantVertices()) {
        	Arista aristaMinimoPeso = hallarAristaConPesoMinimo(grafo);
        	agregarAristaEnAGM(aristaMinimoPeso);
        }
	}
	
	/*
	 * ---
	 */
	
	private static Arista hallarAristaConPesoMinimo(Grafo grafoCompleto) {
		Arista aristaConPesoMinimo = null;
		double pesoMinimo = Float.POSITIVE_INFINITY;

		for (Integer vertice : verticesMarcados) {
			HashSet<Integer> vecinos = grafoCompleto.getVecinos(vertice);
			for (Integer vecino : vecinos) {
				if (noEstaVisitado(vecino) && noSonVecinosEnAGM(vertice, vecino)) {
					double pesoVerticesActuales = grafoCompleto.getProbabilidad(vertice, vecino);
					if (pesoVerticesActuales <= pesoMinimo) {
						pesoMinimo = pesoVerticesActuales;
						aristaConPesoMinimo = new Arista(vertice, vecino, pesoVerticesActuales);
					}
				}
			}
		}
		return aristaConPesoMinimo;
	}
	
	private static void agregarAristaEnAGM(Arista aristaMinimoPeso) {
		 Integer vertice1 = aristaMinimoPeso.getVertice1();
	     Integer vertice2 = aristaMinimoPeso.getVertice2();
	     grafoAGM.agregarArista(vertice1, vertice2, aristaMinimoPeso.getProbabilidad());
	     marcarVertice(vertice1);
	     marcarVertice(vertice2);
	 }
	
	/*
	 * ---
	 */
	
	private static boolean noSonVecinosEnAGM(Integer vertice, Integer vecino) {
		return !grafoAGM.existeArista(vertice, vecino);
	}

	private static boolean noEstaVisitado(Integer vecino) {
		return !verificarSiEstaEnAGM(vecino);
	}

	private static void marcarVertice(Integer vertice) {
		verticesMarcados.add(vertice);
	}
	
	private static boolean verificarSiEstaEnAGM(Integer vertice) {
		return verticesMarcados.contains(vertice);
	}
	
}