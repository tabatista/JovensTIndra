package com.indra.treinamento.java.entidades;

public class Carro {

	private int codigo;
	private String placa;
	private String modelo;
	private int ano;
	private String cor;
	private String marca;
	private double preco;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws NullPointerException{
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString(){
		
		return codigo + ";" + placa + ";" + modelo + ";" + ano + ";" + cor + ";" + marca + ";" + preco;
	}
	
	public void imprimeCarro(){
		
		System.out.println("Codigo carro: " + codigo + "\nModelo: " + modelo.toUpperCase() + "\nAno: " + ano + "\nMarca: " + marca.toUpperCase() + "\nPreço: " + preco);
	}

	

}
