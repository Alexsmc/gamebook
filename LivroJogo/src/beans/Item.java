package beans;
import java.io.*;

@SuppressWarnings("serial")
public class Item implements Serializable, Comparable<Item> {

	private String nome;
	private String descriao;
	private int quantidade;
	private int preco;
	private int modHab;
	private int mobEne;
	private int mobSor;
	private int mobSorMax;

	public Item() {

	}

	public Item(String nome, String descriao, int quantidade, int preco) {
		this.nome = nome;
		this.descriao = descriao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.modHab = 0;
		this.mobEne = 0;
		this.mobSor = 0;
		this.mobSorMax = 0;
	}

	public int getMobEne() {
		return mobEne;
	}

	public void setMobEne(int mobEne) {
		this.mobEne = mobEne;
	}

	public int getMobSor() {
		return mobSor;
	}

	public void setMobSor(int mobSor) {
		this.mobSor = mobSor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescriao() {
		return descriao;
	}

	public void setDescriao(String descriao) {
		this.descriao = descriao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}


	public int getModHab() {
		return modHab;
	}

	public void setModHab(int modificador) {
		this.modHab = modificador;
	}

	public int getMobSorMax() {
		return mobSorMax;
	}

	public void setMobSorMax(int mobSorMax) {
		this.mobSorMax = mobSorMax;
	}
	
	public boolean equals(Item item) {
		return this.nome.equals(item.getNome());
	}

	@Override
	public String toString() {
		return quantidade + "x " + nome+ "\n-"+ descriao + " custo: " + preco + "g\n";
	}
	
	public String toString (int index) {
		return "codigo: "+ index+" "+ this.toString();
	}
	
	public int compareTo (Item outro) {
		return this.nome.compareTo(outro.getNome());
	}

}
