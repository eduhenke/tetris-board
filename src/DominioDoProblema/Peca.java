package DominioDoProblema;

import java.awt.Color;
import java.io.Serializable;

public class Peca implements Serializable {
	public Tipo tipo;
	public Posicao ancora;
	public Color cor;
	public Rotacao rotacao = Rotacao.DEG_0;

	public Peca(Posicao ancora, Tipo tipo, Rotacao rotacao) {
		this.ancora = ancora;
		this.tipo = tipo;
		this.cor = tipo.cor;
		this.rotacao = rotacao;
	}

	public enum Tipo implements Serializable {
		QUADRADO, L, LINHA, T;

		public Color cor;
		static {
			QUADRADO.cor = Color.RED;
			L.cor = Color.BLUE;
			LINHA.cor = Color.ORANGE;
			T.cor = Color.MAGENTA;
		}

		public int[][] pegaPosicoesRelativas(Rotacao rotacao) {
			switch (this) {
				case QUADRADO:
					switch (rotacao) {
						case DEG_0: {
							int[][] posicoes = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } };
							return posicoes;
						}
						case DEG_90: {
							int[][] posicoes = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { -1, 1 } };
							return posicoes;
						}
						case DEG_180: {
							int[][] posicoes = { { 0, 0 }, { -1, 0 }, { -1, -1 }, { 0, -1 } };
							return posicoes;
						}
						case DEG_270: {
							int[][] posicoes = { { 0, 0 }, { 1, 0 }, { 0, -1 }, { 1, -1 } };
							return posicoes;
						}
					}
					break;
				case L:
					switch (rotacao) {
						case DEG_0: {
							int[][] posicoes = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 1, 1 } };
							return posicoes;
						}
						case DEG_90: {
							int[][] posicoes = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { 1, -1 } };
							return posicoes;
						}
						case DEG_180: {
							int[][] posicoes = { { 0, 0 }, { 1, 0 }, { -1, 0 }, { -1, -1 } };
							return posicoes;
						}
						case DEG_270: {
							int[][] posicoes = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 1 } };
							return posicoes;
						}
					}
					break;
				case LINHA:
					switch (rotacao) {
						case DEG_0: {
							int[][] posicoes = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } };
							return posicoes;
						}
						case DEG_90: {
							int[][] posicoes = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } };
							return posicoes;
						}
						case DEG_180: {
							int[][] posicoes = { { 0, 0 }, { -1, 0 }, { -2, 0 }, { -3, 0 } };
							return posicoes;
						}
						case DEG_270: {
							int[][] posicoes = { { 0, 0 }, { 0, -1 }, { 0, -2 }, { 0, -3 } };
							return posicoes;
						}
					}
					break;
				case T:
					switch (rotacao) {
						case DEG_0: {
							int[][] posicoes = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
							return posicoes;
						}
						case DEG_90: {
							int[][] posicoes = { { 0, 0 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
							return posicoes;
						}
						case DEG_180: {
							int[][] posicoes = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
							return posicoes;
						}
						case DEG_270: {
							int[][] posicoes = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
							return posicoes;
						}
					}
					break;
			}
			int[][] posicoes = {};
			return posicoes;
		}
	}

	public enum Rotacao implements Serializable {
		DEG_0, DEG_90, DEG_180, DEG_270;

		public Rotacao girar() {
			if (this == DEG_0)
				return DEG_90;
			if (this == DEG_90)
				return DEG_180;
			if (this == DEG_180)
				return DEG_270;
			if (this == DEG_270)
				return DEG_0;
			return DEG_90;
		}
	}
}
