package gui.telainicial;

import java.io.IOException;

import beans.Livro;
import gui.LivroJogoApp;
import gui.ouro.FluxoOuroController;
import gui.usarsorte.UsarSorteController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaInicialController {
	@FXML private Label lDetalhes;
	@FXML private TextField tf;
	private static Livro livro;
	private LivroJogoApp lvapp;
	private static TelaInicialController controller;
	
	@FXML private void initialize() {
		controller = this;
	}
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
	
	@FXML private void itens() {
		lvapp.carregarTelaItens(livro);
	}
	
	@FXML private void usarSorte() {
		Stage stage = new Stage();
		stage.setTitle("Usar sorte");
		stage.setResizable(false);
		BorderPane rootScene = new BorderPane();
		Scene scene = new Scene(rootScene, 400, 300);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("usarsorte/UsarSorte.fxml"));
			AnchorPane usarSorte = (AnchorPane) loader.load();
			
			rootScene.setCenter(usarSorte);
			
			UsarSorteController controller = loader.getController();
			controller.setStage(stage);
			controller.setJogador(livro.getJogador());
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			refresh();
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML private void fluxoOuro() {
		Stage stage = new Stage();
		stage.setTitle("Fluxo ouro");
		stage.setResizable(false);
		BorderPane rootScene = new BorderPane();
		Scene scene = new Scene(rootScene, 300, 210);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("ouro/FluxoOuro.fxml"));
			AnchorPane fluxoOuro = (AnchorPane) loader.load();
			
			rootScene.setCenter(fluxoOuro);
			
			FluxoOuroController controller = loader.getController();
			controller.setStage(stage);
			controller.setJogador(livro.getJogador());
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			refresh();
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Livro getLivro() {
		return livro;
	}
	
	public static TelaInicialController getController() {
		return controller;
	}
	
}
