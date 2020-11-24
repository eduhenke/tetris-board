package DominioDoProblema;

import InterfaceGrafica.AtorJogador;

import javax.swing.JOptionPane;

public class Tabuleiro {
	public Posicao[][] posicoes = new Posicao[8][8];
	public AtorJogador atorJogador;
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	public boolean partidaEmAndamento = false;

	public Tabuleiro(AtorJogador atorJogador) {
		super();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.posicoes[i][j] = new Posicao(i, j);
			}
		}
		this.atorJogador = atorJogador;

		iniciar();
	}

	public boolean podeColocarPeca(Posicao posicao, Peca.Tipo tipo, Peca.Rotacao rotacao) {
		try {
			int[][] posicoesRelativas = tipo.pegaPosicoesRelativas(rotacao);
			boolean cabe = true;
			for (int[] pos : posicoesRelativas) {
				int i = pos[0] + posicao.i;
				int j = pos[1] + posicao.j;
				if (posicoes[i][j].peca != null)
					cabe = false;
			}
			return cabe;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean temEspacoLivre() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(
					podeColocarPeca(posicoes[i][j], Peca.Tipo.QUADRADO, Peca.Rotacao.DEG_0) || podeColocarPeca(posicoes[i][j], Peca.Tipo.QUADRADO, Peca.Rotacao.DEG_90) || podeColocarPeca(posicoes[i][j], Peca.Tipo.QUADRADO, Peca.Rotacao.DEG_180) || podeColocarPeca(posicoes[i][j], Peca.Tipo.QUADRADO, Peca.Rotacao.DEG_270) ||
					podeColocarPeca(posicoes[i][j], Peca.Tipo.L, Peca.Rotacao.DEG_0) || podeColocarPeca(posicoes[i][j], Peca.Tipo.L, Peca.Rotacao.DEG_90) || podeColocarPeca(posicoes[i][j], Peca.Tipo.L, Peca.Rotacao.DEG_180) || podeColocarPeca(posicoes[i][j], Peca.Tipo.L, Peca.Rotacao.DEG_270) ||
					podeColocarPeca(posicoes[i][j], Peca.Tipo.T, Peca.Rotacao.DEG_0) || podeColocarPeca(posicoes[i][j], Peca.Tipo.T, Peca.Rotacao.DEG_90) || podeColocarPeca(posicoes[i][j], Peca.Tipo.T, Peca.Rotacao.DEG_180) || podeColocarPeca(posicoes[i][j], Peca.Tipo.T, Peca.Rotacao.DEG_270) ||
					podeColocarPeca(posicoes[i][j], Peca.Tipo.LINHA, Peca.Rotacao.DEG_0) || podeColocarPeca(posicoes[i][j], Peca.Tipo.LINHA, Peca.Rotacao.DEG_90) || podeColocarPeca(posicoes[i][j], Peca.Tipo.LINHA, Peca.Rotacao.DEG_180) || podeColocarPeca(posicoes[i][j], Peca.Tipo.LINHA, Peca.Rotacao.DEG_270)
				) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean jogoFinalizou() {
		return !temEspacoLivre();
	}

	public void efetuarColocacaoPeca(Peca peca) {
		int[][] posicoesRelativas = peca.tipo.pegaPosicoesRelativas(peca.rotacao);
		for (int[] pos : posicoesRelativas) {
			int i = pos[0] + peca.ancora.i;
			int j = pos[1] + peca.ancora.j;
			posicoes[i][j].peca = peca;
		}
	}

	public boolean ehTurnoJogadorLocal() {
		if (jogadorLocal == null)
			return false;
		return jogadorLocal.seuTurno;
	}

	public void trocarTurnoJogadores() {
		System.out.println("local: " + jogadorLocal.seuTurno + ", remoto: " + jogadorRemoto.seuTurno);
		jogadorLocal.inverterTurno();
		jogadorRemoto.inverterTurno();
	}

	public void colocarPeca(Peca novaPeca) {
		// já inserimos essa peça anteriormente, ignorar.
		if (!podeColocarPeca(posicoes[novaPeca.ancora.i][novaPeca.ancora.j], novaPeca.tipo, novaPeca.rotacao))
			return;

		efetuarColocacaoPeca(novaPeca);

		Lance lance = new Lance(novaPeca);
		atorJogador.enviarJogada(lance);

		if (jogoFinalizou()) {
			String vencedor = "";
			if (jogadorLocal.seuTurno)
				vencedor = jogadorLocal.nome;
			if (jogadorRemoto.seuTurno)
				vencedor = jogadorRemoto.nome;
			partidaEmAndamento = false;
			JOptionPane.showMessageDialog(null, "Fim de Jogo! Vencedor: " + vencedor);
		}

		trocarTurnoJogadores();
	}

	public void registrarJogadorLocal(String nome) {
		System.out.println(nome);
		jogadorLocal = new Jogador(nome);
		jogadorLocal.iniciar();
	}

	private void iniciar() {
		partidaEmAndamento = false;
	}

	public void iniciarNovaPartida(Integer ordem, String nomeAdversario) {
		jogadorLocal.iniciar();
		jogadorRemoto = new Jogador(nomeAdversario);
		System.out.println("iniciando nova partida:" + ordem.toString());
		if (ordem.equals(1)) {
			jogadorLocal.seuTurno = true;
			jogadorRemoto.seuTurno = false;
		} else {
			jogadorRemoto.seuTurno = true;
			jogadorLocal.seuTurno = false;
		}

		partidaEmAndamento = true;
	}

	public void definirPartidaEmAndamento() {
		partidaEmAndamento = true;
	}

	public boolean encerrarPartida() {
		if (partidaEmAndamento) {
			return true;
		} else
			return false;
	}
}