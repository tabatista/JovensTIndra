package com.indra.treinamento.java.entidades;

/**
 * O login será o mais simples possível - sem senha, apenas um código para
 * acesso, sem autenticação
 * 
 * @author Thais
 *
 */
public class Usuario {

	private int codigoAcesso;
	private String tipoUsuario;

	public Usuario() {

	}

	public Usuario(int codigoAcesso, String tipoUsuario) {

		this.codigoAcesso = codigoAcesso;
		this.tipoUsuario = tipoUsuario;
	}

	public int getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(int codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
