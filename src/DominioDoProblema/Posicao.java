package DominioDoProblema;

import java.io.Serializable;

public class Posicao implements Serializable {
	public Peca peca;
	public int i;
	public int j;
	
	public Posicao(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
