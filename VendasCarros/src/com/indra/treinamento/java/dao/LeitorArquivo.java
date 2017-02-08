package com.indra.treinamento.java.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.indra.treinamento.java.entidades.*;

/**
 * Classe responsavel por ler o arquivo de entrada - arquivo texto com campos
 * separados por ';'
 */
public class LeitorArquivo {

	/** stream (InputStream) */
	private FileReader inputStream;

	/** manipulador de arquivo */
	private BufferedReader arquivoEntrada;

	/**
	 * Abre um arquivo para leitura sequencial
	 * 
	 * @param caminhoArquivo
	 *            O caminho completo(caminho + nome + extensao) ateh o arquivo
	 * @throws IOException
	 */
	public void abrirArquivo(String caminhoArquivo) throws IOException {

		// instancia os objetos
		inputStream = new FileReader(caminhoArquivo);
		arquivoEntrada = new BufferedReader(inputStream);
	}

	/**
	 * Le uma linha do arquivo.
	 * 
	 * @return A linha lida
	 * @throws IOException
	 */
	public String lerLinha() throws IOException {

		return arquivoEntrada.readLine();

	}

	/**
	 * Responsável por criar um hashMap contendo todos os logins do Sistema
	 * através de um arquivo Geral de Cadastros
	 * 
	 * @return Um <code>HashMap</code> com os códigos de todos os logins
	 *         disponíveis.
	 * @throws IOException
	 * @see Usuario
	 */
	public HashMap<Integer, String> lerCadastrosGerais() throws IOException {

		String linha = lerLinha();

		HashMap<Integer, String> loginGeral = new HashMap<>();

		while (linha != null) {

			String[] campo = linha.split(";");
			loginGeral.put(Integer.parseInt(campo[0]), campo[1]);
			linha = lerLinha();

		}

		return loginGeral;

	}

	/**
	 * Realiza a leitura de um arquivo de clientes
	 * 
	 * @param codigo
	 *            codigo de login do cliente
	 * @return Cliente
	 */
	public Cliente lerCliente(int codigo) {

		String linha;
		Cliente cliente = null;
		Usuario usuario = null;
		try {
			linha = lerLinha();

			while (linha != null) {

				String[] campos = linha.split(";");

				if (Integer.parseInt(campos[0]) == codigo) {

					usuario = new Usuario(Integer.parseInt(campos[0]), campos[1]);
					cliente = new Cliente();

					cliente.setUsuario(usuario);
					cliente.setNome(campos[2]);
					cliente.setTelefone(Integer.parseInt(campos[3]));
					cliente.setCpf(Integer.parseInt(campos[4]));
					cliente.setEndereço(campos[5]);

					break;
				}

				linha = lerLinha();
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return cliente;

	}

	public Funcionario lerFuncionario(int codigo) {

		String linha;
		Funcionario funcionario = null;
		Usuario usuario = null;
		try {
			linha = lerLinha();

			while (linha != null) {

				String[] campos = linha.split(";");

				if (Integer.parseInt(campos[0]) == codigo) {

					usuario = new Usuario(Integer.parseInt(campos[0]), campos[1]);

					if (campos[1].equals("Gerente")) {

						funcionario = new Gerente();

					} else {
						funcionario = new Vendedor();
					}

					funcionario.setUsuario(usuario);
					funcionario.setNome(campos[2]);
					funcionario.setTelefone(Integer.parseInt(campos[3]));
					funcionario.setCpf(Integer.parseInt(campos[4]));

					break;
				}

				linha = lerLinha();

			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return funcionario;

	}

	/**
	 * Fecha o arquivo
	 * 
	 * @throws IOException
	 */
	public void fecharArquivo() throws IOException {

		arquivoEntrada.close();
		inputStream.close();

	}

}
