package br.com.udemy.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoHorizontal1() {
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adcicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoHorizontal2() {
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adcicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoVertical1() {
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adcicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoVertical2() {
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adcicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDiagonal() {
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adcicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adcicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
}
