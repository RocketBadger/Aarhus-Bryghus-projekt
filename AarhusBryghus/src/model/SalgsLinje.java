package model;

import java.io.Serializable;

public class SalgsLinje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int antal;
	private Pris pris;
	private double rabatGivet;

	public SalgsLinje(int antal, Pris pris) {
		this.antal = antal;
		this.pris = pris;
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

	@Override
	public String toString() {
		if (rabatGivet > 0) {
			if (pris.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
				return pris.toString() + ", " + antal + " dage" + rabatGivet + " kr i rabat";
			} else
				return pris.toString() + ", " + antal + " styk, " + rabatGivet + " kr i rabat";
		} else {
			if (pris.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
				return pris.toString() + ", " + antal + " dage";
			} else
				return pris.toString() + ", " + antal + " styk";
		}
	}
}
