package DominioDoProblema;

public class Jogador {
	
	protected String nome;

	Jogador(String nome) {
		this.nome = nome;
	}

	boolean seuTurno = false;
	boolean ehVencedor = false;

	public void iniciar() {
		seuTurno = false;
		ehVencedor = false;
	}

	public void inverterTurno() {
		this.seuTurno = !this.seuTurno;
	}
}
