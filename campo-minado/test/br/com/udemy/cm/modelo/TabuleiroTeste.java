package br.com.udemy.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TabuleiroTeste {
	
	 @Test
	    void criarTabuleiro3x3Com1Mina() {
	        Tabuleiro tabuleiro = new Tabuleiro(3, 3, 1);

	        assertEquals(3, tabuleiro.getLinhas());
	        assertEquals(3, tabuleiro.getColunas());
	        assertEquals(1, tabuleiro.getMinas());
	        assertEquals(9, tabuleiro.getCampos().size());
	    }

	    @Test
	    void associarVizinhosCorretamente() {
	        Tabuleiro tabuleiro = new Tabuleiro(2, 2, 0);
	        Campo c1 = tabuleiro.getCampos().get(0);

	        assertEquals(3, c1.getVizinhos().size()); // Cada canto tem 3 vizinhos
	    }

	    @Test
	    void sortearMinasCorretas() {
	        Tabuleiro tabuleiro = new Tabuleiro(4, 4, 4);

	        long minasArmadas = tabuleiro.getCampos().stream().filter(c -> c.isMinado()).count();
	        assertEquals(4, minasArmadas);
	    }

	    @Test
	    void tabuleiroVazio() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Tabuleiro(0, 0, 0);
	        });
	    }

	    @Test
	    void maisMinasQueCampos() {
	        assertThrows(IllegalArgumentException.class, () -> {
	            new Tabuleiro(2, 2, 5);
	        });
	    }
	
}
