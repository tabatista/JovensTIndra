package com.indra.treinamento.java.controladores;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.indra.treinamento.java.dao.*;
import com.indra.treinamento.java.entidades.*;

public class ComprarCarro {

	public static void main(String[] args) {

		/**
		 * Parte de Login
		 */

		// para ler a entrada de dados do teclado do usuário
		Scanner tec = new Scanner(System.in);
		GravadorArquivo criadorLogin = new GravadorArquivo();

		// para gerar dados como lista de carros e de vendas
		GeradorDados gerador = new GeradorDados();

		// verificando existência do arquivo de logins
		File arquivo = new File("D:/cadastrosGeral.csv");

		if (!arquivo.exists()) {

			try {
				criadorLogin.abrirArquivo("D:/cadastrosGeral.csv");
				criadorLogin.fecharArquivo();
			} catch (IOException e) {

				System.err.println("Erro ao abrir arquivo de login!");
			}

		}

		System.out.println("*******SISTEMA DE VENDAS DE CARROS*******");

		// para armazenar os logins <Codigo de Login / Tipo de Usuario>
		HashMap<Integer, String> mapLogin = null;
		LeitorArquivo leitorLogin = new LeitorArquivo();

		try {
			leitorLogin.abrirArquivo("D:/cadastrosGeral.csv");
			mapLogin = leitorLogin.lerCadastrosGerais();
			leitorLogin.fecharArquivo();

		} catch (IOException e) {
			System.err.println("Erro ao tentar ler Cadastros");
		}

		// para autenticar o login
		AcessLogin login = new AcessLogin();

		int codigo = 0, opcao = 0;
		String tipoAcesso = null; // para recuperar Dados do usuario após login

		// para controlar o menu de login
		boolean ok = false;

		do {

			System.out.println("O que deseja fazer: (digite o número correspondente)");
			System.out.println("1 - Logar\n2 - Cadastrar\n3 - Sair do Sistema");
			opcao = new Integer(tec.nextInt());

			switch (opcao) {

			// parte de login
			case 1:

				System.out.print("Código de acesso: ");
				codigo = new Integer(tec.nextInt());

				if (login.autenticaLogin(mapLogin, codigo)) {
					tipoAcesso = mapLogin.get(codigo);
					ok = true;

				} else {
					System.out.println("\nCódigo inválido! Tente novamente ou se cadastre\n");
				}

				break;

			// parte de cadastro
			case 2:
				System.out.println("Você é um: (digite o número correspondente)");
				login.imprimeTiposUsuario();
				int tipoUsuario = tec.nextInt();

				int codigoGerado = login.gerarCodigo();

				while (login.temCadastro(mapLogin, codigoGerado)) {
					codigoGerado = login.gerarCodigo();

				}

				// criando usuario para o cadastro
				Usuario novoUsuario = new Usuario(codigoGerado, login.tipoUsuario(tipoUsuario));

				// imputando cadastro no arquivo, para não precisar reescanear o
				// arquivo geral de cadastro
				mapLogin.put(codigoGerado, login.tipoUsuario(tipoUsuario));

				// cadastro de cliente
				if (tipoUsuario == 1) {

					Cliente cliente = new Cliente();
					cliente.setUsuario(novoUsuario);

					System.out.print("Qual seu nome: ");
					cliente.setNome(tec.next() + tec.nextLine());

					System.out.print("Qual seu telefone (apenas números): ");
					cliente.setTelefone(tec.nextInt());

					System.out.print("Qual seu CPF (apenas números): ");
					cliente.setCpf(tec.nextInt());

					System.out.print("Qual seu endereço (completo): ");
					cliente.setEndereço(tec.next() + tec.nextLine());

					try {
						login.cadastrarUsuario(cliente);

						System.out
								.println("ATENÇÃO: SEU CÓDIGO DE ACESSO É: " + cliente.getUsuario().getCodigoAcesso());
						System.out.println("ANOTE PARA ACESSAR, SE PERDER O CÓGIDO, TERÁ DE REALIZAR OUTRO CADASTRO");

					} catch (Exception e) {
						// try-catch apenas para não divulgação do código caso
						// haja erro
					}
					System.out.println("Pressione qualquer tecla + enter para continuar...");
					tec.next();

					// cadastro de funcionario (geral)
				} else {

					Funcionario funcionario = null;
					if (tipoUsuario == 2) {

						funcionario = new Vendedor();

					} else if (tipoUsuario == 3) {

						funcionario = new Gerente();
					}

					funcionario.setUsuario(novoUsuario);

					System.out.print("Qual seu nome: ");
					funcionario.setNome(tec.next() + tec.nextLine());

					System.out.print("Qual seu telefone (apenas números): ");
					funcionario.setTelefone(tec.nextInt());

					System.out.print("Qual seu CPF (apenas números): ");
					funcionario.setCpf(tec.nextInt());

					try {
						login.cadastrarUsuario(funcionario);

						System.out.println(
								"ATENÇÃO: SEU CÓDIGO DE ACESSO É: " + funcionario.getUsuario().getCodigoAcesso());
						System.out.println("ANOTE PARA ACESSAR, SE PERDER O CÓGIDO, TERÁ DE REALIZAR OUTRO CADASTRO");

					} catch (Exception e) {
						// try-catch apenas para não divulgação do código caso
						// haja erro
					}

					System.out.println("Pressione qualquer tecla + enter para continuar...");
					tec.next();

				}

				break;

			case 3:

				System.out.println("Saindo do sistema...");
				System.exit(0);

				break;

			default:

				System.out.println("Opção inacessível! Tente novamente.\n");
				break;
			}

			// enquanto não estiver okay, de acordo com específicações - deixei
			// apenas para o login
		} while (!ok);

		System.out.println("Logando...");

		/**
		 * Parte da compra e cadastro do carro, etc
		 */
		LeitorArquivo leitorUsuario = null;

		// criando menu para o cliente
		if (tipoAcesso.equals("Cliente")) {

			Cliente clienteLogado = null;
			leitorUsuario = new LeitorArquivo();

			try {
				leitorUsuario.abrirArquivo("D:/cadastrosCliente.csv");
				clienteLogado = leitorUsuario.lerCliente(codigo);
				leitorUsuario.fecharArquivo();

			} catch (IOException e) {
				System.err.println("Erro ao ler dados do cliente");
			}

			System.out.println("\nOlá, " + clienteLogado.getNome().toUpperCase());
			int opcao2 = 0; // para ficar no menu

			do {
				System.out.println("Você deseja (digite o número correspondente): ");
				System.out.println("1 - Comprar um carro\n2 - Sair");
				opcao2 = new Integer(tec.nextInt());

				if (opcao2 == 1) {

					Venda venda = new Venda();
					venda.setCliente(clienteLogado);

					System.out.println("Sua compra está sendo realizada por meio (digite o número correspondente): ");
					System.out.println("1 - Da Internet\n2 - De um Vendedor\n3 - De um gerente");
					int meioVenda = tec.nextInt();

					switch (meioVenda) {
					case 1:

						venda.setMeioVenda("Internet");
						break;

					case 2:

						venda.setMeioVenda("Vendedor");
						break;

					case 3:

						venda.setMeioVenda("Gerente");
						break;

					default:

						venda.setMeioVenda("Internet");
						break;
					}

					System.out.println(
							"\nDigite qualquer caracter e aperte enter para gerar a lista de Carros disponíveis: ");
					tec.next();

					ArrayList<Carro> listaCarro = null;

					try {
						listaCarro = gerador.listarCarros();

					} catch (NumberFormatException e) {
						System.out.println("Nenhum carro disponível!");
						System.out.println("Saindo do programa...");
						System.exit(2);

					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
					try {
						for (Carro carro : listaCarro) {

							carro.imprimeCarro();
							System.out.println(""); // para dar um espaço

							System.out.println(
									"Deseja algum carro da lista? (Digite o número correspondente)\n1 - Sim\n2 - Não");
							int desejo = tec.nextInt();

							if (desejo == 2) {
								System.out.println("Saindo do programa...");
								System.exit(0);
							}

							boolean certeza = false;

							Carro carroEscolhido = null;
							do {
								System.out.print("Qual carro deseja? (Digite o código correspondente): ");
								int codigoCarro = tec.nextInt();

								carroEscolhido = gerador.consultarCarro(codigoCarro);

								System.out.println("Carro escolhido: " + carroEscolhido.getModelo());
								System.out.println("Cor: " + carroEscolhido.getCor());

								System.out.print("Tem certeza que quer esse carro? digite 'true' ou 'false' ");
								certeza = tec.nextBoolean();

							} while (!certeza);

							venda.setCarro(carroEscolhido.getModelo());
							venda.setValorTransacao(carroEscolhido.getPreco());

							System.out.println("Qual a forma de pagamento? ");
							System.out.println(venda.imprimeFormasPagamento());
							venda.defineFormaPagamento(tec.nextInt());

							System.out.println("Em quantas vezes? (digite)");
							int vezes = tec.nextInt();

							System.out.println(
									"*****Cliente digita seus dados após escolher tipo de pagamento e o sistema autentica*****");

							System.out.printf("%d x de %.2f, sem juros\n", vezes, (carroEscolhido.getPreco() / vezes));

							venda.setQuantidadeParcelas(vezes);

							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							Date date = new Date();
							String data = dateFormat.format(date);

							venda.setDataTransacao(data);

							System.out.println("Compra realizada com sucesso!");

							gerador.cadastrarVenda(venda);
						}
					} catch (NullPointerException e) {

						System.out.println("Nenhum carro disponível!");
						System.out.println("Saindo do programa...");
						System.exit(2);

					}
				} else if (opcao2 == 2) {
					System.out.println("Saindo...");
					System.exit(0);

				} else {
					System.out.println("Opção inacessível! Tente novamente");

				}

			}

			while (opcao2 != 2);

			// criando menu para o funcionario
		} else {

			// acessando dados do funcionario
			Funcionario funcionarioLogado = null;
			leitorUsuario = new LeitorArquivo();

			try {
				leitorUsuario.abrirArquivo("D:/cadastrosFuncionario.csv");
				funcionarioLogado = leitorUsuario.lerFuncionario(codigo);
				leitorUsuario.fecharArquivo();

			} catch (IOException e) {
				System.err.println("Erro ao ler dados do funcionário");
			}

			System.out.println("Olá, " + funcionarioLogado.getNome().toUpperCase());

			int opcao3 = 0;
			do {

				System.out.println("\nO que deseja fazer? (digite o número correspondente)");

				// obs: essa parte de venda vai ficar "jogada", por conta do
				// tempo
				System.out.println("1 - Sair do Sistema\n2 - Registrar a venda de um carro\n3 - Cadastrar Carro");
				if (tipoAcesso.equals("Gerente")) {
					System.out.println("4 - Exibir vendas efetuadas");
				}

				opcao3 = new Integer(tec.nextInt());

				switch (opcao3) {

				// sair do programa
				case 1:

					System.out.println("Saindo do programa...");
					System.exit(0);
					break;

				// registrando venda
				case 2:

					System.out.println("Valor da venda: ");
					// setSalario modificado de acordo com a classe para o
					// acréscimo do
					// salário em % para cada venda
					funcionarioLogado.setSalario(tec.nextDouble());
					System.out.printf("Salário alterado! Valor total disponível: R$%.2f",
							funcionarioLogado.getSalario());

					System.out.println("\nPressione qualquer tecla + enter para continuar...");
					tec.next();

					break;
				//
				// Cadastrar carro
				//
				case 3:

					// fazendo essa gambiarra para acrescentar o código do
					// carro... (banco de dados faz falta)
					ArrayList<Carro> codigoCarro = null;

					try {
						codigoCarro = gerador.listarCarros();

					} catch (IOException e) {
						// System.err.println(e.getMessage());
					}

					Carro carroNovo = new Carro();

					System.out.print("Placa: ");
					carroNovo.setPlaca(tec.next() + tec.nextLine());

					System.out.print("Modelo: ");
					carroNovo.setModelo(tec.next() + tec.nextLine());

					System.out.print("Ano: ");
					carroNovo.setAno(tec.nextInt());

					System.out.print("Cor: ");
					carroNovo.setCor(tec.next() + tec.nextLine());

					System.out.print("Marca: ");
					carroNovo.setMarca(tec.next() + tec.nextLine());

					System.out.print("Preço: ");
					carroNovo.setPreco(tec.nextDouble());

					// gambiarra acontecendo...
					try {
						carroNovo.setCodigo((codigoCarro.size() + 1));
					} catch (NullPointerException e) {

						carroNovo.setCodigo(1);
					}

					gerador.cadastrarCarro(carroNovo);

					break;

				// exibe vendas cadastradas
				case 4:

					if (tipoAcesso.equals("Gerente")) {

						ArrayList<Venda> listaVendas = null;
						try {
							listaVendas = gerador.listarVendas();

							for (Venda venda : listaVendas) {

								System.out.println(venda.imprimeVenda() + "\n");
							}

							System.out.println("Pressione qualquer tecla + enter para continuar...");
							tec.next();

						} catch (NullPointerException e) {
							System.err.println(e.getMessage());

						} catch (IOException e) {
							System.err.println(e.getMessage());
						}

					}

					break;

				default:

					System.out.println("Opção inacessível! Tente novamente...");
					break;
				}

			} while (opcao3 != 1);

		}

		tec.close(); // fechando entrada de dados do teclado
	}

}
