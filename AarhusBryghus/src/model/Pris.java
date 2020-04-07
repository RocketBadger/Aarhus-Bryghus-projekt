package model;

import java.io.Serializable;

public class Pris implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produkt produkt;
	private PrisListe prisliste;
	private double pris;
	private Klippekort klippekort;

	public Pris(Produkt produkt, PrisListe prisliste, int pris) {
		this.produkt = produkt;
		this.prisliste = prisliste;
		this.pris = pris;
	}
	
	public Pris(Klippekort klippekort, int pris) {
		this.klippekort = klippekort;
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

	public void setPrisListe(PrisListe pl) {
		this.prisliste = pl;
	}
	
	public Klippekort getKlippekort() {
		return klippekort;
	}

	public void removePrisListe() {
		if (this.prisliste != null) {
			PrisListe pl = this.prisliste;
			this.prisliste = null;
			pl.removePris(this);
		}
	}

	@Override
	public String toString() {
		if (produkt != null) {
			return produkt.toString() + ". Pris: " + pris + " kr";
		} else if (klippekort != null) {
			return klippekort.toString() + ". Pris: " + pris + " kr";
		} else {
			return "noget gik galt i Pris klassen";
		}
	}
}
