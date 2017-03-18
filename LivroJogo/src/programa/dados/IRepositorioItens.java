package programa.dados;

import java.util.List;


import beans.Item;

public interface IRepositorioItens {
	public List<Item> getItens();
	public void setItens(List<Item> itens);
	public void addItem(Item item);
	public void removeItem(Item item);
	public List<Item> listarItens();
	public List<Item> listarEquipItens();
	public List<Item> listarConsumivelItens ();
	public List<Item> listarKeyItens();
	public int indexOf (Item i);
	public Item buscarItem(int cod) throws Exception;
}
