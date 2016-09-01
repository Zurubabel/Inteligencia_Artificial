package agente;

import ambiente.Labirinto;
import geral.PosicaoXY;

public class AgenteLabirinto {

	// Movimentar para 4 direções (Cima, baixo, esquerda, direita)
	// Referencia do Labiririnto 
	
	private Labirinto labirinto;
	private MovimentosAgenteLabirinto movimento;
	
	private PosicaoXY posXY;

	public AgenteLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		labirinto.setAgente(this);
		this.posXY = new PosicaoXY();
		this.movimento = MovimentosAgenteLabirinto.CIMA;
	}
	
	public void movimentar() {
		PosicaoXY proximoMovimento = retornarMovimento();
		
		String valor = this.labirinto.retornarValorPosicaoLabirinto(proximoMovimento);
		
		if (valor.equals("L") || valor.equals("*A*")) {
			
		} else {
			this.labirinto.limpar();
			this.posXY = proximoMovimento;
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
	
}
