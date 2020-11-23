package Rede;

import DominioDoProblema.Lance;
import InterfaceGrafica.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.*;

public class AtorNetgames implements OuvidorProxy {
	
	private static final long serialVersionUID = 1L;
	protected AtorJogador atorJogador;
	protected boolean conectado = false;
	protected Proxy proxy;
	
	public AtorNetgames() {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);	
	}
	public void definirInterfaceJogador(AtorJogador ator) {
		atorJogador = ator;
	}
	public String conectar(String servidor, String nome) {
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Voce ja esta conectado";
		} catch (NaoPossivelConectarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Nao foi possivel conectar";
		} catch (ArquivoMultiplayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Voce esqueceu o arquivo de propriedades";
		}
		this.definirConectado(true);
		return "Sucesso: conectado a Netgames Server";
		
	}

	public void desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.definirConectado(false);
	}

	public void iniciarPartida() {
		System.out.println("iniciarPartida");
		try {
			proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		System.out.println("iniciarNovaPartida");
		int indiceAdversario = 1;
		if (posicao.equals(1)) indiceAdversario = 2;
		String adversario = proxy.obterNomeAdversario(indiceAdversario);
		atorJogador.iniciarNovaPartida(posicao, adversario);
	}

	@Override
	public void finalizarPartidaComErro(String message) {
		// TODO Auto-generated method stub
		atorJogador.encerrarPartida();
		
	}
	public void enviarJogada(Lance lance) {
		try {
			proxy.enviaJogada(lance);
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receberJogada(Jogada jogada) {
		// TODO Auto-generated method stub
		atorJogador.receberJogada((Lance) jogada);
		
	}
	public boolean informarConectado() {
		return conectado;
	}
	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub
		
	}
	public void definirConectado(boolean valor) {
		conectado = valor;
	}
	public void encerrarPartida() {
		try {
			proxy.finalizarPartida();
		} catch (NaoConectadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NaoJogandoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
