package InterfaceGrafica;

import javax.swing.JOptionPane;

import DominioDoProblema.Lance;
import DominioDoProblema.Tabuleiro;
import Rede.AtorNetgames;

public class AtorJogador {
	
	public AtorNetgames ngServer;
	protected InterfaceJogo gui;
	protected Tabuleiro movimentacao;
	
	private static AtorJogador instancia;

	
	public static synchronized AtorJogador getInstance () {
		return instancia;
	}
	
	public static synchronized AtorJogador getInstance (InterfaceJogo InterfaceJogo) {
		if (instancia == null) {
			instancia = new AtorJogador(InterfaceJogo);
		}
		return instancia;
	}
	
	public AtorJogador(InterfaceJogo InterfaceJogo) {
		super();
		iniciar(InterfaceJogo);
	}
	public AtorJogador() {
		ngServer = new AtorNetgames();
	}
	private void iniciar(InterfaceJogo InterfaceJogo) {
		gui = InterfaceJogo;
		ngServer = new AtorNetgames();
		movimentacao = new Tabuleiro();		
		ngServer.definirInterfaceJogador(this);
	}

	public void conectar() {
		boolean conectado = ngServer.informarConectado();
		if(!conectado) {
			String jogador =JOptionPane.showInputDialog("Qual o seu nome?");
			gui.lblNomejogador.setText(jogador);
			String idServidor = ("localhost");
			idServidor = JOptionPane.showInputDialog(null, "Insira o endereço do servidor", idServidor);
			String notificacao = ngServer.conectar(idServidor, jogador);
			movimentacao.registrarJogadorLocal(jogador);
			JOptionPane.showMessageDialog(null, notificacao);
		} else {
			JOptionPane.showMessageDialog(null, "Voce ja esta conectado!");
		}
	}
	
	public boolean desconectar() {
		boolean conectado = ngServer.informarConectado();
		boolean atualizarInterface = false;
		if(conectado) {
			atualizarInterface = movimentacao.encerrarPartida();
			if (atualizarInterface) ngServer.encerrarPartida();
			ngServer.desconectar();
			gui.notificar("Voce esta desconectado");
			gui.minimizaCampo();
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;
	}
	
	public boolean iniciarPartida() {
		
		boolean conectado = ngServer.informarConectado();
		System.out.println(conectado);
		boolean atualizarInterface = false;
		if(conectado) {	
			atualizarInterface = movimentacao.encerrarPartida();
			System.out.println("atualizarInterface(InterfaceJogador) : " + atualizarInterface); //////////////////////////teste

			if (atualizarInterface) ngServer.encerrarPartida();
			ngServer.iniciarPartida();
		} else {
			gui.notificar("Voce nao esta conectado");
		}
		return atualizarInterface;
	}
	public void iniciarNovaPartida(Integer ordem, String adversario) {
		movimentacao.iniciarNovaPartida(ordem, adversario);

	}
	public void receberJogada(Lance lance) {
		 gui.receberJogada(lance);
	}
	public void encerrarPartida() {
		movimentacao.encerrarPartida();
	}
	
	public void enviarJogada(Lance lance) {
		ngServer.enviarJogada(lance);
	}
}
