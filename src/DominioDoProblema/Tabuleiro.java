package DominioDoProblema;

import javax.swing.JOptionPane;
import InterfaceGrafica.AtorJogador;

public class Tabuleiro {
	public Posicao[][] botoesTabuleiro = new Posicao[8][8];
	public AtorJogador atorJogador;
	public boolean pecaColocada=false;
	protected Jogador jogadorLocal;
	protected Jogador jogadorRemoto;
	public boolean partidaEmAndamento = false;
	public boolean jogadaEmAndamento = false;
	
	public Tabuleiro() {
		super();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.botoesTabuleiro[i][j] = new Posicao(i, j);
			}	
		}
		atorJogador = new AtorJogador();
		
		iniciar();
	}
	
	public boolean podeColocarPeca(Posicao botao, Peca.Tipo tipo) {
		
		switch(tipo) {
		case QUADRADO:
			for(int i = 0; i<8; i++) {
				for(int j = 0; j<8; j++) {
					if(botoesTabuleiro[i][j] == botao) {
						if(botoesTabuleiro[i][j].peca == null && botoesTabuleiro[i+1][j].peca == null && botoesTabuleiro[i][j+1].peca == null && botoesTabuleiro[i+1][j+1].peca == null) {
							return true;
						}
						return false;
					}
				}
			}
		case L:
			for(int i = 0; i<8; i++) {
				for(int j = 0; j<8; j++) {
					if(botoesTabuleiro[i][j] == botao) {
						if(botoesTabuleiro[i][j].peca == null && botoesTabuleiro[i+1][j].peca == null && botoesTabuleiro[i-1][j].peca == null && botoesTabuleiro[i+1][j+1].peca == null) {
							return true;
						}
						return false;
					}
				}
			}
		case LINHA:
			for(int i = 0; i<8; i++) {
				for(int j = 0; j<8; j++) {
					if(botoesTabuleiro[i][j] == botao) {
						if(botoesTabuleiro[i][j].peca == null && botoesTabuleiro[i+1][j].peca == null && botoesTabuleiro[i+2][j].peca == null && botoesTabuleiro[i+3][j].peca == null) {
							return true;
						}
						return false;
					}
				}
			}
		case T:
			for(int i = 0; i<8; i++) {
				for(int j = 0; j<8; j++) {
					if(botoesTabuleiro[i][j] == botao) {
						if(botoesTabuleiro[i][j].peca == null && botoesTabuleiro[i-1][j].peca == null && botoesTabuleiro[i][j+1].peca == null && botoesTabuleiro[i][j-1].peca == null) {
							return true;
						}
						return false;
					}
				}
			}
		}
		return false;
			
	}
	
	public boolean jogoAcabou() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				try {
				if(
					podeColocarPeca(botoesTabuleiro[i][j],Peca.Tipo.QUADRADO) ||
					podeColocarPeca(botoesTabuleiro[i][j],Peca.Tipo.L) ||
					podeColocarPeca(botoesTabuleiro[i][j],Peca.Tipo.LINHA) ||
					podeColocarPeca(botoesTabuleiro[i][j],Peca.Tipo.T) 
				) {
					
					System.out.println("jogo nao acabou");
					return false;
				}
				}catch(Exception E) {
					
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Fim de Jogo!");
		return true;
	}
	
	private void inserirPeca(Peca peca) {
		int i = peca.ancora.i;
		int j = peca.ancora.j;
		switch (peca.tipo) {
		case QUADRADO:
            botoesTabuleiro[i][j].peca = peca;
            botoesTabuleiro[i+1][j].peca = peca;
            botoesTabuleiro[i][j+1].peca = peca;
            botoesTabuleiro[i+1][j+1].peca = peca;
		case L:
        	botoesTabuleiro[i][j].peca = peca;
        	botoesTabuleiro[i+1][j].peca = peca;
        	botoesTabuleiro[i-1][j].peca = peca;
        	botoesTabuleiro[i+1][j+1].peca = peca;
		case LINHA:
        	botoesTabuleiro[i][j].peca = peca;
        	botoesTabuleiro[i+1][j].peca = peca;
        	botoesTabuleiro[i+2][j].peca = peca;
        	botoesTabuleiro[i+3][j].peca = peca;
		case T:
        	botoesTabuleiro[i][j].peca = peca;
        	botoesTabuleiro[i-1][j].peca = peca;
        	botoesTabuleiro[i][j+1].peca = peca;
        	botoesTabuleiro[i][j-1].peca = peca;
		}
	}
	
	public void colocarPeca(Peca novaPeca) {
		if (podeColocarPeca(botoesTabuleiro[novaPeca.ancora.i][novaPeca.ancora.j], novaPeca.tipo)) {
			this.inserirPeca(novaPeca);

			Lance lance = new Lance();
			lance.peca = novaPeca;
           	atorJogador.enviarJogada(lance);
           	
            jogoAcabou();
		}
	}
	
	public void registrarJogadorLocal(String jogador) {
		jogadorLocal = new Jogador();
		jogadorLocal.definirNome(jogador);
		jogadorLocal.iniciar();
	}
	private void iniciar() {
		partidaEmAndamento = false;
		jogadaEmAndamento = false;
	}
	
	
	public void iniciarNovaPartida(Integer ordem, String adversario) {
		jogadorLocal.iniciar();
		jogadorRemoto = new Jogador();
		jogadorRemoto.definirNome(adversario);
		if (ordem.equals(1)) jogadorLocal.definirComoPrimeiro();
			else jogadorRemoto.definirComoPrimeiro();
		partidaEmAndamento = true;
	}
	
	public void definirPartidaEmAndamento(boolean valor) {	
		partidaEmAndamento = valor;
	}
	public boolean encerrarPartida() {	
		if (partidaEmAndamento) {
			this.encerrarPartidaLocalmente();
			return true;
		} else return false;
	}
	 public void encerrarPartidaLocalmente() {
			jogadorLocal.iniciar();
			jogadorRemoto = new Jogador();
			jogadorRemoto.iniciar();
		}
}