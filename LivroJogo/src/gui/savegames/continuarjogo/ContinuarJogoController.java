package gui.savegames.continuarjogo;

import arquivo.Arquivo;
import beans.Livro;
import gui.savegames.SaveGames;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class ContinuarJogoController extends SaveGames {
	@FXML private Button bt1;
	
	@FXML protected void initialize () {
		super.initialize();
		bt1.setDisable(true);
	}

	
	public void setFiles(ObservableList<String[]> saves) {
		super.getListaArquivos().setItems(saves);
	}
	
	
	@FXML private void checkSelected() {
		if ( super.getListaArquivos().getSelectionModel().getSelectedItem() != null ) {
			bt1.setDisable(false);
		}
	}
	
	@FXML private void carregarArquivo() {
		String s[] = super.getListaArquivos().getSelectionModel().getSelectedItem();
		String fileName = s[3];
		Livro livro = Arquivo.lerArquivo(fileName);
		if (livro != null)
			super.getLvapp().carregarTelaInicial(livro);
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Falha ao ler arquivo!");
			alert.showAndWait();
			super.getLvapp().carregarInicio();
		}
	}
	
}
