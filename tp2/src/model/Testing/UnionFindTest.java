package model.Testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.UnionFind;

public class UnionFindTest {

    private UnionFind uf;

    @Before
    public void setUp() {
        uf = new UnionFind(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fueraDeRangoTest() {
        UnionFind ufInvalido = new UnionFind(2);
        ufInvalido.union(0, 2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void conjuntoVacioTest() {
        UnionFind uf = new UnionFind(0);
        uf.root(0);
    }
    
    @Test
    public void cadaElementoEsSuPropiaRaizTest() {
        for (int i = 0; i < 5; i++) {
            assertEquals(i, uf.root(i));
        }
    }
    
    @Test
    public void findSinConectarTest() {        
        assertFalse(uf.find(0, 1));
        assertFalse(uf.find(2, 3));
    }
    
    @Test
    public void findConConexionesTest() {
        uf.union(0, 1);
        uf.union(2, 3);
        
        assertTrue(uf.find(0, 1));
        assertTrue(uf.find(2, 3));
    }
    
    @Test
    public void unionIndividualTest() {
        uf.union(0, 1);
        uf.union(2, 3);

        assertTrue(uf.find(0, 1));
        assertTrue(uf.find(2, 3));
        assertFalse(uf.find(0, 2));
    }
    
    @Test
    public void unionConGruposTest() {
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3);					//union de los grupos 
        
        assertTrue(uf.find(0, 3)); 
        assertTrue(uf.find(1, 2));
    }
}