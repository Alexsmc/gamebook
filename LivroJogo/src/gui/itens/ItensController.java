package gui.itens;

import java.io.IOException;

import beans.ConsumivelItem;
import beans.EquipItem;
import beans.Item;
import beans.Livro;
import gui.LivroJogoApp;
import gui.telainicial.TelaInicialController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import programa.FachadaLivroJogo;
import programa.dados.Bolsa;

public class ItensController {
	@FXML private TreeTableView<Item> treeTable;
	@FXML private TreeTableColumn<Item, String> colunaNome;
	@FXML private TreeTableColumn<Item, String> colunaQuantidade;
	@FXML private Label lDetalhes;
	@FXML private Button buttonEquipar;
	@FXML private Button buttonUsar;
	private TreeItem<Item> tiBolsa = new TreeItem<Item>(new Item("Bolsa", "", 0, -1));
	private TreeItem<Item> emUso = new TreeItem<Item>(new Item("Usando", "", 0, -1));
	TreeItem<Item> semUsar = new TreeItem<Item>(new Item("Sem usar", "", 0, -1));
	private Livro livro;
	private LivroJogoApp lvapp;
	
	@FXML protected void initialize () {
		tiBolsa.setExpanded(true);
		ObservableList<Item> itens, consumiveis, equipaveis, key;
		itens = FXCollections.observableArrayList();
		consumiveis = FXCollections.observableArrayList();
		equipaveis = FXCollections.observableArrayList();
		key = FXCollections.observableArrayList();
		Bolsa bolsa = TelaInicialController.getLivro().getJogador().getBolsa();
		
		
		TreeItem<Item> semUsarItem, emUsoConsumivel, semUsarConsumivel, emUsoEquipavel, semUsarEquipavel,
		semUsarKey;
		Item i = new Item("Item", "", 0, -1);
		semUsarItem = new TreeItem<Item>(i);
		i = new Item("Consumivel", "", 0, -1);
		emUsoConsumivel = new TreeItem<Item>(i);
		i = new Item(i, 0);
		semUsarConsumivel = new TreeItem<Item>(i);
		i = new Item("Equipavel", "", 0, -1);
		emUsoEquipavel = new TreeItem<Item>(i);
		i = new Item(i, 0);
		semUsarEquipavel = new TreeItem<Item>(i);
		i = new Item("Key Item", "", 0, -1);
		semUsarKey = new TreeItem<Item>(i);
		
		itens.setAll(bolsa.listarItens());
		consumiveis.setAll(bolsa.listarConsumivelItens());
		equipaveis.setAll(bolsa.listarEquipItens());
		key.setAll(bolsa.listarKeyItens());
		
		for(Item item : itens) {
			semUsarItem.getChildren().add(new TreeItem<Item>(item));
			semUsarItem.getValue().setQuantidade(item.getQuantidade());
		}
		for (Item item : consumiveis) {
			ConsumivelItem consumivelItem = (ConsumivelItem) item;
			TreeItem<Item> tiItem = new TreeItem<Item>(item);
			if (consumivelItem.isEquipado()) {
				emUsoConsumivel.getChildren().add(tiItem);
				emUsoConsumivel.getValue().setQuantidade(item.getQuantidade());
			}
			else {
				semUsarConsumivel.getChildren().add(tiItem);
				semUsarConsumivel.getValue().setQuantidade(item.getQuantidade());
			}
		}
		
		for(Item item : equipaveis) {
			EquipItem equipItem = (EquipItem) item;
			TreeItem<Item> tiItem = new TreeItem<Item>(item);
			if (equipItem.isEquipado()) {
				emUsoEquipavel.getChildren().add(tiItem);
				emUsoEquipavel.getValue().setQuantidade(item.getQuantidade());
			}else {
				semUsarEquipavel.getChildren().add(tiItem);
				semUsarEquipavel.getValue().setQuantidade(item.getQuantidade());
			}
		}
		
		for(Item item : key) {
			semUsarKey.getChildren().add(new TreeItem<Item>(item));
			semUsarKey.getValue().setQuantidade(item.getQuantidade());
		}
		
		
		emUso.getChildren().setAll(emUsoConsumivel, emUsoEquipavel);
		for (TreeItem<Item> treeI : emUso.getChildren()) {
			emUso.getValue().setQuantidade(treeI.getValue().getQuantidade());
		}
		
		semUsar.getChildren().setAll(semUsarConsumivel, semUsarEquipavel, semUsarItem, semUsarKey);
		for (TreeItem<Item> treeI : semUsar.getChildren()) {
			semUsar.getValue().setQuantidade(treeI.getValue().getQuantidade());
		}
		
		
		tiBolsa.getChildren().setAll(emUso, semUsar);
		for (TreeItem<Item> treeI : tiBolsa.getChildren()) {
			tiBolsa.getValue().setQuantidade(treeI.getValue().getQuantidade());
		}
		
		treeTable.setRoot(tiBolsa);
		
		
		
		colunaNome.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Item, String> param) -> new SimpleStringProperty(param.getValue().getValue().getNome()));
		colunaQuantidade.setCellValueFactory( (TreeTableColumn.CellDataFeatures<Item, String> param) -> new SimpleStringProperty(""+param.getValue().getValue().getQuantidade()));
		
	}
	
	public static void refresh() {
		
	}
	
	@FXML private void checkSelected() {
		Item i = null;
		TreeItem<Item> tiItem = treeTable.getSelectionModel().getSelectedItem();
		
		if (tiItem != null && tiItem.getValue().getPreco() >-1)i = tiItem.getValue();
		if (i != null) {
			lDetalhes.setText("Detalhes\n"+i.toString());
		}else {
			buttonEquipar.setDisable(true);
			buttonUsar.setDisable(true);
		}
		if (i instanceof EquipItem) {
			buttonEquipar.setDisable(false);
			buttonUsar.setDisable(true);
		}else if (i instanceof Item) {
			buttonUsar.setDisable(false);
			buttonEquipar.setDisable(true);
		}
	}
	
	@FXML private void novoItem() {
		Stage stage = new Stage();
		stage.setTitle("Gerar item");
		stage.setResizable(false);
		BorderPane rootScene = new BorderPane();
		Scene scene = new Scene(rootScene, 600, 400);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ItensController.class.getResource("NovoItem.fxml"));
			AnchorPane novoItem = (AnchorPane) loader.load();
			
			rootScene.setCenter(novoItem);
			
			NovoItemController controller = loader.getController();
			controller.setStage(stage);
			controller.setBolsa(livro.getJogador().getBolsa());
			controller.setOuro(livro.getJogador().getOuro());
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			initialize();
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void usarItem() {
		Item item = treeTable.getSelectionModel().getSelectedItem().getValue();
		FachadaLivroJogo.getInstancia().usarItem(livro.getJogador(), item);
		initialize();
	}
	@FXML private void equiparItem() {
		EquipItem item = (EquipItem) treeTable.getSelectionModel().getSelectedItem().getValue();
		if (!item.isEquipado())
			try {
				FachadaLivroJogo.getInstancia().equiparItem(livro.getJogador(), item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				FachadaLivroJogo.getInstancia().desequiparItem(livro.getJogador(), item);
			}catch (Exception e) {
				e.printStackTrace();
			}
		initialize();
	}
	@FXML private void voltar() {
		lvapp.carregarTelaInicial(livro);
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public void setLivroJogoApp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	
}
