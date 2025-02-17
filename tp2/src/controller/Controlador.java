package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import model.Arista;
import model.BFS;
import model.Grafo;
import model.Kruskal;
import model.Observer;
import model.Prim;
import model.Conexion;
import model.ConexionJSON;

public class Controlador {
    private Grafo grafo;
    private Grafo grafoAGM;
    private double tiempoEjecucionBusquedaAGM;
    private Observer observer;
    
    
    public Controlador(Observer observer) {
        this.observer = observer;
    }
    
    /*
     * --- Grafo ---
     */

    public void setGrafo(int cantVertices) {
        grafo = new Grafo(cantVertices);
        observer.notificar();
    }
    
	public boolean grafoEsConexo() {
		return BFS.esConexo(grafo);
	}
	
	public double getTiempoEjecucionBusquedaAGM() {
		return tiempoEjecucionBusquedaAGM;
	}

	/*
	 * --- Aristas ---
	 */
	
    public void agregarArista(int vertice1, int vertice2, double probabilidad) {
        grafo.agregarArista(vertice1, vertice2, probabilidad);
        observer.notificar();
    }
	
    public void agregarEspia(String nombreEspia) {
        grafo.agregarVertice(nombreEspia);
        observer.notificar();
    }
    
	public boolean existeArista(int e1, int e2) {
		return grafo.existeArista(e1, e2);
	}
		
	/*
	 * --- Espias (vertices) y sus nombres ---
	 */
	
    public boolean espiaYaRegistrado(String nombreEspia) {
        // Verifica si el espía ya está registrado en el grafo
        return grafo.obtenerVerticePorNombre(nombreEspia) != null;
    }
    
    public ArrayList<String> getNombresEspias() {
		return grafo.getNombresEspias();
	}
    
    public int obtenerVerticePorNombre(String nombre) {
		return grafo.obtenerVerticePorNombre(nombre);
	}
    
	public int cantidadEspiasAgregados() {
		return grafo.getCantEspias();
	}

    /*
     * --- Aristas en formato para interfaz ---
     */

	public ArrayList<Object[]> obtenerAristasFormateadas() {
        HashSet<Arista> aristas = grafo.getAristas();
        ArrayList<Object[]> aristasFormateadas = new ArrayList<>();

        for (Arista arista : aristas) {
            String espia1 = grafo.obtenerNombrePorVertice(arista.getVertice1());
            String espia2 = grafo.obtenerNombrePorVertice(arista.getVertice2());
            double probabilidad = arista.getProbabilidad();
            aristasFormateadas.add(new Object[]{espia1, espia2, probabilidad});
        }

        return aristasFormateadas;
	}
	
	private void agregarAristasFormateadas(LinkedList<Arista> verticesOrdenadosDelAGM,
			ArrayList<Object[]> aristasFormateadas) {
		for (Arista arista : verticesOrdenadosDelAGM) {
			String espia1 = grafo.obtenerNombrePorVertice(arista.getVertice1());
			String espia2 = grafo.obtenerNombrePorVertice(arista.getVertice2());
			double probabilidad = arista.getProbabilidad();
			aristasFormateadas.add(new Object[]{espia1, espia2, probabilidad});
		}
	}

	/*
	 * --- Generacion arbol generador minimo ---
	 */

	public ArrayList<Object[]> obtenerResultadosAGM(String metodo) {
		switch(metodo) {
			case "Prim":
				return obtenerResultadosAGMPrim();
			case "Kruskal":
				return obtenerResultadosAGMKruskal();
			default:
				throw new IllegalArgumentException("Metodo invalido.");
		}
	}
	
	/*
	 * --- Auxiliares arbol generador minimo ---
	 */

	private ArrayList<Object[]> obtenerResultadosAGMKruskal() {
		long inicioAlgoritmo = System.currentTimeMillis();
	    grafoAGM = Kruskal.obtenerArbolGeneradorMinimo(grafo);
	    long finAlgortimo = System.currentTimeMillis();
	    tiempoEjecucionBusquedaAGM = calcularTiempoDeEjecucion(inicioAlgoritmo, finAlgortimo);
		LinkedList<Arista> verticesOrdenadosDelAGM = BFS.recorridoSobreAristas(grafoAGM, 0);
		ArrayList<Object[]> aristasFormateadas = new ArrayList<>();
		
		agregarAristasFormateadas(verticesOrdenadosDelAGM, aristasFormateadas);
		return aristasFormateadas;
	}
	
	private ArrayList<Object[]> obtenerResultadosAGMPrim() {
		long inicioAlgortimo = System.currentTimeMillis();
	    grafoAGM = Prim.obtenerArbolGeneradorMinimo(grafo);
	    long finAlgortimo = System.currentTimeMillis();
	    tiempoEjecucionBusquedaAGM = calcularTiempoDeEjecucion(inicioAlgortimo, finAlgortimo);
	    
		LinkedList<Arista> verticesOrdenadosDelAGM = BFS.recorridoSobreAristas(grafoAGM, 0);
		ArrayList<Object[]> aristasFormateadas = new ArrayList<>();
		
		agregarAristasFormateadas(verticesOrdenadosDelAGM, aristasFormateadas);
		return aristasFormateadas;
	}

	private double calcularTiempoDeEjecucion(long inicio, long fin) {
		return (fin - inicio)/1000.0;
	}
	
	/*
	 * --- Lectura/Escritura formato JSON ---
	 */
	
	public void inicializarArchivoEjemploJSON() {
	        Conexion instanciaDDC1 = new Conexion("Espia1", "Espia2", 0.10);
	        Conexion instanciaDDC2 = new Conexion("Espia2", "Espia3", 0.10);
	        Conexion instanciaDDC3 = new Conexion("Espia1", "Espia3", 0.50);
	        ConexionJSON ddcJSON = new ConexionJSON();
	        ddcJSON.addDatos(instanciaDDC1);
	        ddcJSON.addDatos(instanciaDDC2);
	        ddcJSON.addDatos(instanciaDDC3);
	        String jsonPretty = ddcJSON.generarJSONPretty();
	        ddcJSON.guardarJSON(jsonPretty, "Datos.JSON");
	    }
	 
	public boolean leerArchivoGuardadoJSON(File archivoActual) {
		 try {
			 ArrayList<Object[]> datos = leerJSON(archivoActual);

			 inicializarGrafo(datos);

			 for (Object[] o : datos) {
				 String espia1 = (String) o[0];
				 String espia2 = (String) o[1];
				 double probabilidad = (double) o[2];

				 agregarEspiaSiNoRegistrado(espia1);
				 agregarEspiaSiNoRegistrado(espia2);
				 agregarAristaDesdeString(espia1, espia2, probabilidad);
			 }

			 observer.notificar(); // Notificar a la vista que el grafo ha sido actualizado
			 return true;
		    
		 } catch (Exception e) {
			 return false;
		 }	 
	 }
	 
	 /*
	  * --- Auxiliares lectura/escritura JSON ---
	  */

	private ArrayList<Object[]> leerJSON(File archivoActual) {
		ConexionJSON leido = ConexionJSON.leerJSON(archivoActual.getName());
		ArrayList<Object[]> datos = leido.getTodosLosDatos();
		return datos;
	}

	private void inicializarGrafo(ArrayList<Object[]> datos) {
		int cantVertices = calcularCantidadVertices(datos);
		grafo = new Grafo(cantVertices);
	}

	private void agregarAristaDesdeString(String espia1, String espia2, double probabilidad) {
		int vertice1 = grafo.obtenerVerticePorNombre(espia1);
		int vertice2 = grafo.obtenerVerticePorNombre(espia2);
		grafo.agregarArista(vertice1, vertice2, probabilidad);
	}
	 
	private void agregarEspiaSiNoRegistrado(String nombreEspia) {
		 if (!espiaYaRegistrado(nombreEspia)) {
			 grafo.agregarVertice(nombreEspia);
		 }
	}
	 
	private int calcularCantidadVertices(ArrayList<Object[]> datos) {
		 HashSet<String> nombresEspias = new HashSet<>();
		 for (Object[] o : datos) {
			 nombresEspias.add((String) o[0]);  // Espía 1
			 nombresEspias.add((String) o[1]);  // Espía 2
		 }
		 
		 return nombresEspias.size();
	}
	
}