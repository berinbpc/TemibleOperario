package model.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class Assert
{
	// Verifica que sean iguales como conjuntos
	public static void conjuntosIguales(int[] esperado, Set<Integer> obtenido) {
		assertEquals(esperado.length, obtenido.size());
		
		for(int i=0; i<esperado.length; ++i)
			assertTrue( obtenido.contains(esperado[i]) );
	}
	
	public static void matricesIguales(double[][] esperado, double[][] obtenido) {
		assertEquals(esperado.length, obtenido.length);
		assertEquals(esperado[0].length, obtenido[0].length);
		
		for (int f=0; f<esperado.length; f++) {
			for (int c=0; c<esperado.length; c++) {
				assertTrue(esperado[f][c] == obtenido[f][c]);
			}
		}
	}
}