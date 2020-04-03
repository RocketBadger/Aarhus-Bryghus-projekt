package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Udlejning implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produkt produkt;
	private LocalDate datoUd;
	private LocalDate datoRetur;
	private double indhold;

	public Udlejning(Produkt produkt, LocalDate udlejningsDato, Double ltr) {
		this.produkt = produkt;
		this.datoUd = udlejningsDato;
		this.indhold = ltr;
	}

	public LocalDate getUdlejningsDato() {
		return datoUd;
	}

	public void setReturDato(LocalDate dato) {
		this.datoRetur = dato;
	}

	public LocalDate getReturDato() {
		return datoRetur;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public double getStørrelse() {
		return indhold;
	}

	@Override
	public String toString() {
		return super.toString() + " udlejet " + datoUd;
	}
}
