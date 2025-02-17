package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import controller.Controlador;
import model.Observer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazVecinos implements Observer {

	private JFrame ventanaVecinos;
	private Controlador controlador;
	private JTextField txtProb;
	private JComboBox<String> comboBoxE1;
	private JComboBox <String >comboBoxE2;

	private JTable tablaConexiones;
	private DefaultTableModel modeloTablaConexiones;
	private JScrollPane scrollTablaConexiones;

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
	public InterfazVecinos(Controlador controlador) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.controlador = controlador;
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		initialize();
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicializarVentanaVecinos();
		
		generarLabelsEspias();
		generarCmbBoxEspias();
		generarLblProb();
		generarTxtProb();
		generarBtnConex();
		cargarEspiasEnComboBox();
		
		generarBtnConfirmarConex();
		
		generarTablaConexiones();
		generarScrollTablaConexiones();
	}



	private void generarScrollTablaConexiones() {
		scrollTablaConexiones = new JScrollPane(tablaConexiones);
		scrollTablaConexiones.setBounds(20, 109, 367, 327);	// Se pone con las mismas dimensiones de la tabla (table)
		ventanaVecinos.getContentPane().add(scrollTablaConexiones);
	}

	private void generarTablaConexiones() {
		tablaConexiones = new JTable();
		tablaConexiones.setBounds(20, 109, 367, 327);
		ventanaVecinos.getContentPane().add(tablaConexiones);
		
		modeloTablaConexiones = new DefaultTableModel();
		
		modeloTablaConexiones.addColumn("Nombre espia 1");
		modeloTablaConexiones.addColumn("Nombre espia 2");
		modeloTablaConexiones.addColumn("Probabilidad de intercepcion");
		
		tablaConexiones.setModel(modeloTablaConexiones);
	}



	private void inicializarVentanaVecinos() {
		ventanaVecinos = new JFrame();
		ventanaVecinos.setTitle("Conexiones y Probabilidades");
		ventanaVecinos.setBounds(100,100,700,600);
		ventanaVecinos.setLocationRelativeTo(null);	// Pone la ventana en el centro de la pantalla del usuario
		ventanaVecinos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaVecinos.getContentPane().setLayout(null);
	}

	private void generarLabelsEspias() {
		generarLabelEspia1();
		generarLabelEspia2();
	}

	private void generarLabelEspia2() {
		JLabel lblEspia2 = new JLabel("Espia 2");
		lblEspia2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspia2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEspia2.setBounds(150, 39, 110, 26);
		ventanaVecinos.getContentPane().add(lblEspia2);
	}

	private void generarLabelEspia1() {
		JLabel lblEspia1 = new JLabel("Espia 1");
		lblEspia1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspia1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEspia1.setBounds(10, 39, 110, 26);
		ventanaVecinos.getContentPane().add(lblEspia1);
	}

	private void generarCmbBoxEspias() {
		generarCmbBoxEspia1();
		generarCmbBoxEspia2();
	}

	private void generarCmbBoxEspia2() {
		comboBoxE2 = new JComboBox<String>();
		comboBoxE2.setBounds(160, 76, 88, 22);
		ventanaVecinos.getContentPane().add(comboBoxE2);
	}

	private void generarCmbBoxEspia1() {
		comboBoxE1 = new JComboBox<String>();
		comboBoxE1.setToolTipText("");
		comboBoxE1.setBounds(20, 76, 88, 22);
		ventanaVecinos.getContentPane().add(comboBoxE1);
	}
	
	private void generarLblProb() {
		JLabel lblProb = new JLabel("Probabilidad");
		lblProb.setHorizontalAlignment(SwingConstants.CENTER);
		lblProb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProb.setBounds(425, 39, 110, 26);
		ventanaVecinos.getContentPane().add(lblProb);
	}
	
	private void generarTxtProb() {
		txtProb = new JTextField();
		txtProb.setBounds(435, 77, 100, 20);
		ventanaVecinos.getContentPane().add(txtProb);
		txtProb.setColumns(10);
	}

	private void generarBtnConex() {
	    JButton btnCrearConex = new JButton("Crear conexión");
	    btnCrearConex.setBounds(460, 130, 156, 39);
	    ventanaVecinos.getContentPane().add(btnCrearConex);
	    

	    ventanaVecinos.setVisible(true);

	    btnCrearConex.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	String nombreEspia1 = (String) comboBoxE1.getSelectedItem();
	        	String nombreEspia2 = (String) comboBoxE2.getSelectedItem();
	        	int espia1 = controlador.obtenerVerticePorNombre(nombreEspia1);
                int espia2 = controlador.obtenerVerticePorNombre(nombreEspia2);
	            if (existeBucle()) {
	                Auxiliares.mostrarError("Un espía no se puede comunicar con sí mismo, seleccione un par distinto entre sí", ventanaVecinos);
	                return;
	            }

	            String textoProb = txtProb.getText().trim();
	            if (textoProb.isEmpty()) {
	            	Auxiliares.mostrarError("Debe ingresar alguna probabilidad", ventanaVecinos);
	                return;
	            }

	            try {
	                double probabilidad = Double.parseDouble(textoProb);
	                if (probabilidad <= 0 || probabilidad >= 1) {
	                	Auxiliares.mostrarError("La probabilidad debe ser un número entre 0 y 1.", ventanaVecinos);
	                    return;
	                }if(controlador.existeArista(espia1, espia2)) {
	                	Auxiliares.mostrarError("Ya existe la conexion entre el espia " + (String) comboBoxE1.getSelectedItem() + " y " + (String) comboBoxE2.getSelectedItem(), ventanaVecinos);
	                	return;
	                }
	            
	                controlador.agregarArista(espia1, espia2, probabilidad);
	                Auxiliares.mostrarMensaje("Conexion agregada con exito", ventanaVecinos);
	                
	                modeloTablaConexiones.addRow(new Object[] {nombreEspia1, nombreEspia2, probabilidad});
	       
	            } catch (NumberFormatException ex) {
	            	Auxiliares.mostrarError("Debe ingresar un número válido.", ventanaVecinos);
	                return;
	            }
	        }
	    });
	}

	private void generarBtnConfirmarConex() {
		JButton btnConfirmarConexiones = new JButton("Confirmar conexiones");
	    btnConfirmarConexiones.setBounds(460, 190, 156, 39);
	    ventanaVecinos.getContentPane().add(btnConfirmarConexiones);
	    btnConfirmarConexiones.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		/*
	    		 * Puede agregarse alguna funcionalidad que sugiera conectar un espia para
	    		 * que el grafo sea conexo.
	    		 */
	    		if (! controlador.grafoEsConexo()) {
	    			Auxiliares.mostrarError("No todos los espias estan conectados.", ventanaVecinos);
	                return;
	    		} else {
					Auxiliares.cerrarVentanaActual(ventanaVecinos);
					Auxiliares.pasarALaInterfazResultado(controlador);
	    		}
	    	}
	    });
	}
	
	private boolean existeBucle() {
		String e1 = (String) comboBoxE1.getSelectedItem();
		String e2 = (String) comboBoxE2.getSelectedItem();
		return e1.equals(e2);
		}
	
	
	private void cargarEspiasEnComboBox() {
        ArrayList<String> nombresEspias = controlador.getNombresEspias();// Obtener los nombres desde el controlador
        for (String nombre : nombresEspias) {
            comboBoxE1.addItem(nombre);
            comboBoxE2.addItem(nombre);
        }
    }
	
	@Override
	public void notificar() {
		// TODO Auto-generated method stub
		
	}
}

