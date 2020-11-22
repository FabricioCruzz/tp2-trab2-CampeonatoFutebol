package br.edu.univas.main;


import java.util.Scanner;

import br.edu.univas.vo.Jogos;
import br.edu.univas.vo.Times;

public class StartApp {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
	
		int option;
		int alteraRegistro = 0;
		int	aux = 0;
		int qtd = 50;
		
		int contadorTimes = 0;
		int contadorJogos = 0;
		
		Times[] listaTimes = new Times[qtd];		
		Jogos[] listaJogos = new Jogos[qtd];
		Times[] classificacaoCampeonato = new Times[qtd];
		
		do {
			imprimeMenu();
			
			option = scanner.nextInt();
			scanner.nextLine();
			
			if(option == 1) {
				
				cadastroDeTime(listaTimes, contadorTimes);
				
				contadorTimes++;
				
				System.out.print("\n");
			}
			else if(option == 2) {
							
				edicaoTime(listaTimes, alteraRegistro, contadorTimes);
				
			}
			else if(option == 3) {
				
				System.out.println("::::EXCLUSÃO DE TIME:::: \n");
				
				if(contadorTimes <= 0) {
					System.out.println("Nenhum time foi cadastrado ainda! \n\n");
				}
				else {
					
					excluiTime(listaTimes, contadorTimes, alteraRegistro);
					
					reordenaListaDeTimes(listaTimes);
					
					// QUANDO EXCLUIR UM TIME CONTADOR DIMINUI EM 1 PARA SINCRONIZAR COM O NÚMERO DE TIMES EXISTENTES
					contadorTimes--;
				}		
				
				System.out.print("\n");
			}			
			else if(option == 4) {
				
				cadastroDeJogo(listaJogos, listaTimes, contadorJogos, contadorTimes);
	
				contadorJogos++;
				
				System.out.print("\n");
				
			}
			else if(option == 5) {
				
				edicaoDeJogo(listaTimes, listaJogos, aux, contadorTimes, contadorJogos, alteraRegistro);
			
			}
			else if(option == 6) {
				System.out.println("::::EXCLUSÃO DE JOGO:::: \n");
				
				if(contadorJogos == 0) {
					System.out.println("Não existem jogos cadastrados!");
				}
				else {										
					listaJogos = excluiJogos(listaJogos, listaTimes, contadorTimes, contadorJogos, alteraRegistro);	
					
					reordenaListaDeJogos(listaJogos);
		
					// DIMINUI EM 1 O CONTADOR DE PARTIDAS REGISTRADAS QUANDO SE EXCLUI UM JOGO
					contadorJogos--;
					
					System.out.println("Jogo excluído com sucesso \n");
				}	
			}
			else if(option == 7) {
				System.out.println("\n::::TABELA DO CAMPEONATO::::\n");
				
				if(listaTimes[0] == null) {
					System.out.println("Times/Jogos ainda não foram cadastrados ou foram excluídos! \n\n");
				}
				else {
					
					classificacaoCampeonato = criaListaDeClassificação(listaTimes, contadorTimes, qtd);
					
					ordenaListaDeClassificacao(classificacaoCampeonato, contadorTimes);
					
					imprimeTabelaCampeonato(classificacaoCampeonato, contadorTimes);
				}
			}
			
		}while(option != 9);
		
		
		
		
		scanner.close();
	}
	
	
	public static void imprimeMenu() {

		System.out.println("Bem-vindo ao cadastro de Campeonato de Futebol:\n");
		
		System.out.println("Por favor, selecione uma opção:\n");
		
		System.out.println("1 - Cadastrar Time");
		System.out.println("2 - Editar Time");
		System.out.println("3 - Excluir Time");
		System.out.println("4 - Cadastrar Jogo");
		System.out.println("5 - Editar Jogo");
		System.out.println("6 - Excluir Jogo");
		System.out.println("7 - Listar Classificação do Campeonato");
		System.out.println("9 - Sair");
	}
	
	
	public static void cadastroDeTime(Times[] listaDosTimes, int counterTimes) {
		
		System.out.println("::::CADASTRO DE TIME:::: \n");
		System.out.println("Por favor, digite o nome do time:");
		
		Times time = new Times();
		
		time.nome = scanner.nextLine();

		System.out.println("Por favor, digite o estado de origem do time:");
		
		time.estadoOrigem = scanner.nextLine();
		
		
		time.pontos = 0;
		time.saldoGols = 0;
		
		listaDosTimes[counterTimes] = time;
	}
	
	
	public static void imprimeMenuEdicaoTime() {
		System.out.println("Por favor, tecle a opção correspondente ao que você deseja alterar:\n");
		System.out.println("1 - Editar Nome");
		System.out.println("2 - Editar Estado de Origem");
	}
	
	
	public static void imprimeListaTimes(Times[] listaDosTimes, int contadorDosTimes) {
		for(int i = 0; i < contadorDosTimes; i++) {
			System.out.println((i + 1) + " - Nome - " + listaDosTimes[i].nome + " --- Estado de Origem - " + listaDosTimes[i].estadoOrigem);
		}
	}
	
	
	public static void editarNomeDoTime(Times[] listaDosTimes, int optionEditName) {
		
		System.out.println("Por favor, digite o número correspondente ao time que deseja alterar:");
		optionEditName = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Por favor, digite o novo nome do time:");
		listaDosTimes[(optionEditName - 1)].nome = scanner.nextLine();
	}
	
	
	public static void editarEstadoOrigem(Times[] listaDosTimes, int optionEditState) {
		
		System.out.println("Por favor, digite o número correspondente ao estado de origem que deseja alterar:");
		optionEditState = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Por favor, digite o novo nome do estado de origem:");
		listaDosTimes[(optionEditState - 1)].estadoOrigem = scanner.nextLine();	
	}
	
	
	public static void edicaoTime(Times[] listaTimes, int alteradorRegistros, int counterTimes) {
		
		System.out.println("\n::::EDIÇÃO DE TIME::::\n");
		
		if(listaTimes[0] == null) {
			System.out.println("Nenhum time foi cadastrado!\n");
		}
		else {
			imprimeMenuEdicaoTime();
			alteradorRegistros = scanner.nextInt();
			scanner.nextLine();
			
			if(alteradorRegistros == 1) {
				
				System.out.println("Estes são os times cadastrados até agora:\n");
				imprimeListaTimes(listaTimes, counterTimes);
				
				editarNomeDoTime(listaTimes, alteradorRegistros);
			}
			else {
				System.out.println("Escolha a opção correspondente ao time/estado de origem que deseja modificar:\n");
				imprimeListaTimes(listaTimes, counterTimes);
				
				editarEstadoOrigem(listaTimes, alteradorRegistros);
			}
		}
		System.out.print("\n");
	}
	
	
	public static void excluiTime(Times[] listaDosTimes, int counterTimes, int alteradorRegistros) {
		
		System.out.println("Estes foram os times cadastrados até agora: \n");
		imprimeListaTimes(listaDosTimes, counterTimes);
		
		System.out.println("Por favor, digite o número correspondente ao time que deseja excluir:\n");
		alteradorRegistros = scanner.nextInt();
		scanner.nextLine();
		
		
		listaDosTimes[(alteradorRegistros - 1)] = null;
		
	}	
	
	public static void reordenaListaDeTimes(Times[] listaDosTimes) {
		
		boolean excluiuTime;
		do {
			excluiuTime = false;
			for(int i = 0; i < listaDosTimes.length; i++) {
				if(i == listaDosTimes.length - 1) {
					break;
				}
				else if(listaDosTimes[i] == null && listaDosTimes[i + 1] != null) {
					Times auxTime3 = new Times();
					
					auxTime3 = listaDosTimes[i + 1];
					listaDosTimes[i] = auxTime3;
					listaDosTimes[i + 1] = null;
					
					excluiuTime = true;
				}
			}
			
		}while(excluiuTime);
	}
	
	public static void cadastroDeJogo(Jogos[] listaDosJogos, Times[] listaTimes, int counterJogos, int counterTimes) {
		
		System.out.println("::::CADASTRO DE JOGO:::: \n");
		
		Jogos cadastroJogo = new Jogos();
		
		System.out.println("Por favor, informe o time mandante:");
		cadastroJogo.timeMandante = scanner.nextLine();
			
		System.out.println("Por favor, informe o time visitante:");
		cadastroJogo.timeVisitante = scanner.nextLine();
		
		System.out.println("Por favor, informe o número de gols do time mandante:");
		cadastroJogo.golsMandante = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Por favor, informe o número de gols do time visitante:");
		cadastroJogo.golsVisitante = scanner.nextInt();
		scanner.nextLine();
		
		verificaVencedor(cadastroJogo, listaTimes, counterTimes);
		
		listaDosJogos[counterJogos] = cadastroJogo;
		
	}
	
	public static void verificaVencedor(Jogos partida, Times[] listaDosTimes, int counterTimes) {
		
		int indice;
		
		//VERIFICA VENCEDORES E PERDEDORES DO JOGO REGISTRADO E FAZ O CALCULO DOS PONTOS E DO SALDO DE GOLS.
		//VITÓRIA MANDANTE
		if(partida.golsMandante > partida.golsVisitante) {
			
			// BUSCANDO TIME MANDANTE
			indice = buscaTimeNaListaDeTimes(partida.timeMandante, listaDosTimes, counterTimes);			
			listaDosTimes[indice].pontos += 3;
			listaDosTimes[indice].saldoGols += (partida.golsMandante - partida.golsVisitante);
			
			
			// BUSCANDO TIME VISITANTE
			indice = buscaTimeNaListaDeTimes(partida.timeVisitante, listaDosTimes, counterTimes);	
			listaDosTimes[indice].pontos += 0;
			listaDosTimes[indice].saldoGols += (partida.golsVisitante - partida.golsMandante);	
			
		}
		
		//EMPATE
		else if(partida.golsMandante == partida.golsVisitante) {
			
			// BUSCANDO TIME MANDANTE
			indice = buscaTimeNaListaDeTimes(partida.timeMandante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos += 1;
			listaDosTimes[indice].saldoGols += 0;
			
			// BUSCANDO TIME VISITANTE
			indice = buscaTimeNaListaDeTimes(partida.timeVisitante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos += 1;
			listaDosTimes[indice].saldoGols += 0;
			
		}
		
		//DERROTA MANDANTE
		else {
			// BUSCANDO TIME MANDANTE
			indice = buscaTimeNaListaDeTimes(partida.timeMandante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos += 0;
			listaDosTimes[indice].saldoGols += (partida.golsMandante - partida.golsVisitante);
			
			
			// BUSCA TIME VISITANTE
			indice = buscaTimeNaListaDeTimes(partida.timeVisitante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos += 3;
			listaDosTimes[indice].saldoGols += (partida.golsVisitante - partida.golsMandante);
		}
		
	}
	
	public static int buscaTimeNaListaDeTimes(String nomeTime, Times[] listaDosTimes, int counterTimes) {
		
		int idx = 0;
		
		for(int i = 0; i < counterTimes; i++) {
			if(nomeTime.equals(listaDosTimes[i].nome)) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	public static void edicaoDeJogo(Times[] listaDosTimes, Jogos[] listaDosJogos, int auxiliar, int counterTimes, int counterJogos, int alteradorRegistros) {
		
		System.out.println("::::EDIÇÃO DE JOGO::: \n");
		
		if(counterJogos == 0) {
			System.out.println("Não existem jogos cadastrados! \n");
		}
		
		else {
			
			imprimeListaDeJogos(listaDosJogos, counterJogos);
			
			System.out.println("Informe o número correspondente a qual partida deseja alterar:");
			auxiliar = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Você escolheu a seguinte partida:\n");
			
			System.out.println("Partida número " + auxiliar + " - " + listaDosJogos[auxiliar - 1].timeMandante + " " + listaDosJogos[auxiliar - 1].golsMandante + " X " + listaDosJogos[auxiliar - 1] .golsVisitante + " " + listaDosJogos[auxiliar - 1].timeVisitante);
			
			Jogos alteraJogo = new Jogos();
			alteraJogo = copiaDadosDaListaDeJogos(listaDosJogos, auxiliar);
			
			
			
			// DESCOBRE QUAL O VENCEDOR OU SE DEU EMPATE A PARTIDA JÁ REGISTRADA
			boolean mandanteVencedor = false;
			boolean visitanteVencedor = false;
			boolean empate = false;
			
			if(alteraJogo.golsMandante > alteraJogo.golsVisitante) {
				mandanteVencedor = true;
			}
			else if(alteraJogo.golsMandante == alteraJogo.golsVisitante) {
				empate = true;
			}
			else {
				visitanteVencedor = true;
			}
			
			imprimeMenuEdicaoJogo();
			alteradorRegistros = scanner.nextInt();
			scanner.nextLine();				
			
			if(alteradorRegistros == 1) {
				System.out.println("Por favor, informe o nome do time mandante:");
				
				listaDosJogos[auxiliar - 1].timeMandante = editaNomesDosTimesNosJogos(listaDosTimes, counterTimes, alteradorRegistros, alteraJogo, mandanteVencedor, visitanteVencedor, empate);
				
				System.out.println("Nome alterado com sucesso!");			
			}
			else if(alteradorRegistros == 2) {
				System.out.println("Por favor, informe o nome do time visitante:");
				
				listaDosJogos[auxiliar - 1].timeVisitante = editaNomesDosTimesNosJogos(listaDosTimes, counterTimes, alteradorRegistros, alteraJogo, mandanteVencedor, visitanteVencedor, empate);
				
				System.out.println("Nome alterado com sucesso!");
			}
			else if(alteradorRegistros == 3) {
				System.out.println("Por favor, digite o número de gols do time mandante:");
		
				listaDosJogos[(auxiliar - 1)].golsMandante = alteraGols(listaDosTimes, counterTimes, alteradorRegistros, alteraJogo,mandanteVencedor, visitanteVencedor, empate);
				
				System.out.println("Gols alterados com sucesso!");
			
			}
			else {
				System.out.println("Por favor, digite o número de gols do time visitante:");

				listaDosJogos[(auxiliar - 1)].golsVisitante = alteraGols(listaDosTimes, counterTimes, alteradorRegistros, alteraJogo,mandanteVencedor, visitanteVencedor, empate);;
				
				System.out.println("Gols alterados com sucesso!");
			}
		}
		
		System.out.println("\n");
		
	}
	
	public static void imprimeListaDeJogos(Jogos[] listaDosJogos, int counterJogos) {
		
		System.out.println("Essas são as partidas registradas até agora...\n");
		for(int i = 0; i < counterJogos; i++) {
			System.out.println("Partida número " + (i + 1) + " - " + listaDosJogos[i].timeMandante + " " + listaDosJogos[i].golsMandante + " X " + listaDosJogos[i].golsVisitante + " " + listaDosJogos[i].timeVisitante);
		}
		
	}
	
	public static Jogos copiaDadosDaListaDeJogos(Jogos[] listaDosJogos, int auxiliar) {
		
		Jogos copiaJogos = new Jogos();
		
		copiaJogos.timeMandante = listaDosJogos[auxiliar - 1].timeMandante;
		copiaJogos.timeVisitante = listaDosJogos[auxiliar - 1].timeVisitante;
		copiaJogos.golsMandante = listaDosJogos[auxiliar - 1].golsMandante;
		copiaJogos.golsVisitante = listaDosJogos[auxiliar - 1].golsVisitante;
		
		return copiaJogos;
	}
	
	public static void imprimeMenuEdicaoJogo() {
		
		System.out.println("Informe qual elemento da partida deseja alterar:");
		System.out.println("1 - Time Mandante");
		System.out.println("2 - Time Visitante");
		System.out.println("3 - Gols Time Mandante");
		System.out.println("4 - Gols Time Visitante");
	}
	
	public static String editaNomesDosTimesNosJogos(Times[] listaDosTimes, int counterTimes, int alteradorRegistros, Jogos alteraJogo, boolean mandanteWinner, boolean visitanteWinner, boolean draw) {
		
		int indice;
		String mudaNome;

		mudaNome = scanner.nextLine();
		
		// CASO TIME MANDANTE SEJA O VENCEDOR
		if(mandanteWinner) {
			
			if(alteradorRegistros == 1) {
				// RETIRA OS PONTOS E SALDO DE GOLS ANTERIORMENTE REGISTRADOS NO TIME A SER MUDADO
				indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);						
				
				listaDosTimes[indice].pontos -= 3;
				resetaPontos(listaDosTimes, indice);
				
				listaDosTimes[indice].saldoGols -= (alteraJogo.golsMandante - alteraJogo.golsVisitante);
				
				
				// CONTABLIZA OS PONTOS E SALDO DE GOLS PARA O NOVO TIME
				indice = buscaTimeNaListaDeTimes(mudaNome, listaDosTimes, counterTimes);
				
				listaDosTimes[indice].pontos += 3;
				listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - alteraJogo.golsVisitante);
			}
			else {
				// RETIRA OS PONTOS E SALDO DE GOLS ANTERIORMENTE REGISTRADOS NO TIME A SER MUDADO
				indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
	
				listaDosTimes[indice].saldoGols -= (alteraJogo.golsVisitante - alteraJogo.golsMandante);
		
						
				// CONTABLIZA OS PONTOS E SALDO DE GOLS PARA O NOVO TIME
				indice = buscaTimeNaListaDeTimes(mudaNome, listaDosTimes, counterTimes);
						
				listaDosTimes[indice].pontos -= 3;
				resetaPontos(listaDosTimes, indice);
				listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - alteraJogo.golsMandante);
			}
		}
		
		//EMPATE
		else if(draw) {
			
			// ALTERA NOME DO MANDANTE
			if(alteradorRegistros == 1) {			
				indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
				
				listaDosTimes[indice].pontos -= 1;
				resetaPontos(listaDosTimes, indice);
			}
			// ALTERA NOME DO VISITANTE
			else {
				indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
				
				listaDosTimes[indice].pontos -= 1;
				resetaPontos(listaDosTimes, indice);
				
			}
			
			indice = buscaTimeNaListaDeTimes(mudaNome, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos += 1;	
		}
		
		//DERROTA MANDANTE
		else {
			// ALTERA NOME DO MANDANTE
			if(alteradorRegistros == 1) {
				indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
				listaDosTimes[indice].saldoGols -= (alteraJogo.golsVisitante - alteraJogo.golsMandante);			
			}
			// ALTERA NOME DO VISITANTE
			else {							
				
				indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
				
				listaDosTimes[indice].pontos -= 3;
				listaDosTimes[indice].saldoGols -= (alteraJogo.golsVisitante - alteraJogo.golsMandante);				
				
				indice = buscaTimeNaListaDeTimes(mudaNome, listaDosTimes, counterTimes);
				listaDosTimes[indice].pontos += 3;				
			}
			listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - alteraJogo.golsMandante);
		}
		
		
		return mudaNome;
	}
	
	public static int alteraGols(Times[] listaDosTimes, int counterTimes, int alteradorRegistros, Jogos alteraJogo, boolean mandanteWinner, boolean visitanteWinner, boolean draw) {
		
		int mudaGols;
		
		int gols;
		
		if(alteradorRegistros == 3) {
			gols = alteraJogo.golsVisitante;
		}
		else {
			gols = alteraJogo.golsMandante;
		}
		
		
		mudaGols = scanner.nextInt();
		scanner.nextLine();
		
		int indice;
		
		if(mudaGols > gols) {
			
			// TIME MANDANTE JÁ ERA VENCEDOR SOMENTE ALTEROU SALDO DE GOLS
			if(mandanteWinner) {
				if(alteradorRegistros == 3) {
					
					// CORRIGE SALDO DE GOLS DO MANDANTE APÓS MUDANÇA
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
					listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
					
					// CORRIGE SALDO DE GOLS DO VISITANTE APÓS A MUDANÇA
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
					listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
				}
				else {
					
					// CORRIGE DADOS MANDANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos -= 3;
					resetaPontos(listaDosTimes, indice);
					listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
					
					// CORRIGE DADOS VISITANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos += 3;
					// CORRIGE SALDO DE GOLS DO VISITANTE APÓS A MUDANÇA
					listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante);
				}
			}
			
			// TIME VISITANTE ERA O VENCEDOR E AGORA COM O NOVO RESULTADO SE TORNA PERDEDOR
			else if(visitanteWinner) {
				
				if(alteradorRegistros == 3) {
					
					// CORRIGE DADOS MANDANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos += 3;
					listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
					
					// CORRIGE DADOS VISITANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos -= 3;
					resetaPontos(listaDosTimes, indice);
					listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
				}
				else {					
					
					// CORRIGE DADOS MANDANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
					listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
					
					// CORRIGE DADOS VISITANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
					listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante);
				}
			}
			else {
				if(alteradorRegistros == 3) {
					
					// CORRIGE DADOS MANDANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos += 2;
					listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
					
					// CORRIGE DADOS VISITANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos -= 1;
					resetaPontos(listaDosTimes, indice);
					listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
				}
				else {
					
					// CORRIGE DADOS MANDANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos -= 1;
					resetaPontos(listaDosTimes, indice);
					listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
					
					// CORRIGE DADOS VISITANTE
					indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
					listaDosTimes[indice].pontos += 2;
					listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante); 
				}
			}
		}
		
		// PARTIDA COM A MUDANÇA SE TORNA EMPATE
		else if(mudaGols == gols) {
				if(mandanteWinner) {
					if(alteradorRegistros == 3) {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 2;
						resetaPontos(listaDosTimes, indice);
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 1;
						listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
					}
					else {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 2;
						resetaPontos(listaDosTimes, indice);
						listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 1;
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante);
					
					}
				}
				
				// TIME VISITANTE ERA O VENCEDOR E AGORA COM O NOVO RESULTADO SE TORNA EMPATE
				else if(visitanteWinner) {
					if(alteradorRegistros == 3) {

						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 1;
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 2;
						resetaPontos(listaDosTimes, indice);
						listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
					}
					else {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 1;
						listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 2;
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante);
					}
				}
			}
		
		else { // mudaGols < gols
			
				// MANDANTE ERA GANHADOR E PASSA A SER O PERDEDOR
				if(mandanteWinner) {
					if(alteradorRegistros == 3) {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 3;
						resetaPontos(listaDosTimes, indice);
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 3;
						listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
					}
					else {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante);
					}
				}
				
				// TIME VISITANTE CONTINUA SENDO O VENCEDOR ENTÃO SOMENTE SE ALTERA O SALDO DE GOLS
				else if(visitanteWinner) {
					if(alteradorRegistros == 3) {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
					}
				}
				
				else {
					if(alteradorRegistros == 3) {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 1;
						resetaPontos(listaDosTimes, indice);
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsMandante);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 2;
						listaDosTimes[indice].saldoGols += (alteraJogo.golsMandante - mudaGols);
					}
					else {
						
						// CORRIGE DADOS MANDANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeMandante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos += 2;
						listaDosTimes[indice].saldoGols += (alteraJogo.golsVisitante - mudaGols);
						
						// CORRIGE DADOS VISITANTE
						indice = buscaTimeNaListaDeTimes(alteraJogo.timeVisitante, listaDosTimes, counterTimes);
						listaDosTimes[indice].pontos -= 1;
						resetaPontos(listaDosTimes, indice);
						listaDosTimes[indice].saldoGols += (mudaGols - alteraJogo.golsVisitante);
					}
				}
			}
		
		return mudaGols;
	}
	
	public static void resetaPontos(Times[] listaDosTimes, int indice) {
		
		if(listaDosTimes[indice].pontos < 0) {
			listaDosTimes[indice].pontos = 0;
		}
		
	}
	
	public static void exclusaoDeJogos() {
		
	}
	
	public static Jogos[] excluiJogos(Jogos[] listaDosJogos, Times[] listaDosTimes, int counterTimes, int counterJogos, int alteradorRegistros) {
		System.out.println("Essas são as partidas registradas até agora...\n");
					
		imprimeListaDeJogos(listaDosJogos, counterJogos);
		
		System.out.println("\n Informe o número correspondente a qual partida deseja excluir:");
		alteradorRegistros = scanner.nextInt();
		scanner.nextLine();
					
		Jogos excluiJogo = new Jogos();
		excluiJogo = copiaDadosDaListaDeJogos(listaDosJogos, alteradorRegistros);
					
		resetaDadosAntesDaExclusao(listaDosTimes, excluiJogo, counterTimes);
					
		listaDosJogos[alteradorRegistros - 1] = null;
		
		return listaDosJogos;
	}
	
	public static void resetaDadosAntesDaExclusao(Times[] listaDosTimes, Jogos dadosJogo, int counterTimes) {
		
		int indice;
		
		if(dadosJogo.golsMandante > dadosJogo.golsVisitante) {
			
			// BUSCA MANDANTE
			indice = buscaTimeNaListaDeTimes(dadosJogo.timeMandante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos -= 3;
			resetaPontos(listaDosTimes, indice);
			listaDosTimes[indice].saldoGols -= (dadosJogo.golsMandante - dadosJogo.golsVisitante);
			
			// BUSCA VISITANTE
			indice = buscaTimeNaListaDeTimes(dadosJogo.timeVisitante, listaDosTimes, counterTimes);
			listaDosTimes[indice].saldoGols += (dadosJogo.golsMandante - dadosJogo.golsVisitante);

		}
		else if(dadosJogo.golsMandante == dadosJogo.golsVisitante) {
			
			// BUSCA MANDANTE
			indice = buscaTimeNaListaDeTimes(dadosJogo.timeMandante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos -= 1;
			resetaPontos(listaDosTimes, indice);
			
			// BUSCA VISITANTE
			indice = buscaTimeNaListaDeTimes(dadosJogo.timeVisitante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos -= 1;
			resetaPontos(listaDosTimes, indice);
		}
		else {
			
			// BUSCA VISITANTE
			indice = buscaTimeNaListaDeTimes(dadosJogo.timeVisitante, listaDosTimes, counterTimes);
			listaDosTimes[indice].pontos -= 3;
			resetaPontos(listaDosTimes, indice);
			listaDosTimes[indice].saldoGols -= (dadosJogo.golsVisitante - dadosJogo.golsMandante);
			
			// BUSCA MANDANTE
			indice = buscaTimeNaListaDeTimes(dadosJogo.timeMandante, listaDosTimes, counterTimes);
			listaDosTimes[indice].saldoGols += (dadosJogo.golsVisitante - dadosJogo.golsMandante);
		}
	}
	
	public static void reordenaListaDeJogos(Jogos[] listaDosJogos) {
		
		//REORDENAR VETOR DE PARTIDAS			
		boolean excluiuJogo;
		do {
			excluiuJogo = false;
			for(int i = 0; i < listaDosJogos.length; i++) {
				if(i == listaDosJogos.length - 1) {
					break;
				}
				else if(listaDosJogos[i] == null && listaDosJogos[i + 1] != null) {
					Jogos auxJogo = new Jogos();
					auxJogo = listaDosJogos[i + 1];
					listaDosJogos[i] = auxJogo;
					listaDosJogos[i + 1] = null;
			
					excluiuJogo = true;
				}
			}		
		}while(excluiuJogo);	
	}
	
	
	public static Times[] criaListaDeClassificação(Times[] listaDosTimes, int counterTimes, int tamanho) {
		
		Times[] vetorClassificacao = new Times[tamanho];
		
		// PREENCHE VETOR PARA LISTAR CLASSIFICAÇÃO
		for(int i = 0; i < counterTimes; i ++) {
			Times timeClassificacao = new Times();
		
			timeClassificacao.nome = listaDosTimes[i].nome;
			timeClassificacao.estadoOrigem = listaDosTimes[i].estadoOrigem;
			timeClassificacao.pontos = listaDosTimes[i].pontos;
			timeClassificacao.saldoGols = listaDosTimes[i].saldoGols;
			
			vetorClassificacao[i] = timeClassificacao;
		}
		return vetorClassificacao;
	}
	
	public static void ordenaListaDeClassificacao(Times[] tabelaCamp, int counterTimes) {
		
		boolean swap;
		do {
			swap = false;
			for(int i = 0; i < counterTimes; i++) {
				if(tabelaCamp[i + 1] == null) {
					break;
				}
				else if(tabelaCamp[i].pontos < tabelaCamp[i + 1].pontos) {
					Times auxTime = new Times();
					
					auxTime = tabelaCamp[i];
					tabelaCamp[i] = tabelaCamp[i + 1];
					tabelaCamp[i + 1] = auxTime;
					
					swap = true;
				}
				else if(tabelaCamp[i].pontos == tabelaCamp[i + 1].pontos) {
					if (tabelaCamp[i].saldoGols < tabelaCamp[i + 1].saldoGols) {
						Times auxTime2 = new Times();
						
						auxTime2 = tabelaCamp[i];
						tabelaCamp[i] = tabelaCamp[i + 1];
						tabelaCamp[i + 1] = auxTime2;
						
						swap = true;
					}
				}
			}
			
		}while(swap);
	}
	
	public static void imprimeTabelaCampeonato(Times[] tabelaCamp, int counterTimes) {
		
		// IMPRIME A CLASSIFICAÇÃO
		for(int i = 0; i < counterTimes; i ++) {
			System.out.println((i + 1) + "º - " + tabelaCamp[i].nome + " - Pontos: " + tabelaCamp[i].pontos + " -- Saldo de Gols: " + tabelaCamp[i].saldoGols);
		}
		System.out.print("\n");
		
	}
}
