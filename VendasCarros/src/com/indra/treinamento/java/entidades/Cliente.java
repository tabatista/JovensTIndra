package com.indra.treinamento.java.entidades;

public class Cliente extends Pessoa {

	private String endereco;
	private Usuario usuario;

	public String getEndere�o() {
		return endereco;
	}

	public void setEndere�o(String endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String toString() {
		// <codigo usuario> / <tipo usuario> / <nome cliente> / <telefone> /
		// <cpf>
		return (usuario.getCodigoAcesso() + ";" + usuario.getTipoUsuario() + ";" + nome.toUpperCase() + ";" + telefone + ";" + cpf
				+ ";" + endereco);
	}

}
