package it.reply.sytel.monitoraggio.beanVO.impl;

public class Phone {
	
	private int id;
	private String nome;
	private String dealer;
	private double prezzo;
	private String descrizione;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDealer() {
		return dealer;
	}
	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public String toString() {
		return "Phone [id=" + id + ", nome=" + nome + ", dealer=" + dealer
				+ ", prezzo=" + prezzo + ", descrizione=" + descrizione + "]";
	}
	
	

}
