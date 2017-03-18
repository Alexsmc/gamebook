package gui.inicio;
import gui.LivroJogoApp;
import javafx.fxml.FXML;

public class InicioController {
	private LivroJogoApp lvapp;
	
	@FXML private void novoJogo() {
		System.out.println("novo jogo");
		lvapp.carregarNovoJogo();
	}
	@FXML private void carregarJogo() {
		System.out.println("carregar jogo");
		lvapp.carregarContinuarJogo();
	}
	
	public void setLivroJogoApp (LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	
	@FXML private void batalha() {
		
	}
	
	
}
