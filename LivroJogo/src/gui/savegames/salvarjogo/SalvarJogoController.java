package gui.savegames.salvarjogo;

import java.io.IOException;

import arquivo.Arquivo;
import beans.Livro;
import gui.LivroJogoApp;
import gui.batalha.criarnpc.CriarNpcController;
import gui.savegames.SaveGames;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SalvarJogoController extends SaveGames {
	@FXML private Button btOvrw;
	@FXML private Button btNewFile;
	private Livro livro;
	private Stage stage;
	
	@FXML protected void initialize () {
		super.initialize();
	}
	@FXML private void overwrite() {
		String s[] =  super.getListaArquivos().getSelectionModel().getSelectedItem();
		String fileName = s[3];
		salvarArquivo(fileName);
	}
	@FXML private void newFile() {
		salvarArquivo(null);
	}
	
	public void setFiles(ObservableList<String[]> saves) {
		super.getListaArquivos().setItems(saves);
	}
	
	
	@FXML private void checkSelected() {
		if ( super.getListaArquivos().getSelectionModel().getSelectedItem() != null ) {
			btOvrw.setDisable(false);
			
		}
	}
	
	private void carregarAtualizarPosicao() {
		Stage stageAP = new Stage();
		stageAP.setTitle("Atualizar posicao");
		stageAP.setResizable(false);
		BorderPane rootScene = new BorderPane();
		Scene scene = new Scene(rootScene, 300, 200);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SalvarJogoController.class.getResource("AtualizarPosicao.fxml"));
			AnchorPane atualizarPos = (AnchorPane) loader.load();
			
			rootScene.setCenter(atualizarPos);
			
			AtualizarPosicaoController controller = loader.getController();
			controller.setLivro(livro);
			controller.setStage(stageAP);
			stageAP.initModality(Modality.APPLICATION_MODAL);
			EventHandler<WindowEvent> e = new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent event) {
			    	stage.close();
			    }
			};
			stageAP.setOnCloseRequest(e);
			
			stageAP.setScene(scene);
			stageAP.showAndWait();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void salvarArquivo(String fileName) {
		carregarAtualizarPosicao();
		boolean salvo = Arquivo.salvaArquivo(livro, fileName);
		Alert alert = new Alert(AlertType.INFORMATION);
		if (salvo) {
			alert.setContentText("Arquivo salvo com sucesso!");
		}else {
			alert.setContentText("Falha ao salvar arquivo!");
		}
		alert.showAndWait();
		stage.close();
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
