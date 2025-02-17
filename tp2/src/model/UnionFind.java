package model;

public class UnionFind {
    private int[] A;
    
    public UnionFind(int n) {
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = i; 																		// Cada vértice es su propia raíz al principio 
        }
    }

    // Método para encontrar la raíz de un vértice
   public int root(int i) {
	    verificarIndice(i);
        while (A[i] != i) {
            i = A[i];
        }
        return i;
    }

    // Método para determinar si dos vértices están en la misma componente conexa
   	public boolean find(int i, int j) {
        return root(i) == root(j); 
    }

    // Método para hacer que la raız de una de ellas apunte a la raız de la otra
    public void union(int i, int j) {
        int ri = root(i);
        int rj = root(j); 
        if (ri != rj) { 
            A[ri] = rj;
        }
    }
    
    private void verificarIndice(int i) {
		if (i < 0 || i >= A.length) {
		       throw new IllegalArgumentException("El índice " + i + " está fuera de rango.");
		   }
	}
}