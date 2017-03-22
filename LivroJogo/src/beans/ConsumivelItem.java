package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ConsumivelItem extends EquipItem implements Serializable{
	
	private int duracao, usado;
	
	
	public ConsumivelItem(){
		usado = 0;
	}
	public ConsumivelItem(Item i, int quantidade) {
		super(i, quantidade);
		usado = 0;
	}
	public ConsumivelItem(ConsumivelItem i) {
		super(i, 1);
		duracao = i.getDuracao();
		usado = i.getUsado();
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public int getUsado() {
		return usado;
	}
	public int getUsosRestantes() {
		return duracao - usado;
	}
	
	public void usar() {
		usado++;
	}
	public String toString() {
		return super.toString() + "\nusos restantes "+ getUsosRestantes();
	}
	public boolean equals(ConsumivelItem outro) {
		return super.equals(outro)&& outro.getUsado() == usado;
	}
}
