package programa;
import java.util.*;

import beans.ConsumivelItem;
import beans.Heroi;
import beans.Item;
import beans.Livro;
import beans.Loja;
import arquivo.Arquivo;
import programa.FachadaLivroJogo;

public class Aventura {

	private static Scanner s;
	//private static Random dado;
	private static Livro livro;
	private static Heroi h;
	private static int opcao;
	//private static String texTemp;
	private static Loja loja;
	private static FachadaLivroJogo mundo;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mundo = FachadaLivroJogo.getInstancia();
		new Arquivo();
		s = new Scanner(System.in);
		//texTemp = new String();
		
		
		
		System.out.println("Bem vindo ao sistema Livro-Jogo\n"
				+ "uma ferramenta que veio auxiliar sua leitura!\n");
		
		System.out.println("Escolha sua op��o:\n1- Novo Jogo\n2- Continuar");
		opcao = s.nextInt();
		while (opcao<1 || opcao>3) {
		System.out.println("Op��o inv�lida! Digite novamente!");
			opcao = s.nextInt();
		}
		//do{
			switch (opcao) {
			case 1:
				String nomeLivro, nomeHeroi;
				int opc;
				s.nextLine();
				System.out.println("Nome do Livro:");
				nomeLivro = s.nextLine();
				System.out.println("Nome do Heroi:");
				nomeHeroi = s.nextLine();
				System.out.println("Opcao:");
				opc = s.nextInt();
				livro = mundo.novoJogo(nomeLivro, nomeHeroi, opc);
				h = livro.getJogador();
				break;
			case 2:
				String[][] files = Arquivo.dadosArquivos();
				for (String[] s : files) {
					System.out.println(s[0]+ " "+s[1]+ " "+s[2]);
				}
				int e = s.nextInt();
				if (e >= files.length) {
					e = 0;
				}
				livro = Arquivo.lerArquivo(files[e][3]);
				h = livro.getJogador();
				break;
			}
		//}while(opcao = = 2);
		loja = mundo.lojaYaztromo();
		do{
			System.out.println("Ol�! "+ h.getNome()+ " Eu sou o seu assistente de Aventura.\n- Em que posso ajudar?(Ultima posi��o salva: "+ livro.getPosicao()+")");
			System.out.println("1- Gerar Item?\n2- Listar Itens\n3- Loja Yaztromo\n4- Batalhar\n5- Mostrar Personagem\n6- Salvar Jogo\n7- Testar Sorte\n8- Usar Po��o/Provis�o\n9- Equipamentos"
					+ "\n10- Usar Ouro\n11- Sair");
			opcao = s.nextInt();
			while(opcao<1||opcao>11){
				System.out.println("Opc�o In v�lida! Digite novamente");
				opcao = s.nextInt();
			}
			
			switch (opcao) {
			case 1:
				Item i;
				i = mundo.gerarItem();
				h.getBolsa().addItem(i);
				break;
			case 2:
				System.out.println("--------------- ITENS -------------------");
				for (Item e : h.getBolsa().listarItens()) {
					System.out.println(e.toString( h.getBolsa().indexOf(e) ));
				}
				break;
			case 3:
				try{
				mundo.comprar(h, loja);
				}catch(Exception msg){
					System.out.println(msg);
				}
				break;
			case 4:
				mundo.batalha(h, null, null);
				break;
			case 5:
				System.out.println(h);
				break;
			case 6:
				System.out.println("Digite a posi��o atual:");
				livro.setPosicao(s.nextInt());
				Arquivo.salvaArquivo(livro, null);
				break;
			case 7:
				mundo.usarSorte(h);
				break;
			case 8:
				try{
				for (Item e : h.getBolsa().listarItens()) {
					if (e instanceof ConsumivelItem)
						System.out.println(e.toString( h.getBolsa().indexOf(e) ));
				}
				mundo.usarPocao(h);
				}catch(Exception msg){
					msg.printStackTrace();
					System.out.println(msg);
				}
				break;
			case 9:
				try{
				mundo.equiparItem(h);
				}catch(Exception msg){
					System.out.println(msg);
				}
				break;
			case 10:
				System.out.println("O quanto de ouro voc� recebeu/doou?");
				h.setOuro(s.nextInt());
				break;
			case 11:
				System.out.println("Deseja salvar antes de ir?(1- Sim / 2- N�o)");
				if (s.nextInt()==1) {
					System.out.println("Digite a posi��o atual:");
					livro.setPosicao(s.nextInt());
					Arquivo.salvaArquivo(livro, null);
					System.out.println("Obrigado por usar o programa!");
					System.exit(0);
				} else {
					System.out.println("Obrigado por usar o programa!");
					System.exit(0);
				}
				break;
			default:
				System.out.println("Op��o inv�lida");
		}
		
		}while(h.getEnergiaAtual()>0);
		
	}

}
