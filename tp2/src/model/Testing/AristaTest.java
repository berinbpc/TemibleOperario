package model.Testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Arista;

public class AristaTest {
	private Arista arista1;
	private Arista arista2;
	private Arista arista3;

	@Before
	public void setUp() {
		arista1 = new Arista(0, 1, 0.03);
		arista2 = new Arista(1, 2, 0.24);
		arista3 = new Arista(0, 3, 0.03);
	}

	@Test
	public void testCompareToProbabilidadMenor() {
		assertTrue(arista1.compareTo(arista2) < 0);
	}
	
	@Test
	public void testCompareToProbabilidadMayor() {
		assertTrue(arista2.compareTo(arista1) > 0);
	}
	
	@Test
	public void testCompareToProbabilidadIgual() {
		assertTrue(arista1.compareTo(arista3) == 0);
	}
}
