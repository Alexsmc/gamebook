package beans;

@SuppressWarnings("serial")
public class EquipItem extends Item {

	boolean equipado;

	public EquipItem(){
		super();
		this.equipado = false;
	}
	
	public EquipItem(String nome, String descriao, int quantidade, int preco) {
		super(nome, descriao, quantidade, preco);
		this.equipado = false;
	}

	public boolean isEquipado() {
		return equipado;
	}

	public void setEquipado(boolean equipado) {
		this.equipado = equipado;
	}

	public boolean equipar() {
		this.setEquipado(true);
		return equipado;
	}

	public boolean desequipar() {
		this.setEquipado(false);
		return equipado;
	}
@Override
	public String toString() {

		return super.getQuantidade() + "x " + super.getNome() + " - C�digo: " + "\n" + "-"
				+ super.getDescriao() + " custo: " + super.getPreco() + "g\n";
	}

}
