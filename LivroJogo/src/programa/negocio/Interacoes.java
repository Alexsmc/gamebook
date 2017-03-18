package programa.negocio;

import java.util.*;

import beans.EquipItem;
import beans.Heroi;
import beans.Item;
import beans.Livro;
import beans.Loja;
import beans.Npc;
import gui.batalha.BatalhaController;
import javafx.application.Platform;
import programa.dados.Bolsa;

public class Interacoes {

	private Random dado;
	private Scanner s;
	

	public Interacoes() {
		
	}

	public boolean usarSorte(Heroi jogador, BatalhaController controller) {
		boolean sorte = false;
		boolean validController = controller != null;
		dado = new Random();
//		s = new Scanner(System.in);
		int valor = dado.nextInt(12) + 1;
		String log;
		jogador.setSorteAtual(-1);
		if (valor <= jogador.getSorteAtual()) {
			sorte = true;
			log = "Passou! \n";
		} else {
			log = "Deu ruim! \n";
		}
		System.out.println(log+ "Sorte Atual: " + jogador.getSorteAtual() + "/" + jogador.getSorteMax());
		if (validController)
			controller.addTextLog(log);
		return sorte;
	}

	public String mostrarVidaEHab(Heroi jogador, Npc npc) {
		
		return
		//System.out.println(
				jogador.getNome() + "\nEnergia: " + jogador.getEnergiaAtual() + "/" + jogador.getEnergiaMax()
				+ " Forca:" + jogador.getAtaqueMax() + jogador.incremento() + "\n"
				//);
				+"\n"+
		//System.out.println(
				npc.getNome() + "\nEnergia: " + npc.getEnergiaAtual() + "/" + npc.getEnergiaMax()
				+ " Forca: " + npc.getAtaqueAtual() + "\n"
				//);
				;
	}

	public void batalha(Heroi jogador, Npc npc, BatalhaController controller) {
		boolean validController = (controller != null);
		s = new Scanner(System.in);
		dado = new Random();
		if (npc == null) npc = criarNpc();
		String log = "", detalhes, tsorte="";
		Runnable r = null;
		do {
			detalhes = mostrarVidaEHab(jogador, npc);
			tsorte = "Deseja usar a sorte?"+ "\n"+ "Sorte atual:" + jogador.getSorteAtual();
			System.out.println(detalhes);
			if (validController) {
				controller.setTextDetalhes(detalhes);
				controller.setTextSorte(tsorte);
				controller.addTextLog(log);
				r = new Runnable() {
					@Override
					public void run() {
						controller.atualizaTextos();
					}
				};
				Platform.runLater(r);
			}
			if (!( jogador.getEnergiaAtual() > 0 && npc.getEnergiaAtual() > 0 )) {
				break;
			}
			int fM = (dado.nextInt(12) + 1) + npc.getAtaqueAtual();
			int fJ = (dado.nextInt(12) + 1) + jogador.getAtaqueAtual();

			log = npc.getNome() + " ataca com: " + fM + " x " + fJ + " de " + jogador.getNome()+"\n";
			if (validController) {
				controller.addTextLog(log);
			}

			if (fM > fJ) {
				log = npc.getNome() + " ataca com " + fM + " de forca e fere " + jogador.getNome() + ".\n";
				System.out.println(log);
				if (validController) {
					controller.addTextLog(log);
				}
//				tsorte = "Deseja usar a sorte?"+ "\n"+ "Sorte atual:" + jogador.getSorteAtual();
				int decisao;
				if (!validController) {
					System.out.println(tsorte+"\n1-Sim/2-Nao");
					decisao = s.nextInt();
					
				}else {
					///////////////////////////////////////////////////////////////////////////////////////////////
					Platform.runLater(r);
					controller.threadWait();
					decisao = controller.getUsarSorte() ? 1 : 2;
				}
				if (jogador.getSorteAtual() > 0)
				switch (decisao) {
				case 1:
					boolean sorte = usarSorte(jogador, controller);
					if (sorte) {
						log = "Ufa, que sorte!\n"+ jogador.getNome() + " Perde 1 de vida\n";
						jogador.setEnergiaAtual(-1);
					} else {
						log = "Ops, ma sorte!\n"+  jogador.getNome() + " Perde 3 de vida\n";
						jogador.setEnergiaAtual(-3);
					}
					break;
				default:
					log = jogador.getNome() + " Perde 2 de vida\n";
					jogador.setEnergiaAtual(-2);
					break;
				}

			} else if (fM < fJ) {
				log = jogador.getNome() + " ataca com " + fJ + " de forca e fere " + npc.getNome() + ".\n";
				System.out.println(log);
				if (validController) {
					controller.addTextLog(log);
				}
//				tsorte = "Deseja usar a sorte?\n"+ "Sorte atual:" + jogador.getSorteAtual()+"\n";
				int decisao;
				if (!validController) {
					System.out.println(tsorte+"\n1-Sim/2-Nao");
					decisao = s.nextInt();
					
				}else {
					///////////////////////////////////////////////////////////////////////////////////////////////
					Platform.runLater(r);
					controller.threadWait();
					decisao = controller.getUsarSorte() ? 1 : 2;
				}
				if (jogador.getSorteAtual() > 0)
				switch (decisao) {
				case 1:
					boolean sorte = usarSorte(jogador, controller);
					if (sorte) {
						log = "Wow!, que sorte!\n"+ npc.getNome() + " Perde 4 de vida\n";
						npc.setEnergiaAtual(npc.getEnergiaAtual() - 4);
					} else {
						log = "Ops, ma sorte!\n"+ npc.getNome() + " Perde 1 de vida\n";
						npc.setEnergiaAtual(npc.getEnergiaAtual() - 1);
					}
					break;
				default:
					log = npc.getNome() + " Perde 2 de vida\n";
					npc.setEnergiaAtual(npc.getEnergiaAtual() - 2);
					break;
				}
				
			}
			
			System.out.println(log+"\n");
			if (validController) {
				controller.addTextLog(log);
			}
			log = log + "\n";

		} while (true);

		if (jogador.getEnergiaAtual() == 0) {
			log = "Voce foi derrotado!\n   Game Over!!!\n";
			if (validController) {
				controller.irParaInicio();
			}
		} else if (npc.getEnergiaAtual() == 0) {
			log = "Vitoria!! Inimigo derrotado!\n";
			if (validController) {
				controller.irParaTelaInicial();
			}
		}
		detalhes = mostrarVidaEHab(jogador, npc);
		System.out.println(log);
		System.out.println(detalhes);
		if (validController) {
			controller.addTextLog(log);
		}

	}
	
	public Livro novoJogo(String nomeLivro, String nomeHeroi, int opcao) {
		Livro livro = new Livro();
		livro.setNome(nomeLivro);
		livro.setJogador( criarPersonagem(nomeHeroi, opcao) );
		return livro;
	}

	private Heroi criarPersonagem(String nome, int opcao) {
		Bolsa b = new Bolsa();
//		s = new Scanner(System.in);
		dado = new Random();
		
//		System.out.println("Qual o nome do heroi?");
		Heroi h = new Heroi(nome, dado.nextInt(12) + 13, dado.nextInt(6) + 7, dado.nextInt(6) + 7, b);
		Item i = new EquipItem("Espada", "Espada sem nada demais", 1, 0);
		b.addItem(i);
		i = new EquipItem("Armadura de Couro", "Armadura simples", 1, 0);
		b.addItem(i);
		i = new EquipItem("Escudo", "Escudo simples sem nada demais", 1, 0);
		b.addItem(i);
		i = new Item("Provisões", "Cura +4 de Energia", 10, 0);
		i.setMobEne(4);
		b.addItem(i);
		i = new Item("Lampiao", "Para iluminar os caminhos escuros", 1, 0);

		// b.listarItens();

		System.out.println("Escolha uma  poao abaixo:\n1- Pocao da Habilidade\nRestaura seus pontos de Forca."
				+ "\n2- Pocao do Vigor\nRestaura toda sua energia.\n3- Pocao da Fortuna\nRestaura sua sorte e aumenta em +1.");
//		int opcao = s.nextInt();
		while ((opcao <= 0) || (opcao >= 4)) {
			System.out.println("Opcao Invalida, digite novamente");
			opcao = s.nextInt();
		}

		switch (opcao) {
		case 1:
			i = new Item("Pocao da Habilidade", "Restaura seus pontos de Forca.", 2, 0);
			i.setModHab(h.getAtaqueMax());
			b.addItem(i);
			break;
		case 2:
			i = new Item("Pocao do Vigor", "Restaura toda sua energia", 2, 0);
			i.setMobEne(h.getEnergiaMax());
			b.addItem(i);
			break;
		default:
			i = new Item("Pocao da Fortuna", "Restaura sua sorte e aumenta em +1", 2, 0);
			i.setMobSorMax(1);
			i.setMobSor(h.getSorteMax() + 1);
			b.addItem(i);
			break;
		}

		System.out.println(h);
		for (Item it : b.listarItens()) {
			System.out.println(it);
		}

		return h;
	}

	public Npc criarNpc() {
		s = new Scanner(System.in);
		Npc m = new Npc();
		System.out.println("Digite o nome do NPC:");
		m.setNome(s.nextLine());
		System.out.println("Qual o poder de Habilidade?:");
		m.setAtaqueMax(s.nextInt());
		m.setAtaqueAtual(m.getAtaqueMax());
		System.out.println("Qual a Energia?:");
		m.setEnergiaMax(s.nextInt());
		m.setEnergiaAtual(m.getEnergiaMax());
		return m;
	}

	public Item gerarItem() {
		Item i = new Item();
		int mod = 0;
		s = new Scanner(System.in);
		/*
		 * System.out.println("Qual o nome do Item?"); i.setNome(s.nextLine());
		 * System.out.println("Escreva uma breve descricao:");
		 * i.setDescriao(s.nextLine()); System.out.println("Quantos voce tem?");
		 * i.setQuantidade(s.nextInt()); System.out.println("Quanto custou?");
		 * i.setPreco(s.nextInt());
		 */
		System.out.println("Qual o tipo do item? \n1- Consumivel\n2- Equipamento\n3- Item chave");
		mod = s.nextInt();
		s.nextLine();
		if (mod == 1) {
			System.out.println("Qual o nome do Item?");
			i.setNome(s.nextLine());
			System.out.println("Escreva uma breve descricao:");
			i.setDescriao(s.nextLine());
			System.out.println("Quantos voce tem?");
			i.setQuantidade(s.nextInt());
			System.out.println("Quanto custou?");
			i.setPreco(s.nextInt());
			System.out.println("Qual Habilidade modifica?\n1- Habilidade\n2- Energia\n3- Sorte");
			mod = s.nextInt();
			switch (mod) {
			case 1:
				System.out.println("Qual o modificador de Habilidade?");
				i.setModHab(s.nextInt());
			case 2:
				System.out.println("Qual o modificador de Energia?");
				i.setMobEne(s.nextInt());
			case 3:
				System.out.println("Qual o modificador de Sorte?");
				i.setMobSor(s.nextInt());
			default:
				break;
			}
		} else if (mod == 2) {
			EquipItem equip = new EquipItem();
			System.out.println("Qual o nome do Item?");
			s.nextLine();
			equip.setNome(s.nextLine());
			System.out.println("Escreva uma breve descricao:");
			equip.setDescriao(s.nextLine());
			s.nextLine();
			System.out.println("Quantos voce tem?");
			int cust = s.nextInt();
			equip.setQuantidade(cust);
			System.out.println("Quanto custou?");
			cust = s.nextInt();
			equip.setPreco(cust);
			System.out.println("Qual Habilidade modifica?\n1- Habilidade\n2- Energia\n3- Sorte");
			mod = s.nextInt();
			switch (mod) {
			case 1:
				System.out.println("Qual o modificador de Habilidade?");
				equip.setModHab(s.nextInt());
				i = equip;
			case 2:
				System.out.println("Qual o modificador de Energia?");
				equip.setMobEne(s.nextInt());
				i = equip;
			case 3:
				System.out.println("Qual o modificador de Sorte?");
				equip.setMobSor(s.nextInt());
				i = equip;
			default:
				break;
			}
		}
		return i;
	}

	public void usarPocao(Heroi jogador) throws Exception{
		System.out.println("Digite o codigo da pocao");
		int cod = s.nextInt();
		Item i = jogador.getBolsa().buscarItem(cod);
		jogador.modificador(i);
		jogador.getBolsa().removeItem(i);
		System.out.println(jogador);
		return;
	}

	public void equiparItem(Heroi heroi)  throws Exception{
		for (Item i : heroi.getBolsa().listarEquipItens()) {
			System.out.println(i.toString( heroi.getBolsa().indexOf(i) ));
		}
		System.out.println("Digite o codigo do equipamento!");
		int cod = s.nextInt();
		try {
			heroi.equiparItem( (EquipItem) heroi.getBolsa().buscarItem(cod));
		}catch (ClassCastException | IndexOutOfBoundsException e) {
			System.out.println(e);
			System.out.println("Codigo informado nao e valido");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Loja lojaYaztromo() {
		Bolsa artigos = new Bolsa();
		Item i = new Item("Pocao de Cura", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Pocao de Controle de Plantas", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("Pocao de Serenidade", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Pocao de Controle de Insetos", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("Antídoto", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("agua benta", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Anel de luz", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Botas Saltitantes", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("Corda de Escalada", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Rede de Aprisionamento", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Bracadeira de forca", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Luva de Arremesso", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("Varinha Localizadora de agua", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("Alho", "Sem descricao", 1, 2);
		artigos.addItem(i);
		i = new Item("Tiara de concentracao", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Capsula de Fogo", "Sem descricao", 1, 3);
		artigos.addItem(i);
		i = new Item("Filtors Nasais", "Sem descricao", 1, 3);
		artigos.addItem(i);
		Loja lYaztromo = new Loja(artigos);
		return lYaztromo;

	}

	public boolean condicaoDeCompra(int preco, Heroi jogador) {
		if (preco <= jogador.getOuro()) {
			return true;
		} else if (preco > jogador.getOuro()) {
			return false;
		}
		return false;
	}

	public void comprar(Heroi jogador, Loja loja)  throws Exception{
		s = new Scanner(System.in);
		int cod;
		Item i = new Item();
		do {
			System.out.println("Bem Vindo a loja  (SALDO: " + jogador.getOuro() + "g)");
			System.out.println("------------------------------");
			System.out.println("         ITENS A VENDA");
			System.out.println("------------------------------");
			for (Item it : loja.getArtigos().listarItens()) {
				System.out.println(it.toString( loja.getArtigos().indexOf(it) ));
			}
			do {
				System.out.println("Qual voce dejesa? (Digite o codigo do Item / 0- Sair da loja)");
				cod = s.nextInt();
				if (cod == 0) {
					System.out.println("Obrigado por comprar conosco!");
					return;
				} else if (cod > 0) {
					i = loja.getArtigos().buscarItem(cod);
				}
			} while (i == null);
			System.out.println("Este e o Item que voce quer?(1- Sim / 2- Nao)\n" + i.getNome() + " - Preco: "
					+ i.getPreco() + "g");
			cod = s.nextInt();
			while (cod < 0 || cod > 2) {
				System.out.println("Opcao invalida");
				cod = s.nextInt();
			}
			switch (cod) {
			case 1:
				if (condicaoDeCompra((i.getPreco() * i.getQuantidade()), jogador)) {
					jogador.getBolsa().addItem(i);
					jogador.setOuro(-(i.getPreco() * i.getQuantidade()));
					loja.getArtigos().removeItem(i);
				} else {
					System.out.println("Saldo insuficiente");
				}
				break;
			case 2:
				cod = 0;
				break;
			default:
				System.out.println(("Opcao Invalida"));
				cod = 0;
				break;
			}
		} while (cod != 0);
		System.out.println("Obrigado por comprar conosco!");
		return;
	}

}
