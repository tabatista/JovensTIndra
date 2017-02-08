package com.indra.treinamento.java.entidades;

public class Vendedor extends Funcionario {

	/**
	 * set modificado de acordo com a classe para o acréscimo do salário em % para cada venda
	 */
	public void setSalario(double venda) {
		this.salario += venda * 0.05;
	}

}
