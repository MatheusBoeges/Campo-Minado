package br.com.udemy.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.udemy.cm.excecao.ExplosaoException;

public class Campo {
	
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adcicionarVizinho(Campo candidatoVizinho) {
		boolean linhaDiferente = linha != candidatoVizinho.linha;
		boolean colunaDiferente = coluna != candidatoVizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - candidatoVizinho.linha);
		int deltaColuna = Math.abs(coluna - candidatoVizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(candidatoVizinho);
			return true;
		} else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(candidatoVizinho);
			return true;
		} else {
			return false;
		}
		
	}

	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	boolean abrir() {
		
		if(!aberto && !marcado) {
			aberto = true;
		
			if(minado) {
				throw new ExplosaoException();
			}
		
		if(vizinhacaSegura()) {
			vizinhos.forEach(v -> v.abrir());
		}
		
		return true;
	} else {
		return false;
	}
}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}
	
	void minar() {
		minado = true;
	}

	public boolean isMinado() {
		return minado;
	}
	
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	Long minasNaVizinhaca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if(marcado) {
			return "x";
		} else if(aberto && minado) { // campo aberto e minado.
			return "*";
		} else if(aberto && minasNaVizinhaca() > 0) { //  campo  estiver aberto e tiver minas na vizinhança, mostrar a quantidade de minas na vizinhaças. 
			return Long.toString(minasNaVizinhaca());
		} else if(aberto) { //  campo  estiver aberto e não tiver minas na vizinhança.
			return " ";
		} else { // campo ainda está fechado.
			return "?";
		}
	}
	
}
