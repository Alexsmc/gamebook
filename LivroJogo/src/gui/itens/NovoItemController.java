package gui.itens;

import beans.ConsumivelItem;
import beans.EquipItem;
import beans.Heroi;
import beans.Item;
import beans.KeyItem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import programa.dados.Bolsa;

public class NovoItemController {
	@FXML TextField tfNome;
	@FXML TextArea taDescricao;
	@FXML TextField tfQuantidade;
	@FXML TextField tfCusto;
	@FXML TextField tfModificador;
	@FXML TextField tfDuracao;
	@FXML RadioButton rbItem;
	@FXML RadioButton rbEquip;
	@FXML RadioButton rbCons;
	@FXML RadioButton rbKey;
	@FXML RadioButton rbHab;
	@FXML RadioButton rbEnergia;
	@FXML RadioButton rbSorte;
	@FXML Button buttonCriar;
	@FXML ToggleGroup tgTipoItem;
	@FXML ToggleGroup tgModificador;
	private Stage stage;
	private Bolsa bolsa;
	private Heroi jogador;
	
	private void initialize() {
		
	}
	@FXML private void checkAll() {
		boolean value = ! ( tfNome.getText().isEmpty() || taDescricao.getText().isEmpty() || tfQuantidade.getText().isEmpty() ||
				tfCusto.getText().isEmpty() || tgModificador.getSelectedToggle() != null && tfModificador.getText().isEmpty() ||
				tgTipoItem.getSelectedToggle() == null);
		if (tgTipoItem.getSelectedToggle() != null && tgTipoItem.getSelectedToggle().equals( rbCons ) ) {
			tfDuracao.setDisable(false);
			if (tfDuracao.getText().isEmpty()) {
				buttonCriar.setDisable(true);
				return;
			}
		}
		else
			tfDuracao.setDisable(true);
		
		if (value) {
			buttonCriar.setDisable(false);
		}else {
			buttonCriar.setDisable(true);
			return;
		}
		
			
		
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private void carregarOuroInsuf() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Ouro insuficiente!");
		alert.showAndWait();
		stage.close();
	}
	
	@FXML private void criarItem() {
		boolean continuar = true;
		String nome, descricao, quantidade, custo, modificador, duracao;
		nome = tfNome.getText(); descricao = taDescricao.getText();
		quantidade = tfQuantidade.getText(); custo = tfCusto.getText();
		modificador = tfModificador.getText(); duracao = tfDuracao.getText();
		if (custo == null || Integer.parseInt(custo) < 0 ) {
			tfCusto.setText("");
			tfCusto.setPromptText("Valor invalido");
			continuar = false;
		}
		if (quantidade == null || Integer.parseInt(quantidade) < 1) {
			tfQuantidade.setText("");
			tfQuantidade.setPromptText("Valor invalido");
			continuar = false;
		}
		if (modificador == null || Integer.parseInt(modificador) < 0) {
			tfModificador.setText("");
			tfModificador.setPromptText("Valor invalido");
			continuar = false;
		}
		try {
			if (!tfDuracao.isDisable() && (duracao == null || duracao.equals("") || 
					Integer.parseInt(duracao) < 0)) {
				tfDuracao.setText("parseint"+ Integer.parseInt(duracao));
				tfDuracao.setPromptText("Valor invalido");
				continuar = false;
			}
		}catch(NumberFormatException e) {
			tfDuracao.setText("");
			tfDuracao.setPromptText("Valor invalido");
			continuar = false;
		}
		if (!continuar) {
			checkAll();
			return;
		}
		
		int iQuantidade = Integer.parseInt(quantidade);
		int iCusto = Integer.parseInt(custo);
		if (jogador.getOuro() < iQuantidade*iCusto) {
			carregarOuroInsuf();
			return;
		}
		
		Item i = new Item();
		i.setNome(nome);
		i.setDescriao(descricao);
		i.setQuantidade(iQuantidade);
		i.setPreco(iCusto);
		RadioButton rb = (RadioButton) tgModificador.getSelectedToggle();
		if (rb == null) {
			
		}else if (rb.equals(rbHab)) {
			i.setModHab(Integer.parseInt(modificador));
		}else if (rb.equals(rbEnergia)) {
			i.setMobEne(Integer.parseInt(modificador));
		}else if (rb.equals(rbSorte)){
			i.setMobSor(Integer.parseInt(modificador));
		}
		
		rb = (RadioButton) tgTipoItem.getSelectedToggle();
		
		
		if (rb.equals(rbCons)) {
			ConsumivelItem consItem = new ConsumivelItem(i, i.getQuantidade());
			consItem.setDuracao(Integer.parseInt(duracao));
			bolsa.addItem(consItem);
		}else if (rb.equals(rbEquip)) {
			EquipItem equipItem = new EquipItem(i, i.getQuantidade());
			bolsa.addItem(equipItem);
		}else if (rb.equals(rbKey)) {
			KeyItem keyItem = new KeyItem(i, i.getQuantidade());
			bolsa.addItem(keyItem);
		}else
			bolsa.addItem(i);
		jogador.setOuro(-(iQuantidade * iCusto));
		
		stage.close();
		
	}
	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}
	public void setJogador(Heroi jogador) {
		this.jogador = jogador;
	}
	
		
}