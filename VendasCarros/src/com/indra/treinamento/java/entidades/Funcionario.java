package com.indra.treinamento.java.entidades;

public class Funcionario extends Pessoa {

	protected double salario;
	private Usuario usuario;

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		// <codigo usuario> / <tipo usuario> / <nome funcionario> / <telefone> /
		// <cpf> / <salario>
		return (usuario.getCodigoAcesso() + ";" + usuario.getTipoUsuario() + ";" + nome + ";" + telefone + ";" + cpf
				+ ";" + salario);
	}

}
