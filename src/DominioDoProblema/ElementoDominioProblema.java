package DominioDoProblema;

public class ElementoDominioProblema {
	
	protected boolean conectado = false;
	protected boolean partidaAndamento = false;

	public ElementoDominioProblema() {
		// TODO Auto-generated constructor stub
	}

	public void definirConectado(boolean valor) {
		conectado = valor;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
	
	public void definirPartidaAndamento(boolean valor) {
		partidaAndamento = valor;
	}
	
	public boolean informarPartidaAndamento() {
		return partidaAndamento;
	}
	
	public boolean permitidoConectar() {
		return !conectado;
	}
	
	public boolean permitidoDesconectar() {
		return conectado;
	}

	public boolean permitidoIniciarPartida() {
		return !partidaAndamento;
	}


}
