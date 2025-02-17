package model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConexionJSON {
	// Objetos tipo "DatosDeConexion" para convertir en JSON
	public ArrayList<Conexion> datos;
	
	public ConexionJSON() {
		datos = new ArrayList<Conexion>();
	}
	
	public void addDatos(Conexion d) {
		datos.add(d);
	}

	public ArrayList<Object[]> getTodosLosDatos() {
		ArrayList<Object[]> todosLosDatos = new ArrayList<Object[]>();
		for (Conexion d: datos) {
			Object[] o = new Object[3];
			o[0] = d.getEspia1();
			o[1] = d.getEspia2();
			o[2] = d.getProbabilidad();
			todosLosDatos.add(o);
		}
		return todosLosDatos;
	}
	
	
	public String generarJSONPretty() {
		// Crea una instancia de GSON pretty
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Convierte este objeto (DatosDeConexionJSON y por ende, cada uno de los indices de datos) 
		// a un string (largo) en formato JSON
		String json = gson.toJson(this);
		
		return json;
	}
	
	public void guardarJSON(String jsonParaGuardar, String archivoDestino) {
		try {
			// Crea un objeto FileWriter para escribir sobre el archivo ubicando en la direccion dada.
			FileWriter writer = new FileWriter(archivoDestino);
			// Escribe el string largo obtenido anteriormente
			writer.write(jsonParaGuardar);
			writer.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static ConexionJSON leerJSON(String archivo) {
		
		Gson gson = new Gson();
		ConexionJSON ret = null;
		
		try {
			// Se le pasa la referencia logica del "archivo"
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			// gson.fromJson, se le pasa el bufferedreader y se le indica que se esta leyendo 
			// datosDeConexion
			ret = gson.fromJson(br, ConexionJSON.class);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return ret;
	}

}
