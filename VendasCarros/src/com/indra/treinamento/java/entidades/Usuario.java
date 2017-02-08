package com.indra.treinamento.java.entidades;

/**
 * O login ser� o mais simples poss�vel - sem senha, apenas um c�digo para
 * acesso, sem autentica��o
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
