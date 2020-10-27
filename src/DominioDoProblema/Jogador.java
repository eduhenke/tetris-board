package DominioDoProblema;

public class Jogador {
	
	protected String nome;
	protected boolean seuTurno = false;
	protected boolean vencedor = false;
	
	public boolean informarTurno() {
		return seuTurno;
	}

	public void inverterTurno() {
		if (seuTurno) {
			seuTurno = false;
		} else {
			seuTurno = true;
		}
	}

	public String informarNome() {
		return nome;
	}

	public boolean informarVencedor() {
		return vencedor;
	}

	public void iniciar() {
		seuTurno = false;
		vencedor = false;
	}

	public void definirNome(String jogador) {
		nome = jogador;
	}

	public void definirComoPrimeiro() {
		seuTurno = true;
	}

	public void definirVencedor(boolean valor) {
		vencedor = valor;
	}

}
