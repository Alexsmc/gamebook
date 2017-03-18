package programa.dados;

import java.io.*;
import java.util.*;

import beans.EquipItem;
import beans.Item;
import beans.KeyItem;
import beans.ConsumivelItem;

@SuppressWarnings("serial")
public class Bolsa implements IRepositorioItens, Serializable {

	private List<Item> itens;

	public Bolsa() {
		super();
		this.itens = new ArrayList<Item>();
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	boolean incdecreaseQuantidade (Item item, int v) {
		if (itens.contains(item)) {
			int p = this.itens.indexOf(item);
			Item i = itens.get(p);
			i.setQuantidade(v);
			return true;
		}
		return false;
	}
	public void addItem(Item item) {
		if (incdecreaseQuantidade(item, 1)) {
			//System.out.println("Item guardado!!");
			return;
		}
		this.itens.add(item);
		
		//System.out.println("Item guardado!!");
	}

	public void removeItem(Item item) {
		if (item.getQuantidade() > 1) {
			incdecreaseQuantidade(item, -1);
			//System.out.println("Item guardado!!");
			return;
		}
		this.itens.remove(item);
		//System.out.println("Item guardado!!");
		}

	public List<Item> listarItens() {
		List<Item> itens = new ArrayList<Item>(this.itens);
		return itens;
	}
	public List<Item> listarEquipItens() {
		System.out.println("chamada");
		List<Item> itens = new ArrayList<Item>();
		for (Item i : this.itens) {
			if (i instanceof EquipItem) {
				itens.add(i);
			}
		}
		return itens;
		
	}
	public List<Item> listarConsumivelItens () {
		List<Item> itens = new ArrayList<Item>();
		for (Item i : this.itens) {
			if (i instanceof ConsumivelItem) 
				itens.add(i);
		}
		return itens;
	}
	public List<Item> listarKeyItens() {
		List<Item> itens = new ArrayList<Item>();
		for (Item i : this.itens) {
			if (i instanceof KeyItem)
				itens.add(i);
		}
		return itens;
	}
	
	public int indexOf (Item i) {
		return this.itens.indexOf(i);
	}

	public Item buscarItem(int cod) throws Exception{
		return itens.get(cod);
		//System.out.println("Item n�o encontrado!");
	}

}