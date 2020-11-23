package InterfaceGrafica;

import javax.swing.JOptionPane;

import DominioDoProblema.Lance;
import DominioDoProblema.Tabuleiro;
import Rede.AtorNetgames;

public class AtorJogador {
	public AtorNetgames ngServer;
	protected InterfaceJogo gui;
	protected Tabuleiro tabuleiro;

	public AtorJogador(InterfaceJogo gui) {
		this.gui = gui;
		ngServer = new AtorNetgames();
		ngServer.definirInterfaceJogador(this);
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public void conectar() {
		boolean conectado = ngServer.informarConectado();
		if(!conectado) {
			String jogador =JOptionPane.showInputDialog("Qual o seu nome?");
			gui.lblNomejogador.setText(jogador);
			String idServidor = ("localhost");
			idServidor = JOptionPane.showInputDialog(null, "Insira o endereço do servidor", idServidor);
			String notificacao = ngServer.conectar(idServidor, jogador);
			tabuleiro.registrarJogadorLocal(jogador);
			JOptionPane.showMessageDialog(null, notificacao);
		} else {
			JOptionPane.showMessageDialog(null, "Voce ja esta conectado!");
		}
	}
	
	public boolean desconectar() {
		boolean conectado = ngServer.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			atualizarInterface = tabuleiro.encerrarPartida();
			if (atualizarInterface) ngServer.encerrarPartida();
			ngServer.desconectar();
			gui.notificar("Voce esta desconectado");
			gui.minimizaCampo();
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;
	}
	
	public void iniciarPartida() {
		boolean conectado = ngServer.informarConectado();
		if(conectado) {
			ngServer.iniciarPartida();
		} else {
			gui.notificar("Você não está conectado");
		}
	}
	public void iniciarNovaPartida(Integer ordem, String adversario) {
		tabuleiro.iniciarNovaPartida(ordem, adversario);
	}
	public void receberJogada(Lance lance) {
		 gui.receberJogada(lance);
	}
	public void encerrarPartida() {
		tabuleiro.encerrarPartida();
	}
	
	public void enviarJogada(Lance lance) {
		ngServer.enviarJogada(lance);
	}
}
