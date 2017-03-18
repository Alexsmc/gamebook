package gui.novojogo;

import beans.Livro;
import gui.LivroJogoApp;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import programa.FachadaLivroJogo;

public class NovoJogoController {
	@FXML private RadioButton rbHab;
	@FXML private RadioButton rbVigor;
	@FXML private RadioButton rbFortuna;
	@FXML private TextField nomeL;
	@FXML private TextField nomeH;
	private int pocaoEscolhida = 1;
	private FachadaLivroJogo fachada;
	private LivroJogoApp lvapp;
	@FXML private void rbHab () {
		pocaoEscolhida = 1;
	}
	@FXML private void rbVigor () {
		pocaoEscolhida = 2;
	}
	@FXML private void rbFortuna () {
		pocaoEscolhida = 3;
	}
	private boolean checkFields() {
		boolean t = false;
		if (nomeL.getText().isEmpty()) {
			nomeL.setPromptText("Preencha o campo");
			t = true;
		}
		if (nomeH.getText().isEmpty()) {
			nomeH.setPromptText("Preencha o campo");
			t = true;
		}
		return t;
	}
	@FXML private void novoJogo() {
		if (checkFields()) {
			return;
		}
		fachada = FachadaLivroJogo.getInstancia();
		Livro livro = fachada.novoJogo(nomeL.getText(), nomeH.getText(), pocaoEscolhida);
		lvapp.carregarTelaInicial(livro);
	}
	
	public void setLivroJogoApp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	
}
