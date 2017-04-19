package indra.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import indra.util.Driver;

public class TrocarSenha {

	public static void main(String[] args) {

		System.out.println("Olá! O Portal está exigindo que você troque "
				+ "sua senha porque ela irá expirar, só que você deseja manter a mesma senha?!"
				+ "\n Seu problema acaba aqui! Irei realizar essa troca fake de senha para você, "
				+ "e você poderá manter a mesma senha. Vamos lá?! =D");

		System.out.println(
				"\nAntes de tudo, recomendo que você incialize seu navegador chrome e autentique o seu usuário de rede.");

		// leitura de entrada de dados
		Scanner tec = new Scanner(System.in);

		System.out.print("\nQual é seu usuário da Indra: ");
		String user = tec.next() + tec.nextLine();

		System.out.println(
				"\nOBS: como o intuito é manter a senha atual, a senha que digitar agora será sua senha. (Eu preciso mesmo falar isso?!)");
		System.out.print("Qual sua senha atual: ");
		String senha = tec.next() + tec.nextLine();

		System.out.println("\nInformações fornecidas: ");
		System.out.println(" Usuario: " + user);
		System.out.println(" Senha: " + senha);

		System.out.println("\nDesenha continuar? (digitar número correspondente a opção desejada)");
		System.out.println(" 1 - SIM\n 2 - NÃO");
		int proceder = 0;

		try {
			proceder = tec.nextInt();

		} catch (InputMismatchException e) {
			System.err.println("OPS! Entrada de dados inválida! Finalizando programa...");
			System.exit(1);
		}

		if (proceder == 2) {
			System.out.println("Saindo do programa...");
			System.exit(0);
		}

		System.out.println("O robô logará no chrome-driver e realizará o processo de troca de senha para você!"
				+ "\n NÃO INTERFIRA NO PROCESSO.\n" + "Pressione qualquer tecla + enter para continuar");
		tec.next();

		try {

			Driver driver = new Driver();

			driver.trocarSenhaParaAtual(user, senha);
			System.out.println("\nSENHA \"ALTERADA\" COM SUCESSO! CONTINUE COM SEU COMODISMO =D");

			driver.encerrarDriver();
			driver.matarProcessos();

		} catch (Exception e) {
			System.err.println(
					"Algo de errado não está certo... Não foi possível realizar a troca de senha! Reinicie o programa e tente novamente!");
			e.printStackTrace();

		} finally {
			tec.close();
		}
	}

}
