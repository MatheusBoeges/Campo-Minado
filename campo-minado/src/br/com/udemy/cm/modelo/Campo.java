package br.com.udemy.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	
	private final int Linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.Linha = linha;
		this.coluna = coluna;
	}

}
