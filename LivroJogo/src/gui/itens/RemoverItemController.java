package gui.itens;

import beans.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programa.dados.Bolsa;

public class RemoverItemController {
	
	private Bolsa bolsa;
	private Item item;
	private Stage stage;
	@FXML private Label lDetalhes;
	@FXML private TextField tfQuantidade;
	
	private boolean checkAll() {
		try {
			int i = Integer.parseInt(tfQuantidade.getText());
			if (i > 0 && i <= item.getQuantidade()) {
				return true;
			}
		}catch(NumberFormatException e) {
			return false;
		}
		return false;
	}
	@FXML private void remover() {
		if (!checkAll()) {
			tfQuantidade.setText("");
			tfQuantidade.setPromptText("Valor invalido");
			return;
		}else {
			int i = Integer.parseInt(tfQuantidade.getText());
			bolsa.removeItem(item, i);
			stage.close();
		}
	}
	
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void setDetalhesText (String text) {
		lDetalhes.setText(text);
	}
}
