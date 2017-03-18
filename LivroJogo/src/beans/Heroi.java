package beans;

import java.io.*;

import programa.dados.Bolsa;

@SuppressWarnings("serial")
public class Heroi extends Personagem implements Serializable {

	private int sorteMax;
	private int sorteAtual;
	private Bolsa bolsa;
	private int ouro;
//	private int posicao;

	public Heroi() {
		this.ouro = 30;
		this.energiaAtual = energiaMax;
		this.ataqueAtual = ataqueMax;
		this.sorteAtual = sorteMax;
	}

	public Heroi(String nome, int energiaMax, int ataqueMax, int sorteMax, Bolsa bolsa) {
		super();
		this.nome = nome;
		this.energiaMax = energiaMax;
		this.energiaAtual = energiaMax;
		this.ataqueMax = ataqueMax;
		this.ataqueAtual = ataqueMax;
		this.sorteMax = sorteMax;
		this.sorteAtual = sorteMax;
		this.setBolsa(bolsa);
		this.ouro = 30;
//		this.posicao = 1;
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

		this.energiaAtual = this.energiaAtual + energiaAtual;

		if (this.energiaAtual > this.energiaMax) {
			this.energiaAtual = this.energiaMax;
		} else if (this.energiaAtual < 0) {
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

		this.ataqueAtual = this.ataqueAtual + ataqueAtual;

		if (this.ataqueAtual < 0) {
			this.ataqueAtual = 0;
		}

	}

	public int getSorteMax() {
		return sorteMax;
	}

	public void setSorteMax(int sorteMax) {
		this.sorteMax = sorteMax;
	}

	public int getSorteAtual() {
		return sorteAtual;
	}

	public void setSorteAtual(int sorteAtual) {
		this.sorteAtual = this.sorteAtual + sorteAtual;

		if (this.sorteAtual > this.sorteMax) {
			this.sorteAtual = this.sorteMax;
		}
		if (this.sorteAtual < 0) {
			this.sorteAtual = 0;
		}
	}

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}

	public int getOuro() {
		return ouro;
	}

	public void setOuro(int ouro) {

		if (ouro < 0 && Math.abs(ouro) > this.ouro) {
			System.out.println("Saldo insuficiene");
			return;
		}
		this.ouro += ouro;
		if (this.ouro <= 0) {
			this.ouro = 0;
		}
	}

	public String incremento() {
		String incremento = null;
		if (this.ataqueAtual - this.ataqueMax > 0) {
			incremento = "(+" + (this.ataqueAtual - this.ataqueMax) + ")";
		} else if (this.ataqueAtual - this.ataqueMax <= 0) {
			incremento = "(" + (this.ataqueAtual - this.ataqueMax) + ")";
		}
		return incremento;
	}

	public void modificador(Item item) {
		this.setAtaqueAtual(item.getModHab());
		this.setEnergiaAtual(item.getMobEne());
		this.setSorteMax(this.sorteMax + item.getMobSorMax());
		this.setSorteAtual(item.getMobSor());
		return;
	}
	
	public void equiparItem(EquipItem equip){
		if(!equip.isEquipado()){
			equip.equipar();
			this.modificador(equip);
		}
		return;
	}
	
	public void desequiparItem(EquipItem equip){
		if (equip.isEquipado()) {
			equip.desequipar();
			this.setAtaqueAtual(-equip.getModHab());
			this.setEnergiaAtual(-equip.getMobEne());
			this.setSorteAtual(-equip.getMobSor());
		}
		return;
	}

	@Override
	public String toString() {
		return "Nome personagem:"+"\t"+ nome+ "\nEnergia:\t" + energiaAtual + "/" + energiaMax + "\nAtaque:\t" + ataqueMax + incremento()
				+ "\nSorte:\t" + sorteAtual + "/" + sorteMax + "\nOuro:\t" + ouro + "g\n";
	}

//	public int getPosicao() {
//		return posicao;
//	}

//	public void setPosicao(int posicao) {
//		this.posicao = posicao;
//	}

}
