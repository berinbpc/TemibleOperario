package model;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {
    private static Grafo grafoAGM;
    private static List<Arista> aristasOrdenadas;

    public static Grafo obtenerArbolGeneradorMinimo(Grafo grafo) {
    	verificarSiEsConexo(grafo);
        inicializarGrafoAGM(grafo);
        encontrarAGM(grafo);
        return grafoAGM;
    }

    private static void verificarSiEsConexo(Grafo grafo) {
		if (!BFS.esConexo(grafo)) {
			throw new IllegalArgumentException("El grafo no es conexo.");
		}
	}
  
    private static void inicializarGrafoAGM(Grafo grafo) {
        grafoAGM = new Grafo(grafo.getCantVertices());
        aristasOrdenadas = obtenerAristasOrdenadas(grafo);
    }

    private static List<Arista> obtenerAristasOrdenadas(Grafo grafo) {
        List<Arista> aristas = new ArrayList<>(grafo.getAristas());
        aristas.sort(null);															//usa el compareTo de la clase arista para ordenar 
        return aristas;
    }

    private static void encontrarAGM(Grafo grafo) {
        UnionFind uf = new UnionFind(grafo.getCantVertices());
        int aristasAgregadas = 0;

        while (aristasAgregadas < grafo.getCantVertices() - 1 && !aristasOrdenadas.isEmpty()) {
            Arista aristaMinima = obtenerSiguienteArista();

            if (!uf.find(aristaMinima.getVertice1(), aristaMinima.getVertice2())) {
                agregarAristaAlAGM(aristaMinima);
                uf.union(aristaMinima.getVertice1(), aristaMinima.getVertice2());
                aristasAgregadas++;
            }
        }
    }

    private static Arista obtenerSiguienteArista() {
        return aristasOrdenadas.remove(0); 											// Tomo la arista de menor peso
    }

    private static void agregarAristaAlAGM(Arista arista) {
        grafoAGM.agregarArista(arista.getVertice1(), arista.getVertice2(), arista.getProbabilidad());
    }
}