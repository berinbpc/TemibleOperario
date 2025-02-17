package model;

public class Arista implements Comparable<Arista>{
	  int vertice1;
	  int vertice2;
	  double probabilidad;

	  /*
	   * ---- CONSTRUCTOR ----
	   */
	  
	  public Arista(int vertice1, int vertice2, double probabilidad) {
	        this.vertice1 = vertice1;
	        this.vertice2 = vertice2;
	        this.probabilidad = probabilidad;
	   }

	  /*
	   * ---- GETTERS ----
	   */
	  
	    public int getVertice1() {
	        return vertice1;
	    }

	    public int getVertice2() {
	        return vertice2;
	    }

	    public double getProbabilidad() {
	        return probabilidad;
	    }

	    /*
		 * ---- OTROS ----
		 */
	    
	    @Override
	    public int compareTo(Arista otra) {
	        return Double.compare(this.probabilidad, otra.probabilidad);
	    }
}
