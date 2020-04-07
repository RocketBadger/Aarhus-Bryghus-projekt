package gui;

import model.BetalingsFormer;

public class TableColumns {
	private String produktNavn;
	private double produktPris;
	private double samletPris;
	private int produktAntal;
	private BetalingsFormer betalingsform;
	
	public TableColumns(String produktNavn, int produktAntal, double produktPris, double samletPris, BetalingsFormer betalingsform) {
		this.produktNavn = produktNavn;
		this.produktAntal = produktAntal;
		this.produktPris = produktPris;
		this.samletPris = samletPris;
		this.betalingsform = betalingsform;
	}
	
	public TableColumns(String produktNavn, BetalingsFormer betalingsform) {
		this.produktNavn = produktNavn;
		this.betalingsform = betalingsform;
	}
	
	public String getProduktNavn() {
		return produktNavn;
	}
	
	public double getProduktPris() {
		return produktPris;
	}
	
	public double getSamletPris() {
		return samletPris;
	}
	
	public int getProduktAntal() {
		return produktAntal;
	}
	
	public BetalingsFormer getBetalingsform() {
		return betalingsform;
	}
}
