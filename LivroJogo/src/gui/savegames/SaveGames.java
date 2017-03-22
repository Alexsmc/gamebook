package gui.savegames;

import arquivo.Arquivo;
import gui.LivroJogoApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SaveGames {
	private LivroJogoApp lvapp;
	@FXML private TableView<String[]> listaArquivos;
	@FXML private TableColumn<String[], String> colunaNomeL;
	@FXML private TableColumn<String[], String> colunaNomeH;
	@FXML private TableColumn<String[], String> colunaPos;
	
	
	@FXML protected void initialize () {
		ObservableList<String[]> saves = FXCollections.observableArrayList();
		for (String[] s : Arquivo.dadosArquivos()) {
			saves.add(s);
		}
		listaArquivos.setItems(saves);
		
		colunaNomeL.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
		colunaNomeH.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
		colunaPos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
	}
	@FXML protected void excluirArquivo() {
		String fileName = listaArquivos.getSelectionModel().getSelectedItem()[0];
		Arquivo.excluir(fileName);
		initialize();
	}
	
	
	
	public LivroJogoApp getLvapp() {
		return lvapp;
	}
	
	public void setLivroJogoApp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}


	public TableView<String[]> getListaArquivos() {
		return listaArquivos;
	}



	public TableColumn<String[], String> getColunaNomeL() {
		return colunaNomeL;
	}



	public TableColumn<String[], String> getColunaNomeH() {
		return colunaNomeH;
	}



	public TableColumn<String[], String> getColunaPos() {
		return colunaPos;
	}

}
