package gui;

import java.io.IOException;

import beans.Heroi;
import beans.Livro;
import gui.batalha.BatalhaController;
import gui.batalha.criarnpc.CriarNpcController;
import gui.inicio.InicioController;
import gui.novojogo.NovoJogoController;
import gui.savegames.continuarjogo.ContinuarJogoController;
import gui.savegames.salvarjogo.SalvarJogoController;
import gui.telainicial.TelaInicialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LivroJogoApp extends Application {
	private Stage primaryStage;
	private BorderPane rootScene;
	
	@Override
	public void start (Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Assistente Livro Jogo");
		this.primaryStage.setResizable(false);
		this.rootScene = new BorderPane();
		
		Scene scene = new Scene(rootScene, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		carregarInicio();
		
	}
	public void carregarInicio() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("inicio/Inicio.fxml"));
			AnchorPane inicio = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(inicio);
			
			InicioController controller = loader.getController();
			controller.setLivroJogoApp(this);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		launch(args);
	}
	
	public void carregarNovoJogo () {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("novojogo/NovoJogo.fxml"));
			AnchorPane novoJogo = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(novoJogo);
			
			NovoJogoController controller = loader.getController();
			controller.setLivroJogoApp(this);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarContinuarJogo () {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("savegames/continuarjogo/ContinuarJogo.fxml"));
			
			AnchorPane continuarJogo = (AnchorPane) loader.load();
			this.rootScene.setCenter(continuarJogo);
			
			ContinuarJogoController controller = loader.getController();
			controller.setLivroJogoApp(this);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void carregarSalvarJogo(Livro livro) {
		Stage stage = new Stage();
		stage.setTitle("Salvar Progresso");
		stage.setResizable(false);
		BorderPane rootScene = new BorderPane();
		Scene scene = new Scene(rootScene, 600, 400);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("savegames/salvarjogo/SalvarJogo.fxml"));
			AnchorPane salvarJogo = (AnchorPane) loader.load();
			
			rootScene.setCenter(salvarJogo);
			
			SalvarJogoController controller = loader.getController();
			controller.setLivroJogoApp(this);
			controller.setStage(stage);
			controller.setLivro(livro);
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarTelaInicial (Livro livro) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("telainicial/TelaInicial.fxml"));
			AnchorPane telaInicial = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(telaInicial);
			
			TelaInicialController controller = loader.getController();
			TelaInicialController.setLivro(livro);
			controller.setLivroJogoApp(this);
			controller.refresh();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarCriarNpc(Heroi h) {
		Stage stage = new Stage();
		stage.setTitle("Criar NPC");
		stage.setResizable(false);
		BorderPane rootScene = new BorderPane();
		Scene scene = new Scene(rootScene, 400, 200);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("batalha/criarnpc/CriarNpc.fxml"));
			AnchorPane criarNpc = (AnchorPane) loader.load();
			
			rootScene.setCenter(criarNpc);
			
			CriarNpcController controller = loader.getController();
			controller.setLvapp(this);
			CriarNpcController.setHeroi(h);
			controller.setStage(stage);
			stage.setScene(scene);
			stage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarBatalha () {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LivroJogoApp.class.getResource("batalha/Batalha.fxml"));
			AnchorPane batalha = (AnchorPane) loader.load();
			
			this.rootScene.setCenter(batalha);
			
			BatalhaController controller = loader.getController();
			controller.setLvapp(this);
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    System.exit(0);
	    
	}
}
