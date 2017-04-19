package indra.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import indra.util.Driver;

public class TrocarSenha {

	public static void main(String[] args) {

		System.out.println("Ol�! O Portal est� exigindo que voc� troque "
				+ "sua senha porque ela ir� expirar, s� que voc� deseja manter a mesma senha?!"
				+ "\n Seu problema acaba aqui! Irei realizar essa troca fake de senha para voc�, "
				+ "e voc� poder� manter a mesma senha. Vamos l�?! =D");

		System.out.println(
				"\nAntes de tudo, recomendo que voc� incialize seu navegador chrome e autentique o seu usu�rio de rede.");

		// leitura de entrada de dados
		Scanner tec = new Scanner(System.in);

		System.out.print("\nQual � seu usu�rio da Indra: ");
		String user = tec.next() + tec.nextLine();

		System.out.println(
				"\nOBS: como o intuito � manter a senha atual, a senha que digitar agora ser� sua senha. (Eu preciso mesmo falar isso?!)");
		System.out.print("Qual sua senha atual: ");
		String senha = tec.next() + tec.nextLine();

		System.out.println("\nInforma��es fornecidas: ");
		System.out.println(" Usuario: " + user);
		System.out.println(" Senha: " + senha);

		System.out.println("\nDesenha continuar? (digitar n�mero correspondente a op��o desejada)");
		System.out.println(" 1 - SIM\n 2 - N�O");
		int proceder = 0;

		try {
			proceder = tec.nextInt();

		} catch (InputMismatchException e) {
			System.err.println("OPS! Entrada de dados inv�lida! Finalizando programa...");
			System.exit(1);
		}

		if (proceder == 2) {
			System.out.println("Saindo do programa...");
			System.exit(0);
		}

		System.out.println("O rob� logar� no chrome-driver e realizar� o processo de troca de senha para voc�!"
				+ "\n N�O INTERFIRA NO PROCESSO.\n" + "Pressione qualquer tecla + enter para continuar");
		tec.next();

		try {

			Driver driver = new Driver();

			driver.trocarSenhaParaAtual(user, senha);
			System.out.println("\nSENHA \"ALTERADA\" COM SUCESSO! CONTINUE COM SEU COMODISMO =D");

			driver.encerrarDriver();
			driver.matarProcessos();

		} catch (Exception e) {
			System.err.println(
					"Algo de errado n�o est� certo... N�o foi poss�vel realizar a troca de senha! Reinicie o programa e tente novamente!");
			e.printStackTrace();

		} finally {
			tec.close();
		}
	}

}
