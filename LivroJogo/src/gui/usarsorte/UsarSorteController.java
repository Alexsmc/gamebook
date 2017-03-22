package gui.usarsorte;

import beans.Heroi;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import programa.FachadaLivroJogo;

public class UsarSorteController {
	@FXML private Label lSorte;
	@FXML private Button button;
	private Heroi jogador;
	private Stage stage;
	private void initialize() {
		lSorte.setText("");
	}
	@FXML private void usarSorte() {
		
		if (jogador.getSorteAtual() >0)
			if (FachadaLivroJogo.getInstancia().usarSorte(jogador)) {
				lSorte.setText("Passou!");
			}else
				lSorte.setText("Deu ruim!");
		else {
			lSorte.setText("Sua sorte acabou!");
		}
		button.setText("Sair");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void setJogador(Heroi jogador) {
		this.jogador = jogador;
	}
}
