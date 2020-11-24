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
	
	public boolean podeColocarPeca(Posicao posicao, Peca.Tipo tipo) {
		int i = posicao.i;
		int j = posicao.j;
		try {
			switch (tipo) {
				case QUADRADO:
					return (posicoes[i][j].peca == null && posicoes[i + 1][j].peca == null && posicoes[i][j + 1].peca == null && posicoes[i + 1][j + 1].peca == null);
				case L:
					return (posicoes[i][j].peca == null && posicoes[i + 1][j].peca == null && posicoes[i - 1][j].peca == null && posicoes[i + 1][j + 1].peca == null);
				case LINHA:
					return (posicoes[i][j].peca == null && posicoes[i + 1][j].peca == null && posicoes[i + 2][j].peca == null && posicoes[i + 3][j].peca == null);
				case T:
					return (posicoes[i][j].peca == null && posicoes[i - 1][j].peca == null && posicoes[i][j + 1].peca == null && posicoes[i][j - 1].peca == null);
			}
			return false;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	
	private boolean temEspacoLivre() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(
					podeColocarPeca(posicoes[i][j],Peca.Tipo.QUADRADO) ||
					podeColocarPeca(posicoes[i][j],Peca.Tipo.L) ||
					podeColocarPeca(posicoes[i][j],Peca.Tipo.LINHA) ||
					podeColocarPeca(posicoes[i][j],Peca.Tipo.T)
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
		int i = peca.ancora.i;
		int j = peca.ancora.j;
		switch (peca.tipo) {
		case QUADRADO:
			switch (peca.rotacao) {
				case DEG_0: posicoes[i][j].peca = peca; posicoes[i+1][j].peca = peca; posicoes[i][j+1].peca = peca; posicoes[i+1][j+1].peca = peca;
				case DEG_90: posicoes[i][j].peca = peca; posicoes[i+1][j].peca = peca; posicoes[i][j+1].peca = peca; posicoes[i+1][j+1].peca = peca;
				case DEG_180: posicoes[i][j].peca = peca; posicoes[i+1][j].peca = peca; posicoes[i][j+1].peca = peca; posicoes[i+1][j+1].peca = peca;
				case DEG_270: posicoes[i][j].peca = peca; posicoes[i+1][j].peca = peca; posicoes[i][j+1].peca = peca; posicoes[i+1][j+1].peca = peca;
			}
            break;
		case L:
        	posicoes[i][j].peca = peca;
        	posicoes[i+1][j].peca = peca;
        	posicoes[i-1][j].peca = peca;
        	posicoes[i+1][j+1].peca = peca;
			break;
		case LINHA:
        	posicoes[i][j].peca = peca;
        	posicoes[i+1][j].peca = peca;
        	posicoes[i+2][j].peca = peca;
        	posicoes[i+3][j].peca = peca;
			break;
		case T:
        	posicoes[i][j].peca = peca;
        	posicoes[i-1][j].peca = peca;
        	posicoes[i][j+1].peca = peca;
        	posicoes[i][j-1].peca = peca;
			break;
		}
	}

	public boolean ehTurnoJogadorLocal() {
		if (jogadorLocal == null) return false;
		return jogadorLocal.seuTurno;
	}

	public void trocarTurnoJogadores() {
		System.out.println("local: " + jogadorLocal.seuTurno + ", remoto: " + jogadorRemoto.seuTurno);
		jogadorLocal.inverterTurno();
		jogadorRemoto.inverterTurno();
	}

	public void colocarPeca(Peca novaPeca) {
		// já inserimos essa peça anteriormente, ignorar.
		if (!podeColocarPeca(posicoes[novaPeca.ancora.i][novaPeca.ancora.j], novaPeca.tipo))  return;

		efetuarColocacaoPeca(novaPeca);

		Lance lance = new Lance(novaPeca);
		atorJogador.enviarJogada(lance);

		if (jogoFinalizou()) {
			String vencedor = "";
			if (jogadorLocal.seuTurno) vencedor = jogadorLocal.nome;
			if (jogadorRemoto.seuTurno) vencedor = jogadorRemoto.nome;
			partidaEmAndamento = false;
			JOptionPane.showMessageDialog(null, "Fim de Jogo! Vencedor: "+vencedor);
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
		} else return false;
	}
}