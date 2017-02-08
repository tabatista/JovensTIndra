package com.indra.treinamento.java.controladores;

import java.io.IOException;
import java.util.ArrayList;
import com.indra.treinamento.java.dao.*;
import com.indra.treinamento.java.entidades.*;

public class GeradorDados {

	/**
	 * Escreve um carro no arquivo.
	 * 
	 * @param O
	 *            <code>carro</code> a ser escrito no arquivo.
	 * @throws IOException
	 * @see Carro
	 */
	public void cadastrarCarro(Carro carro) {

		GravadorArquivo cadastroCarro = new GravadorArquivo();

		try {

			/*
			 * O metodo toString foi implementado de forma a devolver uma String
			 * com os campos separados com ';'
			 */
			cadastroCarro.abrirArquivo("D:/cadastrosCarro.csv");
			cadastroCarro.escreverLinha(carro.toString());

			cadastroCarro.fecharArquivo();

			System.out.println("Carro cadastrado com sucesso!");

		} catch (IOException e) {

			System.err.println("Erro ao tentar cadastrar Carro");
		}

	}

	/**
	 * Lista todos os carros do arquivo de carro
	 * 
	 * @return ArrayList de carros
	 * @throws IOException
	 */
	public ArrayList<Carro> listarCarros() throws IOException {

		LeitorArquivo cadastroCarro = new LeitorArquivo();
		String linha;

		ArrayList<Carro> listaCarros = null;

		try {
			cadastroCarro.abrirArquivo("D:/cadastrosCarro.csv");

			linha = cadastroCarro.lerLinha();

			while (linha != null) {

				listaCarros = new ArrayList<>();

				String[] campos = linha.split(";");

				Carro carro = new Carro();

				carro.setCodigo(Integer.parseInt(campos[0]));
				carro.setPlaca(campos[1]);
				carro.setModelo(campos[2]);
				carro.setAno(Integer.parseInt(campos[3]));
				carro.setCor(campos[4]);
				carro.setMarca(campos[5]);
				carro.setPreco(Double.parseDouble(campos[6]));

				listaCarros.add(carro);
				linha = cadastroCarro.lerLinha();
			}

			cadastroCarro.fecharArquivo();

		} catch (IOException e) {

			throw new IOException("Erro ao tentar listar Carros");
		}
		return listaCarros;
	}

	/**
	 * Retorna um carro específico com base no código do parametro
	 * 
	 * @param codigoCarro
	 *            o carro a ser procurado
	 * @return o carro com todas as informações contida com base no código fornecido
	 */
	public Carro consultarCarro(int codigoCarro) {

		LeitorArquivo consultaCarro = new LeitorArquivo();
		String linha;

		Carro carroConsultado = null;

		try {
			consultaCarro.abrirArquivo("D:/cadastrosCarro.csv");

			linha = consultaCarro.lerLinha();

			while (linha != null) {

				String[] campos = linha.split(";");

				if (Integer.parseInt(campos[0]) == codigoCarro) {

					carroConsultado = new Carro();

					carroConsultado.setCodigo(Integer.parseInt(campos[0]));
					carroConsultado.setPlaca(campos[1]);
					carroConsultado.setModelo(campos[2]);
					carroConsultado.setAno(Integer.parseInt(campos[3]));
					carroConsultado.setCor(campos[4]);
					carroConsultado.setMarca(campos[5]);
					carroConsultado.setPreco(Double.parseDouble(campos[6]));
					break;
				}

				linha = consultaCarro.lerLinha();

			}

			consultaCarro.fecharArquivo();

		} catch (IOException e) {

			System.err.println("Erro ao tentar consultar Carro");
		}
		return carroConsultado;
	}

	/**
	 * Grava uma venda no arquivo de vendas
	 * 
	 * @param venda
	 *            a venda para ser gravada
	 */
	public void cadastrarVenda(Venda venda) {

		GravadorArquivo cadastroVenda = new GravadorArquivo();

		try {
			cadastroVenda.abrirArquivo("D:/cadastrosVenda.csv");
			cadastroVenda.escreverLinha(venda.toString());

			cadastroVenda.fecharArquivo();

			System.out.println("Venda cadastrada com sucesso!");

		} catch (IOException e) {

			System.err.println("Erro ao tentar cadastrar Venda");
		}

	}

	/**
	 * Lista todos as Vendas do arquivo de Venda
	 * 
	 * @return ArrayList de Vendas
	 * @throws IOException,
	 *             NullPointerException
	 */
	public ArrayList<Venda> listarVendas() throws IOException, NullPointerException {

		LeitorArquivo cadastroVenda = new LeitorArquivo();
		String linha;

		ArrayList<Venda> listaVendas = new ArrayList<>();

		try {
			cadastroVenda.abrirArquivo("D:/cadastrosVenda.csv");

			linha = cadastroVenda.lerLinha();

			while (linha != null) {

				String[] campos = linha.split(";");

				Venda venda = new Venda();
				Cliente clienteVenda = new Cliente();

				clienteVenda.setNome(campos[0]);
				clienteVenda.setTelefone(Integer.parseInt(campos[1]));

				venda.setCliente(clienteVenda);
				venda.setCarro(campos[2]);
				venda.setValorTransacao(Double.parseDouble(campos[3]));

				venda.setDataTransacao(campos[4]);
				venda.setFormaPagamento(campos[5]);
				venda.setQuantidadeParcelas(Integer.parseInt(campos[6]));
				venda.setMeioVenda(campos[7]);

				listaVendas.add(venda);
				linha = cadastroVenda.lerLinha();
			}

			cadastroVenda.fecharArquivo();

		} catch (NullPointerException e) {

			throw new NullPointerException("Nenhuma venda realizada!");

		} catch (IOException e) {

			throw new IOException("Nenhuma venda realizada!");

		}

		return listaVendas;
	}

}
