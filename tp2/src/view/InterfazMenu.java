package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class InterfazMenu {

	private JFrame ventanaMenu;
	private JLabel labelTitulo;
	private JTextArea textAreaDescripcion;
	private JButton btnIngresarDatosManuales;
	private JButton btnIngresarDatosArchivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazMenu window = new InterfazMenu();
					window.ventanaMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazMenu() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicializarVentanaMenu();
		generarTitulo();
		generarDescripción();
		generarBotonDatosManuales();
		generarBotonDatosArchivo();
	}
	
	/*
	 * --- Auxiliares de intializate ---
	 */

	private void inicializarVentanaMenu() {
		ventanaMenu = new JFrame();
		ventanaMenu.setTitle("Temible operario del recontra espionaje");
		ventanaMenu.setBounds(100, 100, 625, 407);
		ventanaMenu.setLocationRelativeTo(null);	// Pone la ventana en el centro de la pantalla del usuario
		ventanaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaMenu.getContentPane().setLayout(null);
	}

	private void generarTitulo() {
		labelTitulo = new JLabel("Temible operario del recontra espionaje.");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setBounds(10, 11, 589, 35);
		ventanaMenu.getContentPane().add(labelTitulo);
	}
	
	private void generarDescripción() {
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaDescripcion.setText("Bienvenido, este programa le permite conocer de qué manera tiene que contactarse los espías"
									+ "\na fin de que un mensaje sea recibido con el menor riesgo de ser descubierto por el enemigo."
									+ "\n"
									+ "\nPara ello, usted debe indicar cuántos espías hay, cuáles espías pueden encontrarse para"
									+ "\ncomunicar el mensaje y cuál es la probabilidad de que el enemigo descubra ese encuentro."
									+ "\n"
									+ "\nTiene dos maneras de ingresar estos datos:"
									+ "\n"
									+ "\n  - Manualmente: Primero establece cuántos espías hay y los nombres de cada uno y luego"
									+ "\n  indica la probabilidad de que los espías sean descubiertos en el encuentro, debe ase-"
									+ "\n  gurarse de que todos los espías esten conectados."
									+ "\n"
									+ "\n  - Archivo: Se le da un archivo de texto, usted puede rellenar respetando el formato dado.");
		textAreaDescripcion.setBounds(30, 57, 547, 228);
		textAreaDescripcion.setOpaque(false);
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setBackground(new Color(0,0,0,0));
		ventanaMenu.getContentPane().add(textAreaDescripcion);
	}

	private void generarBotonDatosArchivo() {
		btnIngresarDatosManuales = new JButton("Ingresar datos por archivo");
		btnIngresarDatosManuales.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIngresarDatosManuales.setBounds(343, 311, 192, 23);
		ventanaMenu.getContentPane().add(btnIngresarDatosManuales);
		btnIngresarDatosManuales.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				pasarALaVentanaCargarDatosPorArchivo();
	        }


	    });
	}

	private void generarBotonDatosManuales() {
		btnIngresarDatosArchivo = new JButton("Ingresar datos manualmente");
		btnIngresarDatosArchivo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIngresarDatosArchivo.setBounds(63, 310, 192, 23);
		ventanaMenu.getContentPane().add(btnIngresarDatosArchivo);
		btnIngresarDatosArchivo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				pasarALaVentanaCargarDatosManualmente();
	        }


	    });
	}
	
	/*
	 * --- Auxiliares de cambio de pantalla ---
	 */
	
	private void pasarALaVentanaCargarDatosPorArchivo() {
		Auxiliares.cerrarVentanaActual(ventanaMenu);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InterfazCargarDatosPorArchivo(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void pasarALaVentanaCargarDatosManualmente() {
		Auxiliares.cerrarVentanaActual(ventanaMenu);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InterfazCargarDatosManualmente(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
