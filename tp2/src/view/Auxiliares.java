package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controlador;

public class Auxiliares {

	 public static void mostrarError(String mensaje, JFrame ventana) {
	        JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	 }
	    
	 public static void mostrarMensaje(String mensaje, JFrame ventana) {
	        JOptionPane.showMessageDialog(ventana, mensaje, "Mensaje: ", JOptionPane.OK_CANCEL_OPTION);
	    	
	 }
	 
	 static void cerrarVentanaActual(JFrame ventana) {
		 ventana.dispose();
	 }
	 
	 static void pasarALaInterfazResultado(Controlador controlador) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new InterfazResultado(controlador); 
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	 
	 static void pasarALaInterfazVecinos(Controlador controlador) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new InterfazVecinos(controlador);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	    
	    
	    
}

