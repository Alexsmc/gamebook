package gui.savegames.salvarjogo;

import beans.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AtualizarPosicaoController {
	@FXML private TextField tfPosicao;
	private Livro livro;
	private Stage stage;
	

	
	@FXML private void atualizarPosicao() {
		Integer i = new Integer(tfPosicao.getText());
		livro.setPosicao(i);
		stage.close();
	}


	public void setLivro(Livro livro) {
		this.livro = livro;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
