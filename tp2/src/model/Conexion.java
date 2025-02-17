package model;

import java.io.Serializable;

public class Conexion implements Serializable {
	private String espia1;
	private String espia2;
	private double probabilidad;
	// Version del objeto
	private static final long serialVersionUID = 1;
	
	public Conexion(String espia1, String espia2, double probabilidad) {
		this.espia1 = espia1;
		this.espia2 = espia2;
		this.probabilidad = probabilidad;
	}
	
	public String getEspia1() {
		return espia1;
	}
	
	public String getEspia2() {
		return espia2;
	}
	
	public double getProbabilidad() {
		return probabilidad;
	}
	

}

