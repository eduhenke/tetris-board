package DominioDoProblema;

import java.awt.Color;
import java.io.Serializable;

public class Peca implements Serializable {
	public Tipo tipo;
	public Posicao ancora;
	public Color cor;
	
	public static enum Tipo implements Serializable {
		QUADRADO, L, LINHA, T;

		public Color cor;
		static {
			QUADRADO.cor = Color.RED;
			L.cor = Color.BLUE;
			LINHA.cor = Color.ORANGE;
			T.cor = Color.MAGENTA;
		}
	}
	
}
