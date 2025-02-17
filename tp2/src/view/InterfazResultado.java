package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import controller.Controlador;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class InterfazResultado {

	private JFrame ventanaResultado;
	private Controlador controlador;
	private JTable tableConexiones;
	private JTable tableResultados;
	private JLabel labelConexiones;
	private JLabel labelResultados;
	private JLabel labelTiempoDeEjecucion;
	private JLabel labelSegundosEnEjecucion;
	private JScrollPane scrollTableConexiones;
	private JScrollPane scrollTableResultados;
	private DefaultTableModel tableConexionesModel;
	private DefaultTableModel tableResultadosModel;
	private String algoritmoElegido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
			       
                   
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public InterfazResultado(Controlador controlador) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.controlador = controlador;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicializarVentanaResultado();
		mostrarVentanaSeleccionAlgortimo();
		generarLabelConexiones();
		generarTablaConexiones();
		generarLabelResultados();
		generarTablaResultados();
		generarLabelTiempoDeEjecucion();
		generarLabelSegundosEnEjecucion();
	}

	/*
	 * --- Auxiliares intializate ---
	 */
	
	private void inicializarVentanaResultado() {
		ventanaResultado = new JFrame();
		ventanaResultado.setTitle("Resultados");
		ventanaResultado.setBounds(100, 100, 769, 440);
		ventanaResultado.setLocationRelativeTo(null);	// Pone la ventana en el centro de la pantalla del usuario
		ventanaResultado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaResultado.getContentPane().setLayout(null);
	}
	
	private void mostrarVentanaSeleccionAlgortimo() {
		String[] opciones = {"Prim", "Kruskal"};
		
		int respuesta = JOptionPane.showOptionDialog(
				null, 
				"Seleccione un método de resolución", 
				"Método", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, 
				null, 
				opciones, 
				opciones[0]);
		
		if (respuesta == 0) {
			algoritmoElegido = "Prim";
		} else {
			algoritmoElegido = "Kruskal";
		}
	}

	private void generarLabelConexiones() {
		labelConexiones= new JLabel("Conexiones");
		labelConexiones.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelConexiones.setHorizontalAlignment(SwingConstants.LEADING);
		labelConexiones.setBounds(20, 11, 150, 30);
        ventanaResultado.getContentPane().add(labelConexiones);
	}
	
	private void generarTablaConexiones() {
		 tableConexionesModel = new DefaultTableModel();
		 tableConexiones = new JTable(tableConexionesModel);
		 scrollTableConexiones = new JScrollPane(tableConexiones);
		 scrollTableConexiones.setBounds(10, 40, 400, 350);
		 ventanaResultado.getContentPane().add(scrollTableConexiones);
	        
		 llenarTablaConexiones();
	        
		 ventanaResultado.setVisible(true);
	 }

	private void generarLabelResultados() {
		labelResultados= new JLabel("En este orden deben encontrarse los espías:");
		labelResultados.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelResultados.setHorizontalAlignment(SwingConstants.LEADING);
		labelResultados.setBounds(430, 11, 300, 30);
        ventanaResultado.getContentPane().add(labelResultados);
	}
	    
	private void generarTablaResultados() {
		 tableResultadosModel = new DefaultTableModel();
		 tableResultados = new JTable(tableResultadosModel);
		 scrollTableResultados = new JScrollPane(tableResultados);
		 scrollTableResultados.setBounds(420, 40, 323, 310);
		 ventanaResultado.getContentPane().add(scrollTableResultados);

		 // Llenar la tabla con los resultados del algoritmo de Prim
		 llenarTablaResultados();
	 }
	 
	private void generarLabelTiempoDeEjecucion() {
		 labelTiempoDeEjecucion = new JLabel("Tiempo de ejecución del algortimo:");
		 labelTiempoDeEjecucion.setHorizontalAlignment(SwingConstants.LEADING);
		 labelTiempoDeEjecucion.setFont(new Font("Tahoma", Font.BOLD, 13));
		 labelTiempoDeEjecucion.setBounds(430, 360, 234, 30);
		 ventanaResultado.getContentPane().add(labelTiempoDeEjecucion);
	 }
	 
	private void generarLabelSegundosEnEjecucion() {
		 labelSegundosEnEjecucion = new JLabel(controlador.getTiempoEjecucionBusquedaAGM() + "s");
		 labelSegundosEnEjecucion.setHorizontalAlignment(SwingConstants.RIGHT);
		 labelSegundosEnEjecucion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 labelSegundosEnEjecucion.setBounds(593, 361, 140, 30);
		 ventanaResultado.getContentPane().add(labelSegundosEnEjecucion);
	 }
	 
	 /*
	  * --- Auxiliares para rellenar las tablas ---
	  */
	 
	private void llenarTablaConexiones() {
		 String[] columnas = {"Espia 1", "Espia 2", "Probabilidades"};
		 tableConexionesModel.setColumnIdentifiers(columnas);
		 ArrayList<Object[]> aristasFormateadas = controlador.obtenerAristasFormateadas();
		 for (Object[] fila : aristasFormateadas) {
			 tableConexionesModel.addRow(fila);
		 }
	 }
	 
	private void llenarTablaResultados() {
		 String[] columnasResultados = {"Espía 1", "Espía 2", "Probabilidad de Intercepción"};
		 tableResultadosModel.setColumnIdentifiers(columnasResultados);
		 // Obtener las aristas con sus probabilidades del AGM
		 ArrayList<Object[]> resultadosPrim = controlador.obtenerResultadosAGM(algoritmoElegido); 

		 for (Object[] fila : resultadosPrim) {
			 tableResultadosModel.addRow(fila);
		 }
	 }
}