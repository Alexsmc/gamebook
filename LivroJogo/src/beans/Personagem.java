package beans;

import java.io.Serializable;

@SuppressWarnings("serial")
abstract class Personagem implements Serializable{

	protected String nome;
	protected int energiaMax;
	protected int energiaAtual;
	protected int ataqueMax;
	protected int ataqueAtual;
	
	public Personagem(){
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getEnergiaMax() {
		return energiaMax;
	}

	public void setEnergiaMax(int energiaMax) {
		this.energiaMax = energiaMax;
	}

	public int getEnergiaAtual() {
		return energiaAtual;
	}

	public void setEnergiaAtual(int energiaAtual) {
		if (energiaAtual>=0) {
			this.energiaAtual = energiaAtual;
		}else{
			this.energiaAtual = 0;
		}
		
	}

	public int getAtaqueMax() {
		return ataqueMax;
	}

	public void setAtaqueMax(int ataqueMax) {
		this.ataqueMax = ataqueMax;
	}

	public int getAtaqueAtual() {
		return ataqueAtual;
	}

	public void setAtaqueAtual(int ataqueAtual) {
		if (ataqueAtual>=0) {
			this.ataqueAtual = ataqueAtual;
		}else{
			this.ataqueAtual = 0;
		}
		
	}
}
