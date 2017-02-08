package com.indra.treinamento.java.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.indra.treinamento.java.entidades.*;

/**
 * Classe responsavel por gravar o arquivo de saida - arquivo texto com campos
 * separados por ';'
 */
public class GravadorArquivo {

	/** stream (inputStream) */
	private FileWriter outputStream;

	/** manipulador de arquivo */
	private PrintWriter arquivoSaida;

	/**
	 * Abre um arquivo para escrita sequencial
	 * 
	 * @param caminhoArquivo
	 *            O caminho completo(caminho + nome + extensao) ateh o arquivo
	 * @throws IOException
	 */
	public void abrirArquivo(String caminhoArquivo) throws IOException {

		// instancia os objetos

		File arquivo = new File(caminhoArquivo);

		if (arquivo.exists()) {
			// 'true' para continuar gravando após criação do arquivo
			outputStream = new FileWriter(caminhoArquivo, true);
		} else {
			//sem true para não ter pulo na primeira linha, deixando apenas espaços em branco
			outputStream = new FileWriter(caminhoArquivo);
		}
		arquivoSaida = new PrintWriter(outputStream);
	}

	/**
	 * Escreve uma linha no arquivo.
	 * 
	 * @param linha
	 *            a linha a ser escrita
	 * @throws IOException
	 */
	public void escreverLinha(String linha) {

		arquivoSaida.println(linha);

	}

	/**
	 * Escreve um Cliente no arquivo.
	 * 
	 * @param O
	 *            <code>Cliente</code> a ser escrito no arquivo.
	 * @throws IOException
	 * @see Cliente
	 */

	public void escreverUsuario(Cliente cliente) {

		/*
		 * O metodo toString foi implementado de forma a devolver uma String com
		 * os campos separados com ';'
		 */

		arquivoSaida.println(cliente.toString());

	}

	/**
	 * Escreve um funcionario no arquivo.
	 * 
	 * @param O
	 *            <code>funcionario</code> a ser escrito no arquivo.
	 * @throws IOException
	 * @see Funcionario
	 */

	public void escreverUsuario(Funcionario funcionario) {

		/*
		 * O metodo toString foi implementado de forma a devolver uma String com
		 * os campos separados com ';'
		 */

		arquivoSaida.println(funcionario.toString());

	}

	/**
	 * Fecha o arquivo
	 * 
	 * @throws IOException
	 */
	public void fecharArquivo() throws IOException {

		arquivoSaida.close();
		outputStream.close();

	}

}
