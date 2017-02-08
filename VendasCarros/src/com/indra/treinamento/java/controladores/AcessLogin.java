package com.indra.treinamento.java.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import com.indra.treinamento.java.dao.GravadorArquivo;
import com.indra.treinamento.java.entidades.*;

/**
 * Classe responsável pelos logins e cadastro do sistema.
 * 
 * @author Thais
 *
 */
public class AcessLogin {

	/**
	 * Autenticação básica para ter acesso ao Sistema;
	 * 
	 * @param map
	 * @param codigoLogin
	 * @return
	 */
	public boolean autenticaLogin(HashMap<Integer, String> map, int codigoLogin) {

		if (map.containsKey(codigoLogin))

			return true;

		else
			return false;
	}

	/**
	 * Verifica se o código já existe nos cadastros
	 * 
	 * @param codigoGerado
	 * @return
	 */
	public boolean temCadastro(HashMap<Integer, String> map, int codigoGerado) {

		Set<Integer> cadastros = map.keySet();

		boolean contem = false;
		for (Integer cadastro : cadastros) {

			if (cadastro == codigoGerado) {
				contem = true;
				break;
			}
		}
		return contem;
	}

	/**
	 * imprime tipos de usuario no sistema
	 * 
	 * @return
	 */
	public void imprimeTiposUsuario() {

		System.out.println("1 - Cliente\n2 - Vendedor\n3 - Gerente");
	}

	/**
	 * Retorna uma String contendo um tipo de usuário (vendedor, cliente ou
	 * gerente)
	 * 
	 * @param numeroUsuario
	 *            o numero do tipo
	 * @return
	 */
	public String tipoUsuario(int numeroUsuario) {

		String tipoUsuario = null;

		if (numeroUsuario == 1) {

			tipoUsuario = "Cliente";

		} else if (numeroUsuario == 2) {

			tipoUsuario = "Vendedor";

		} else if (numeroUsuario == 3) {

			tipoUsuario = "Gerente";
		}

		return tipoUsuario;
	}

	/**
	 * Cadastra um tipo de usuario de acordo com sua numeração
	 * 
	 * @param numeroUsuario
	 * @return
	 */
	public void cadastrarUsuario(Cliente cliente) {

		GravadorArquivo cadastroCliente = new GravadorArquivo();
		GravadorArquivo cadastrosGeral = new GravadorArquivo();

		try {
			cadastroCliente.abrirArquivo("D:/cadastrosCliente.csv");
			cadastroCliente.escreverLinha(cliente.toString());

			cadastrosGeral.abrirArquivo("D:/cadastrosGeral.csv");
			cadastrosGeral.escreverLinha(
					cliente.getUsuario().getCodigoAcesso() + ";" + cliente.getUsuario().getTipoUsuario());

			cadastroCliente.fecharArquivo();
			cadastrosGeral.fecharArquivo();

			System.out.println("Cliente cadastrado com sucesso!");

		} catch (IOException e) {

			System.err.println("Erro ao tentar cadastrar Cliente");
		}

	}

	public void cadastrarUsuario(Funcionario funcionario) {

		GravadorArquivo cadastroFuncionario = new GravadorArquivo();
		GravadorArquivo cadastrosGeral = new GravadorArquivo();

		try {
			cadastroFuncionario.abrirArquivo("D:/cadastrosFuncionario.csv");
			cadastroFuncionario.escreverLinha(funcionario.toString());

			cadastrosGeral.abrirArquivo("D:/cadastrosGeral.csv");
			cadastrosGeral.escreverLinha(
					funcionario.getUsuario().getCodigoAcesso() + ";" + funcionario.getUsuario().getTipoUsuario());

			cadastroFuncionario.fecharArquivo();
			cadastrosGeral.fecharArquivo();

			System.out.println(funcionario.getUsuario().getTipoUsuario() + " cadastrado com sucesso!");

		} catch (IOException e) {

			System.err.println("Erro ao tentar cadastrar Funcionario");
		}

	}

	public int gerarCodigo() {

		Random gerador = new Random();

		int codigoGerado = gerador.nextInt(9000);

		return codigoGerado;
	}

}
