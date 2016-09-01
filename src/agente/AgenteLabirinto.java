package agente;

import ambiente.Labirinto;
import geral.PosicaoXY;

public class AgenteLabirinto {

	// Movimentar para 4 direções (Cima, baixo, esquerda, direita)
	// Referencia do Labiririnto 
	
	private Labirinto labirinto;
	private MovimentosAgenteLabirinto movimento;
	
	private PosicaoXY posXY;
	
	private int pilhaMovimentos;

	public AgenteLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		labirinto.setAgente(this);
		this.posXY = new PosicaoXY();
		this.movimento = MovimentosAgenteLabirinto.CIMA;
	}
	
	public void movimentar() {
		if (this.pilhaMovimentos >= 4) {
			return;
		}
		PosicaoXY proximoMovimento = retornarMovimento();
		String valor = this.labirinto.retornarValorPosicaoLabirinto(proximoMovimento);
		
		if (valor.equals("L") || valor.equals("*A*")) {
			proximoMovimento();
			aumentarPilha();
			movimentar();
		} else {
			this.labirinto.limpar();
			this.posXY = proximoMovimento;
		}
	}
	
	private void aumentarPilha() {
		this.pilhaMovimentos++;
	}

	private void proximoMovimento() {
		switch(this.movimento) {
			case CIMA:
				this.movimento = MovimentosAgenteLabirinto.BAIXO;
				break;
			case BAIXO:
				this.movimento = MovimentosAgenteLabirinto.ESQUERDA;
				break;
			case ESQUERDA:
				this.movimento = MovimentosAgenteLabirinto.DIREITA;
				break;
			case DIREITA:
				this.movimento = MovimentosAgenteLabirinto.CIMA;
				break;
		}
	}

	public PosicaoXY retornarMovimento() {
		int retornoPosX = this.posXY.getPosX();
		int retornoPosY = this.posXY.getPosY();
		switch(movimento) {
			case CIMA:
				if (retornoPosX > 0) {
					retornoPosX -= 1;
				}
				break;
			case BAIXO:
				if (retornoPosX < this.labirinto.getTamanhoLabirinto() - 1) {
					retornoPosX += 1;
				}
				break;
			case ESQUERDA:
				if (retornoPosY > 0) {
					retornoPosY -= 1;
				}
				break;
			case DIREITA:
				if (retornoPosY < this.labirinto.getTamanhoLabirinto() - 1) {
					retornoPosY += 1;
				}
				break;
		}
		return new PosicaoXY(retornoPosX, retornoPosY);
	}

	public PosicaoXY getPosicao() {
		return this.posXY;
	}

	public boolean isAindaLimpando() {
		return pilhaMovimentos < 4;
	}

	public void zerarPilha() {
		this.pilhaMovimentos = 0;
	}

	public void setPosicao(PosicaoXY posicaoXY) {
		this.posXY = posicaoXY;
		
	}
	
}
