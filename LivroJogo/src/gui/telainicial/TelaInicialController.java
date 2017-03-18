package gui.telainicial;

import beans.Livro;
import gui.LivroJogoApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TelaInicialController {
	@FXML private Label lDetalhes;
	@FXML private TextField tf;
	private static Livro livro;
	private LivroJogoApp lvapp;
	
	public void refresh () {
		lDetalhes.setText(livro.toString());
	}
	public static void setLivro (Livro outroLivro) {
		livro = outroLivro;
	}
	public void setLivroJogoApp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	
	@FXML private void batalha() {
		lvapp.carregarCriarNpc(livro.getJogador());
	}
	
	@FXML private void salvarJogo() {
		lvapp.carregarSalvarJogo(livro);
	}
	public static Livro getLivro() {
		return livro;
	}
	
	
}
