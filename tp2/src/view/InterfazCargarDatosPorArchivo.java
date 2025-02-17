package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import controller.Controlador;
import model.Observer;

public class InterfazCargarDatosPorArchivo implements Observer{

	private JFrame ventanaPrincipal;
	private JTextArea textArea;
	private JScrollPane scrollTextArea;
	private File archivoActual;
	private JLabel labelInstrucciones;
	private JButton btnMostrarArchivo;
	private JButton btnGuardarArchivo;
	private Controlador controlador;

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
	 */
	public InterfazCargarDatosPorArchivo() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            controlador = new Controlador(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		inicializarVentanaPrincipal();
		inicializarArchivoEjemploJSON();
		generarTextoInstrucciones();
		generarBotonAbrirArchivo();
		generarBotonGuardarArchivo();
		
	}
	
	/*
	 * --- Auxiliares intializate ---
	 */
	
	private void inicializarVentanaPrincipal() {
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setBounds(100, 100,700,600);
		ventanaPrincipal.setTitle("Carga de datos en archivo");
		ventanaPrincipal.setLocationRelativeTo(null);	// Pone la ventana en el centro de la pantalla del usuario
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.getContentPane().setLayout(null);
	}

	private void inicializarArchivoEjemploJSON() {
		controlador.inicializarArchivoEjemploJSON();
	}

	private void generarTextoInstrucciones() {
		labelInstrucciones = new JLabel("Ingrese los datos en el siguiente archivo de texto, respetando el formato dado.");
		labelInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		labelInstrucciones.setBounds(10, 11, 664, 23);
		ventanaPrincipal.getContentPane().add(labelInstrucciones);
	}
	
	private void generarBotonAbrirArchivo() {
		btnMostrarArchivo = new JButton("Mostrar archivo JSON");
		btnMostrarArchivo.setBounds(253, 37, 182, 23);
		ventanaPrincipal.getContentPane().add(btnMostrarArchivo);

		btnMostrarArchivo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		generarTextArea();
	    		generarScrollTextArea();
	    		abrirArchivo();
	    		activarBotonGuardarArchivo();
	    	}
	    });
	}
	
	private void generarBotonGuardarArchivo() {
		btnGuardarArchivo = new JButton("Guardar archivo JSON");
		btnGuardarArchivo.setEnabled(false);
		btnGuardarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarArchivo();
					
				if (leerArchivoGuardado()) {
		            if (!controlador.grafoEsConexo()) {
		                Auxiliares.mostrarError("No todos los espias estan conectados.", ventanaPrincipal);
		                return;
		            }
		            Auxiliares.mostrarMensaje("Archivo guardado/leido exitosamente. Se abrirá una ventana mostrando el resultado final.", ventanaPrincipal);
					Auxiliares.cerrarVentanaActual(ventanaPrincipal);
					Auxiliares.pasarALaInterfazResultado(controlador);
					
				} else {
					Auxiliares.mostrarError("El archivo ingresado no respeta el formato, intente de nuevo", ventanaPrincipal);
					return;
				}
				
			}
		});
		btnGuardarArchivo.setBounds(253, 527, 182, 23);
		ventanaPrincipal.getContentPane().add(btnGuardarArchivo);
	}
	
	/*
	 * --- Auxiliares generarBotonAbrirArchivo ---
	 */
	
	private void generarTextArea() {
		textArea = new JTextArea();
		textArea.setBounds(10, 65, 664, 419);
		ventanaPrincipal.getContentPane().add(textArea);
	}
	
	private void abrirArchivo() {
		archivoActual = new File("Datos.JSON");
		
		if (archivoActual.exists()) {
	           try {
	        	   BufferedReader reader = new BufferedReader(new FileReader(archivoActual));
	               String lineaDeTextoActual;
	               StringBuilder textoArchivo = new StringBuilder();
	               while ((lineaDeTextoActual = reader.readLine()) != null) {
	                   textoArchivo.append(lineaDeTextoActual).append("\n");
	               }
	               textArea.setText(textoArchivo.toString());  // Mostrar el contenido del JSON en el JTextArea
	               reader.close();
	           } catch (IOException e) {
	               e.printStackTrace();
	             
	           }
        }
		
	}
	
	private void generarScrollTextArea() {
		scrollTextArea = new JScrollPane(textArea);
		scrollTextArea.setBounds(10, 65, 664, 419);	// Se pone con las mismas dimensiones de la tabla (table)
		ventanaPrincipal.getContentPane().add(scrollTextArea);
	}
	
	private void activarBotonGuardarArchivo() {
		btnGuardarArchivo.setEnabled(true);
	}
	
	/*
	 * --- Auxiliares generarBotonGuardarArchivo ---
	 */
	
    private void guardarArchivo() {
        if (archivoActual != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoActual))) {
                writer.write(textArea.getText());  // Guardar el contenido del JTextArea en el archivo JSON
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
	private boolean leerArchivoGuardado() {
		return controlador.leerArchivoGuardadoJSON(archivoActual);
	}
	
	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		
	}

}
