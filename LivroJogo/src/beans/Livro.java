package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Livro implements Serializable {
	private String nome;
	private int posicao;
	private Heroi jogador;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public Heroi getJogador() {
		return jogador;
	}
	public void setJogador(Heroi jogador) {
		this.jogador = jogador;
	}
	public String toString() {
		return "Nome do livro:\t"+ nome+ "\nUltima posicao salva:\t"+
		posicao+ "\n\n"+ jogador.toString();
	}
	
}
