package br.com.udemy.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.udemy.cm.excecao.ExplosaoException;

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
	
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
			});
		
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirComVizinhos() {
		
		Campo campo11 = new Campo(1,1);
		
		Campo campo22 = new Campo(2,2);
		campo22.adcicionarVizinho(campo11);

		campo.adcicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,1);
		campo12.minar();
		
		Campo campo22 = new Campo(2,2);
		campo22.adcicionarVizinho(campo11);
		campo22.adcicionarVizinho(campo12);
		
		campo.adcicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	void objetivoAlcancadoDesvendado() {
		campo.abrir();
		
		assertTrue(campo.objetivoAlcancado());
		
	}
	
	@Test
	void objetivoAlcancadoProtegido() {
		campo.minar();
		campo.isMarcado();
		
		assertTrue(campo.objetivoAlcancado());
		
	}
	
	@Test
	void objetivoAlcancadoNãoDesvendadoNemProtegido() {
		
		assertFalse(campo.objetivoAlcancado());
	
	}
	
	@Test
	void minasNaVizinhanca() {
		
		Campo campo1 = new Campo(1, 2);
		campo1.minar();
		campo.adcicionarVizinho(campo1);
		
		Long quantidadesDeMinas = campo.minasNaVizinhaca();
		assertEquals(1, quantidadesDeMinas);
	}
	
	@Test
	void reiniciar() {
		campo.abrir();
		campo.alternarMarcacao();
		campo.reiniciar();
		
		assertFalse(campo.isAberto());
		assertFalse(campo.isMarcado());
		assertEquals(0, campo.minasNaVizinhaca());
		
	}
	
	@Test
	void toStringMarcado() {
		campo.isMarcado();
		assertEquals("x", campo.toString());
		
	}
	
	@Test
	void toStringAbertoEMinado() {
		campo.abrir();
		campo.minar();
		
		assertEquals("*", campo.toString());
	}
	
	@Test
	void toStringNumeroDeMinasAbertoENaoMinado() {
		
		Campo campo1 = new Campo(1, 2);
		
		campo1.minar();
		campo.adcicionarVizinho(campo1);
		campo.abrir();
		
		assertEquals("1", campo.toString());
		
	}
	
	@Test
	void toStringEspacoEmBranco() {
		campo.abrir();
		assertEquals(" ", campo.toString());
	}
	
	@Test
	void toStringFechado() {
		assertEquals("?", campo.toString());
	}
	
}
