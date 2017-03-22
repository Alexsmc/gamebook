package beans;

@SuppressWarnings("serial")
public class EquipItem extends Item {

	boolean equipado;

	public EquipItem(){
		super();
		this.equipado = false;
	}
	public EquipItem(Item item, int quantidade) {
		super(item, quantidade);
		equipado = false;
	}
//	public EquipItem(EquipItem item) {
//		super(item);
//		equipado = false;
//	}
	
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
//	@Override
//	public String toString() {
//
//		return super.getQuantidade() + "x " + super.getNome() + "\n"
//				+ super.getDescriao() + "\ncusto: " + super.getPreco() + "g\n";
//	}
	public boolean equals(EquipItem outro) {
		return super.equals(outro) && equipado == outro.isEquipado();
	}

}
