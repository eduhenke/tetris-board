package DominioDoProblema;

import java.awt.Color;

public class Peca {
	public Tipo tipo;
	public Posicao ancora;
	public Color cor;
	
	public static enum Tipo {
		QUADRADO, L, LINHA, T;

		public int codigo;
		static {
			QUADRADO.codigo = 0;
			L.codigo = 1;
			LINHA.codigo = 2;
			T.codigo = 3;
		}
		public Color cor;
		static {
			QUADRADO.cor = Color.RED;
			L.cor = Color.BLUE;
			LINHA.cor = Color.ORANGE;
			T.cor = Color.MAGENTA;
		}
	}
	
}
