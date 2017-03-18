package gui.batalha;


import beans.Heroi;
import beans.Npc;
import gui.LivroJogoApp;
import gui.batalha.criarnpc.CriarNpcController;
import gui.telainicial.TelaInicialController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import programa.FachadaLivroJogo;

public class BatalhaController {
	@FXML CheckBox cbUsarSorte;
	@FXML private TextArea taLog;
	@FXML private Label lDetalhes;
	@FXML private Label lSorte;
	@FXML private Button continuar;
	@FXML private Button start;
	private boolean usarSorte;
	private Heroi h;
	private Npc npc;
	private LivroJogoApp lvapp;
	private Thread thread;
	private String textDetalhes, textSorte, textLog="";
	
	@FXML private void initialize() {
		taLog.setText("");
		lSorte.setText("");
		h = CriarNpcController.getHeroi();
		npc = CriarNpcController.getNpc();
		lDetalhes.setText(FachadaLivroJogo.getInstancia().mostrarVidaEHab(h, npc));
		continuar.setVisible(false);
	}
	
	@FXML private void batalha() {
		start.setVisible(false);
		continuar.setVisible(true);
		BatalhaController controller = this;
		thread = new Thread(new Runnable() {
			public void run () {
				FachadaLivroJogo.getInstancia().batalha(h, npc, controller);
			}
		});
		thread.start();
	}
	
	
	@FXML private void mudarUsarSorte() {
		usarSorte = !usarSorte;
	}
	
	public void irParaTelaInicial () {
			continuar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					lvapp.carregarTelaInicial(TelaInicialController.getLivro());
				}
			});
	}
				
	public void irParaInicio() {
			continuar.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					lvapp.carregarInicio();
				}
		});
	}
	
	public void atualizaTextos() {
		setlDetalhes(textDetalhes);
		setlSorte(textSorte);
		taLog.setText( this.taLog.getText()+textLog );
		textLog = "";
		taLog.selectPositionCaret(taLog.getLength());
		taLog.deselect();
	}
	@FXML private synchronized void threadNotify() {
		cbUsarSorte.setDisable(true);
		synchronized (this) {
			notify();
		}
	}
	
	
	public synchronized void threadWait() {
		if (h.getSorteAtual()>0)
			cbUsarSorte.setDisable(false);
		else
			usarSorte = false;
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getUsarSorte() {
		return usarSorte;
	}
	public Button getContinuar() {
		return continuar;
	}
	public void setContinuar(Button continuar) {
		this.continuar = continuar;
	}
	public LivroJogoApp getLvapp() {
		return lvapp;
	}
	public void setLvapp(LivroJogoApp lvapp) {
		this.lvapp = lvapp;
	}
	public Heroi getHeroi() {
		return h;
	}
	public void setHeroi(Heroi h) {
		this.h = h;
	}


	public Npc getNpc() {
		return npc;
	}


	public void setNpc(Npc npc) {
		this.npc = npc;
	}
	
	public void addTextLog (String text) {
		textLog = textLog + text;
	}
	
	public void setlDetalhes (String text) {
		this.lDetalhes.setText(text);
	}
	public void setlSorte (String text) {
		this.lSorte.setText(text);
	}


	
	public void setTextDetalhes (String text) {
		this.textDetalhes = text;
	}
	public void setTextSorte (String text) {
		this.textSorte = text;
	}
	
}
