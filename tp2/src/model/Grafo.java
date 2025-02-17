package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Grafo {
    private ArrayList<HashSet<Integer>> listaVecinos;
    private HashSet<Arista> aristas;
    private int cantVertices;
    private HashMap<String, Integer> mapaEspias; // HashMap para asociar nombres a índices
    private ArrayList<String> nombresEspias; // Lista para almacenar los nombres


    /*
     * ---- CONSTRUCTORES ----
     */
    
	/**
	 * Crea un grafo con la cantidad de nodos/vertices indicada.
	 * @param cantVertices cantidad de vertices que tendra el grafo.
	 * @throws IllegalArgumentException si la cantidad de vertices es 0 o negativa.
	 */
    public Grafo(int cantVertices) {
    	if (cantVertices > 0) {
    		this.cantVertices = cantVertices;
    		
    		inicializarListaDeVecinos(cantVertices);
    		
    		aristas = new HashSet<>();
            mapaEspias = new HashMap<>();
            nombresEspias = new ArrayList<>();
    		
    		
    	} else {
    		throw new IllegalArgumentException("Cantidad de vertices no puede ser menor o igual a 0");
    	}
    }
    
	/*
	 * ---- VÉRTICES (AGREGAR, OBTENER, ELIMINAR) ----
	 */
    
    public void agregarVertice(String nombreEspia) {
    	verificarCantidadEspias();
    	verificarEspiaDuplicado(nombreEspia);
        int indice = mapaEspias.size(); // El índice es igual al tamaño actual del mapa
        mapaEspias.put(nombreEspia, indice);
        nombresEspias.add(nombreEspia); // Agregar el nombre a la lista
    }
    
	private void agregarVecino(int vertice, int vecino) {
		// Vertice -> Vecino
        listaVecinos.get(vertice).add(vecino);
        // Vecino -> Vertice
        listaVecinos.get(vecino).add(vertice);
	}
    
    // Método para obtener el índice de un espía dado su nombre
    public Integer obtenerVerticePorNombre(String nombreEspia) {
        return mapaEspias.get(nombreEspia);
    }

    // Agregar un método para obtener el nombre de un espía dado su índice
    public String obtenerNombrePorVertice(int vertice) {
        if (vertice < 0 || vertice >= nombresEspias.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de los límites: " + vertice);
        }
        return nombresEspias.get(vertice);
    }
    
	private void eliminarVecinos(int vertice, int vecino) {
		// Vertice -/> Vecino
        listaVecinos.get(vertice).remove(vecino);
        // Vecino -/> Vertice
        listaVecinos.get(vecino).remove(vertice);
	}
  
	/*
	 * ---- ARISTAS (AGREGAR, ELIMINAR, EXISTE) ----
	 */

	/**
	 * Agrega una arista entre dos vertices dados con un valor de probabilidad (o peso) asociada a esa arista.
	 * @param vertice un vertice que tendrá la arista.
	 * @param vecino otro vertice que tendrá esa misma arista.
	 * @param probabilidad peso de esa arista.
	 * @throws IllegalArgumentException si la probabilidad esta fuera del rango [0, 1] o si los vertices estan fuera de rango o generan un ciclo.
	 */
    public void agregarArista(int vertice, int vecino, double probabilidad) {
    	verificarValidezVertices(vertice, vecino);
    	verificarValidezProbabilidad(probabilidad);
    	agregarVecino(vertice, vecino);
        
        Arista a = new Arista(vertice, vecino, probabilidad);
        aristas.add(a);
    }

	/**
	 * Elimina una arista ya existente entre dos vertices.
	 * @param vertice un vertice que contiene a la arista.
	 * @param vecino otro vertice que contiene a esa misma arista.
	 * @throws IllegalArgumentException si los vertices estan fuera del rango.
	 */
    public void eliminarArista(int vertice, int vecino) {
    	verificarValidezVertices(vertice, vecino);
    	eliminarVecinos(vertice, vecino);
    	
    	Arista aristaAEliminar = encontrarAristaParaEliminar(vertice, vecino);
    	aristas.remove(aristaAEliminar);
    }

	private Arista encontrarAristaParaEliminar(int vertice, int vecino) {
		Arista aristaAEliminar = null;
    	
    	for (Arista a : aristas) {
    		if (esAristaBuscada(vertice, vecino, a)) {
    			aristaAEliminar = a;
    		}
    	}
    	
		return aristaAEliminar;
	}
    
	/**
	 * Elimina una arista ya existente entre dos vertices.
	 * @param vertice un vertice que puede contener la arista.
	 * @param vecino otro vertice que puede contener a esa arista.
	 * @return {@code true} si existe arista entre esos dos vertices.
	 */
    public boolean existeArista(int vertice, int vecino) {
    	return listaVecinos.get(vertice).contains(vecino);
    }
    
    /*
     * ---- GETTERS ----
     */

	/**
	 * Devuelve un conjunto de vecinos de un vertice dado.
	 * @param vertice vertice que se desea conocer sus vecinos.
	 * @return {@code HashSet<Integer>} con los vecinos de ese vertice.
	 */
    public HashSet<Integer> getVecinos(int vertice) {
    	if (vertice < 0 || vertice >= listaVecinos.size()) {
            throw new IndexOutOfBoundsException("El vértice " + vertice + " está fuera de los límites.");
        }
        return listaVecinos.get(vertice);
    }
    
	/**
	 * Devuelve la probabilidad (o peso) de una arista.
	 * @param vertice un vertice que contenga una arista.
	 * @param vecino otro vertice que contenga a esa misma arista.
	 * @return La probabilidad de esa arista o -1 si no existe esa arista.
	 */
    public double getProbabilidad(int vertice, int vecino) {
    	double probabilidad = encontrarProbabilidadDeArista(vertice, vecino);
    	return probabilidad;
    }

	private double encontrarProbabilidadDeArista(int vertice, int vecino) {
		double probabilidad = 0;
    	
    	for (Arista a : aristas) {
    		if (esAristaBuscada(vertice, vecino, a)) {
    			probabilidad = a.getProbabilidad();
    		}
    	}
    	
		return probabilidad;
	}
    
    public int getCantVertices() {
    	return cantVertices;
    }
    
	public Integer getVertice(int i) {
		if (i < 0 || i >= listaVecinos.size()) {
	        throw new IndexOutOfBoundsException("Índice fuera de los límites: " + i);
	    }
	    return i;  // El índice ya representa el vértice, por lo que simplemente lo devuelve
	}
	
	public int getCantidadDeAristas() {
	    int totalAristas = 0;
	 
	    for (HashSet<Integer> vecinos : listaVecinos) {
	        totalAristas += vecinos.size();
	    }

	    return totalAristas / 2;
	}
	
	public HashSet<Arista> getAristas() {
	    return aristas;
	}
	
	public int getCantEspias() {
		return this.nombresEspias.size();
	}
	
	public ArrayList<String> getNombresEspias(){
		return this.nombresEspias;
	}
    
    /*
     * ---- INICIALIZACIONES ----
     */
    
	private void inicializarListaDeVecinos(int cantVertices) {
		listaVecinos = new ArrayList<HashSet<Integer>>(cantVertices);
		
		for (int i = 0; i < cantVertices; ++i) {
			listaVecinos.add(new HashSet<Integer>());
		}
	}
	
	/*
	 * ---- VERIFICACIONES ----
	 */

	public boolean esAristaBuscada(int vertice, int vecino, Arista a) {
		return a.getVertice1() == vertice && a.getVertice2() == vecino ||
				a.getVertice1() == vecino && a.getVertice2() == vertice;
	}
	
	private void verificarEspiaDuplicado(String nombreEspia) {
		if (mapaEspias.containsKey(nombreEspia)) {
            throw new IllegalArgumentException("El espía ya está registrado: " + nombreEspia);
        }
	}


	private void verificarCantidadEspias() {
		if (mapaEspias.size() >= cantVertices) {
            throw new IllegalStateException("No se pueden agregar más espías; se alcanzó el límite de vértices.");
        }
	}
	
	private void verificarValidezProbabilidad(double probabilidad) {
		if (! esProbabilidadValida(probabilidad)) {
			throw new IllegalArgumentException("La probabilidad debe estar entre 0 y 1");
		}
	}

	private void verificarValidezVertices(int vertice, int vecino) {
		if (vertice >= cantVertices) {
			throw new IllegalArgumentException("Vertice ingresado: " + vertice + " no esta en el grafo de " + cantVertices + " vertices.");
		}
		if (vecino >= cantVertices) {
			throw new IllegalArgumentException("Vertice ingresado: " + vecino + " no esta en el grafo de " + cantVertices + " vertices.");
		}
		if (vecino == vertice) {
			throw new IllegalArgumentException("Vertices ingresados (" + vertice + " - " + vecino + ") generan un ciclo en el grafo.");
		}
	}
	
    private boolean esProbabilidadValida(double probabilidad) {
    	return probabilidad >= 0.00 && probabilidad <= 1.00;
    }
    
    public String toString() {
    	StringBuilder str = new StringBuilder();
    	for (Arista a : aristas) {
    		str.append(a.getVertice1() + ", " + a.getVertice2() + ", " + a.getProbabilidad() + "\n");
    		str.append(obtenerNombrePorVertice(a.getVertice1()) + ", " + obtenerNombrePorVertice(a.getVertice2()) + "\n");
    	}
    	return str.toString();
    }
    
}
