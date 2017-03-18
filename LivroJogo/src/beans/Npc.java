package beans;

@SuppressWarnings("serial")
public class Npc extends Personagem{

	public Npc(){

	}
	
	public Npc(String nome, int energiaMax, int ataqueMax) {
		super();
		this.nome = nome;
		this.energiaMax = energiaMax;
		this.energiaAtual = energiaMax;
		this.ataqueMax = ataqueMax;
		this.ataqueAtual = ataqueMax;
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
	
	public String toString() {
		return  nome + "\nEnergia " + energiaAtual + "/" + energiaMax +  "\nAtaque "
				+ ataqueMax;
	}
	
}
