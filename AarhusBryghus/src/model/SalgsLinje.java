package model;

import java.io.Serializable;

public class SalgsLinje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int antal;
	private Pris pris;
	private double rabatGivet;
	private double RPris;
	private Rundvisning rv;

	public SalgsLinje(int antal, Pris pris) {
		this.antal = antal;
		this.pris = pris;
	}

	public SalgsLinje(double pris, Rundvisning rv) {
		this.RPris = pris;
		this.rv = rv;
	}

	public int getAntal() {
		return antal;
	}

	public Pris getPris() {
		return pris;
	}

	public double getRabatGivet() {
		return rabatGivet;
	}

	public void givRabat(double rabat) {
		this.rabatGivet += rabat;
	}

	public double getRundVPris() {
		return RPris;
	}

	public Rundvisning getRundvisning() {
		return rv;
	}

	@Override
	public String toString() {
		if (rabatGivet > 0) {
			if (pris.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
				return pris.toString() + ", " + antal + " dage" + rabatGivet + " kr i rabat";
			} else
				return pris.toString() + ", " + antal + " styk, " + rabatGivet + " kr i rabat";
		} else if (rv != null) {
			return rv.toString() + ", " + this.getRundVPris() + " kr";
		} else {
			if (pris.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
				return pris.toString() + ", " + antal + " dage";
			} else
				return pris.toString() + ", " + antal + " styk";
		}
	}
}
