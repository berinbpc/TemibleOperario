package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.Controlador;
import model.Observer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
public class InterfazCargarDatosManualmente implements Observer {

	private JFrame ventanaPrincipal;
	private JTextField textCantEspias;
	private Controlador controlador;
	private ArrayList<JTextField> listaCamposNombres;
	private JPanel panelCantEspias;
	private JPanel panelNombresEspias;
	private JScrollPane scrollPaneNombresEspias;
	private JPanel panelInputEspias;
	private JLabel labelCantEspias;
	private JButton btnCantEspias;
	private JButton btnAgregarEspias;

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
	public InterfazCargarDatosManualmente() {
		
		// Para que el diseño de la ventana se adapte al sistema del usuario
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            controlador = new Controlador(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaCamposNombres = new ArrayList<>();
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicializarVentanaPrincipal();
		
		generarPanelCantEspias();
		generarPanelNombresEspias();
		generarPaneInputEspias();
		generarScrollNombresEspias();
		generarBotonAgregarEspias();
		
		agregarEspiasEnLista(); 
	}
	
	
	private void inicializarVentanaPrincipal() {
		ventanaPrincipal = new JFrame();
		ventanaPrincipal.setTitle("Carga de datos manualmente");
		ventanaPrincipal.setBounds(100, 100,950,500);
		ventanaPrincipal.setVisible(true);
		ventanaPrincipal.setLocationRelativeTo(null);	// Pone la ventana en el centro de la pantalla del usuario
		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaPrincipal.getContentPane().setLayout(null);
	    }	
	

	private JPanel generarPanelCantEspias() {
		panelCantEspias = new JPanel();
		panelCantEspias.setBounds(10, 17, 790, 26);
		ventanaPrincipal.getContentPane().add(panelCantEspias);
		panelCantEspias.setLayout(null);
		generarLabelCantEspias();
		generarTextCantEspias();
		generarBotonEstablecerCantEspias();
		return panelCantEspias;	   
	}
	
	

	
	private void generarPanelNombresEspias() {
		panelNombresEspias = new JPanel();
		panelNombresEspias.setBounds(20, 41, 755, 393);
		ventanaPrincipal.getContentPane().add(panelNombresEspias);
		panelNombresEspias.setLayout(new BorderLayout(0, 0));	
		
		
	}
	
	private void generarPaneInputEspias() {
	    panelInputEspias = new JPanel();
	    panelInputEspias.setLayout(new BoxLayout(panelInputEspias, BoxLayout.Y_AXIS));
	}
	
	private void generarScrollNombresEspias() {
		scrollPaneNombresEspias = new JScrollPane(panelInputEspias);
	    scrollPaneNombresEspias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Activa el scroll si es necesario
	    scrollPaneNombresEspias.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panelNombresEspias.add(scrollPaneNombresEspias, BorderLayout.CENTER);
	}
	
	private void generarBotonAgregarEspias() {
		btnAgregarEspias = new JButton("Agregar Espías"); 
		btnAgregarEspias.setEnabled(false);
		panelNombresEspias.add(btnAgregarEspias, BorderLayout.SOUTH); // Añade el botón al panel
	}

	private boolean hayNombresRepetidos() {
	    Set<String> nombresUnicos = new HashSet<>();
	    for (JTextField campoNombre : listaCamposNombres) {
	        String nombreEspia = campoNombre.getText().trim();
	        if (!nombresUnicos.add(nombreEspia)) {
	            Auxiliares.mostrarError("El nombre '" + nombreEspia + "' está repetido en uno de los campos.", ventanaPrincipal);
	            return true; //hay repetidos
	        }
	    }
	    return false;	//no hay repetidos
	}
	
	private void agregarEspiasEnLista() {
        btnAgregarEspias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (verificarCantEspias()) {
                    Auxiliares.mostrarMensaje("Se agregaron todos los espías", ventanaPrincipal);
                    btnAgregarEspias.setEnabled(false);
                }
            	if (hayNombresRepetidos()) {
            		return;// Si hay repetidos, no continua.
                }
            	if (! verificarCamposTextoEspias()) {
            		return; 
            	}
                 for (JTextField campoNombre : listaCamposNombres) {
                     String nombreEspia = campoNombre.getText().trim();
                     try {
                         controlador.agregarEspia(nombreEspia);
                     }  catch (IllegalArgumentException ex) {
                         Auxiliares.mostrarError("El espía ya está registrado: " + nombreEspia, ventanaPrincipal); 
                         return; // Detenemos el proceso si hay un error
                 }
                } 
                 	Auxiliares.cerrarVentanaActual(ventanaPrincipal);
					Auxiliares.pasarALaInterfazVecinos(controlador);
             }

			
         });
     }
	
	private boolean verificarCantEspias() {
		return controlador.cantidadEspiasAgregados() == listaCamposNombres.size();
	}
	
	private boolean verificarCamposTextoEspias() {
		if (listaCamposNombres == null || listaCamposNombres.isEmpty()) {
            Auxiliares.mostrarError("Primero debe establecer la cantidad de espías.", ventanaPrincipal);
            return false;
        }
   	
   	 	for (JTextField campoNombre : listaCamposNombres) {
            String nombreEspia = campoNombre.getText().trim();
            if (nombreEspia.isEmpty()) {
                Auxiliares.mostrarError("El nombre del espía no puede estar vacío.", ventanaPrincipal);
                return false;
            }
   	 	}
   	 	return true;
		
	}
	

	// Métodos Auxiliares de generarPanelCantEspias
	
	private void generarLabelCantEspias() {
		labelCantEspias = new JLabel("Cantidad de espías");
		labelCantEspias.setBounds(10, 6, 120, 17);
		panelCantEspias.add(labelCantEspias);
	}
	
	private void generarTextCantEspias() { 
		textCantEspias = new JTextField();
		textCantEspias.setBounds(140, 4, 86, 20);
		textCantEspias.setColumns(10);
		panelCantEspias.add(textCantEspias);
	}
	
	private void generarBotonEstablecerCantEspias() {
		btnCantEspias = new JButton("Establecer cantidad de espias");
		btnCantEspias.setBounds(265, 3, 491, 23);
		panelCantEspias.add(btnCantEspias);
		generarVerticesGrafo(btnCantEspias); 
		
	}

	private void generarVerticesGrafo(JButton btnCantEspias) {
	    btnCantEspias.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String textoCantidad = textCantEspias.getText().trim();
	            if (textoCantidad.isEmpty()) {
	                Auxiliares.mostrarError("Debe ingresar la cantidad de espías.", ventanaPrincipal);
	                return;
	            }
	            try {
	                int cantidadEspias = Integer.parseInt(textoCantidad);
	                if (cantidadEspias <= 1) {
	                    Auxiliares.mostrarError("La cantidad de espías debe ser mayor a 1.", ventanaPrincipal);
	                    return;
	                }
	                
	                controlador.setGrafo(cantidadEspias);
	                if(cantidadEspias >1) {
	                	btnCantEspias.setEnabled(false);
	                	btnAgregarEspias.setEnabled(true);
	                } 
	                
	            } catch (NumberFormatException ex) {
	                Auxiliares.mostrarError("Debe ingresar un número válido.", ventanaPrincipal);
	            }
	        }
	    });
	}
	

	@Override
	public void notificar() {
		panelInputEspias.removeAll(); // Limpia los campos anteriores
		listaCamposNombres = new ArrayList<>();
		listaCamposNombres.clear();

		int cantidadEspias = Integer.parseInt(textCantEspias.getText());
		for (int i = 0; i < cantidadEspias; i++) {
			JLabel lbl = new JLabel("Nombre del Espía " + (i + 1) + ": ");
	        JTextField txt = new JTextField(cantidadEspias);
	        panelInputEspias.add(lbl);
	        panelInputEspias.add(txt);
	        listaCamposNombres.add(txt);
	    }

	    panelInputEspias.revalidate(); // Revalidar el panel
	    panelInputEspias.repaint(); // Repintar el panel
	}
	    
	   
}