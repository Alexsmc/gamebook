package gui.ouro;

import beans.Heroi;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class FluxoOuroController {
	@FXML private TextField tfValor;
	@FXML private Button button;
	@FXML private ToggleGroup escolha;
	private Heroi jogador;
	private int opcao;
	private Stage stage;
	private void initialize() {
		
	}
	@FXML private void checkAll() {
		if (tfValor.getText() != null && escolha.getSelectedToggle() != null) {
			button.setDisable(false);
		}else
			button.setDisable(true);
	}
	@FXML private void acresc() {
		opcao = 1;
	}
	@FXML private void decresc() {
		opcao = -1;
	}
	@FXML private void confirmar() {
		int i;
		try{
			i = Integer.parseInt(tfValor.getText());
			i = Integer.max(i, -i);
			jogador.setOuro(i*opcao);
			stage.close();
		}catch(NumberFormatException e) {
			tfValor.setText("");
			tfValor.setPromptText("Valor invalido");
		}
		
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void setJogador(Heroi jogador) {
		this.jogador = jogador;
	}
}
