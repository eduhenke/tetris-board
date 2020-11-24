package DominioDoProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Lance implements Jogada {
	private static final long serialVersionUID = 1L;
	public Peca peca;
	Lance(Peca peca) {
		this.peca = peca;
	}
	
}
