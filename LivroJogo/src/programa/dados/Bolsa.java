package programa.dados;

import java.io.*;
import java.util.*;

import beans.EquipItem;
import beans.Heroi;
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
		for (Item i : itens)
			System.out.println(i);
		
		//System.out.println("Item guardado!!");
	}

	public void removeItem(Item item) {
		System.out.println("removeItem");
		if (item.getQuantidade() > 1) {
			incdecreaseQuantidade(item, -1);
			//System.out.println("Item guardado!!");
			return;
		}
		this.itens.remove(item);
		//System.out.println("Item guardado!!");
		}
	
	
	public String equiparItem(Heroi heroi, EquipItem item)  throws Exception{
//		System.out.println("euiparItem");
//		for (int c = 0; c < itens.size(); c++) {
//			Item i = itens.get(c);
//			if (i.equals(item)) {
//				if (i instanceof EquipItem && ((EquipItem)i).isEquipado()) {
//					return "Item ja equipado!";
//				}
//			}
//		}
		Item itemEquipado = null;
		if (item instanceof ConsumivelItem) {
			ConsumivelItem i = (ConsumivelItem) item;
			itemEquipado = new ConsumivelItem(i);
		}else if (item instanceof EquipItem) {
			EquipItem i = (EquipItem) item;
			itemEquipado = new EquipItem(i, 1);
		}
		itens.add(itemEquipado);
		try {
			removeItem(item);
			heroi.equiparItem( (EquipItem) itemEquipado);
			return "Item Equipado";
		}catch (ClassCastException | IndexOutOfBoundsException e) {
			System.out.println(e);
			System.out.println("Codigo informado nao e valido");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "Erro. Verifique o console para mais informacoes";
	}
	
	public String desequiparItem (Heroi heroi, Item item) throws Exception {
			EquipItem itemDesequipado = (EquipItem) item;
			try {
				for (int index = 0; index < itens.size(); index++) {
					Item i = itens.get(index);
					if (i instanceof EquipItem && itemDesequipado.equals((EquipItem) i)) {
						itens.remove(index);
					}
				}
				heroi.desequiparItem(itemDesequipado);
				if (itemDesequipado instanceof ConsumivelItem) {
					ConsumivelItem i = (ConsumivelItem) itemDesequipado;
					for (int index = 0; index < itens.size(); index++) {
						Item itemLista = itens.get(index);
						if (itemLista instanceof ConsumivelItem && i.equals((ConsumivelItem) itens.get(index))) {
							itens.get(index).setQuantidade(1);
							break;
						}else if (index == itens.size()-1) {
							System.out.println("cria novo");
							itens.add(i);
							break;
						}
					}
				}else
					addItem(itemDesequipado);
				
			}catch (ClassCastException | IndexOutOfBoundsException e) {
				System.out.println(e);
				System.out.println("Codigo informado nao e valido");
			}catch (Exception e) {
				e.printStackTrace();
			}
		return "Desequipado";
	}

	public List<Item> listarItens() {
		List<Item> itens = new ArrayList<Item>();
		for (Item i : this.itens) {
			if ( ! (i instanceof ConsumivelItem || i instanceof EquipItem || i instanceof KeyItem) ) {
				System.out.println(i.getNome()+" "+(i instanceof ConsumivelItem));
				itens.add(i);
			}
		}
		return itens;
	}
	public List<Item> listarEquipItens() {
		System.out.println("chamada");
		List<Item> itens = new ArrayList<Item>();
		for (Item i : this.itens) {
			if (!(i instanceof ConsumivelItem) && i instanceof EquipItem) {
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
	
	public List<Item> listarTodos() {
		List<Item> itens = new ArrayList<Item>(this.itens);
		return itens;
	}
	
	public int indexOf (Item i) {
		return this.itens.indexOf(i);
	}

	public Item buscarItem(int cod) {
		return itens.get(cod);
		//System.out.println("Item n�o encontrado!");
	}

	@Override
	public void removeItem(int index) {
		itens.remove(index);
	}

}