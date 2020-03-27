package model;

public class Pris {

	private Produkt produkt;
	private PrisListe prisliste;
	private double pris;
	
	public Pris(Produkt produkt, PrisListe prisliste, int pris) {
		this.produkt = produkt;
		this.prisliste = prisliste;
		this.pris = pris;
	}
	
	public Produkt getProdukt() {
		return produkt;
	}
	
	public PrisListe getPrisListe() {
		return prisliste;
	}
	
	public double getPris() {
		return pris;
	}
	
	@Override
	public String toString() {
		return produkt.toString() + pris;
	}
}
