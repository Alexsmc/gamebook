package gui.batalha.criarnpc;

import beans.Heroi;
import beans.Npc;
import gui.LivroJogoApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CriarNpcController {
	@FXML private TextField tfNome;
	@FXML private TextField tfHab;
	@FXML private TextField tfEnerg;
	@FXML private Button bCriar;
	private Stage stage;
	private static Heroi h;
	private static Npc npc;
	

	private LivroJogoApp lvapp;
	
	@FXML private void initialize() {
		bCriar.setDisable(true);
	}
	
	@FXML private void checkFields() {
		if (tfNome.getText().isEmpty() || tfHab.getText().isEmpty() || tfEnerg.getText().isEmpty()) {
//			if (!bCriar.isDisable())
				bCriar.setDisable(true);
		}else {
			bCriar.setDisable(false);
		}
	}
	
	@FXML void criarNpc() {
		npc = new Npc(tfNome.getText(), Integer.parseInt( tfEnerg.getText() ), Integer.parseInt( tfHab.getText() ) );
		lvapp.carregarBatalha();
		stage.close();
	}
	
	public static Heroi getHeroi() {
		return h;
	}

	public static void setHeroi(Heroi heroi) {
		h = heroi;
	}
	
	public LivroJogoApp getLvapp() {
		return lvapp;
	}
	public static Npc getNpc() {
		return npc;
	}

	public void setLvapp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}
