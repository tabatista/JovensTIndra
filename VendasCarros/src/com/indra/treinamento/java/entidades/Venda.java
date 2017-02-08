package com.indra.treinamento.java.entidades;

public class Venda {

	private Cliente cliente;
	private String carro; // Apenas o modelo do carro
	private double valorTransacao;
	private String dataTransacao;
	private String formaPagamento;
	private int quantidadeParcelas;
	private String meioVenda;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(String dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public String getMeioVenda() {
		return meioVenda;
	}

	public void setMeioVenda(String meioVenda) {
		this.meioVenda = meioVenda;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	/**
	 * Recebe um numero para definir formato do pagamento
	 * 
	 * @param numeroPagamento
	 */
	public void defineFormaPagamento(int numeroPagamento) {

		String formaPagamento = null;

		if (numeroPagamento == 1) {

			formaPagamento = "Boleto";

		} else if (numeroPagamento == 2) {

			formaPagamento = "Cartão de crédito";

		} else if (numeroPagamento == 3) {

			formaPagamento = "Cartão de débito";

		} else {
			formaPagamento = "Boleto";
		}

		this.formaPagamento = formaPagamento;
	}

	/**
	 * Imprime as formas de pagamento disponíveis
	 * 
	 * @return
	 */
	public String imprimeFormasPagamento() {

		return "1 - Boleto\n2 - Cartão de crédito\n3 - Cartão de débito";
	}

	public String toString() {

		return cliente.getNome() + ";" + cliente.getTelefone() + ";" + carro + ";" + valorTransacao + ";"
				+ dataTransacao + ";" + formaPagamento + ";" + quantidadeParcelas + ";" + meioVenda;
	}

	public String imprimeVenda() {

		String saida = "Nome do Cliente: " + cliente.getNome() + "\nTelefone: " + cliente.getTelefone() + "\nCarro: "
				+ carro + "\nData e Hora da Transação: " + dataTransacao + "\nForma Pagamento: " + formaPagamento
				+ "\nQuantidade de parcelas: " + quantidadeParcelas + "\nEfetuou a venda: " + meioVenda;

		return saida;
	}

}
