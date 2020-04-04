package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SalgsLinje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int antal;
	private Pris pris;
	private double rabatGivet;

	private double RPris;
	private Rundvisning rv;
	private double KlipPris;
	private Klippekort kk;

	private List<Pris> gavePriser;
	private Gaveæske gaveæske;

	public SalgsLinje(int antal, Pris pris) {
		this.antal = antal;
		this.pris = pris;
	}

	public SalgsLinje(double pris, Rundvisning rv) {
		this.RPris = pris;
		this.rv = rv;
	}
	
	public SalgsLinje(double pris, Klippekort klip) {
		this.KlipPris = pris;
		this.kk = klip;
	}

	public SalgsLinje(List<Pris> gavePriser, Gaveæske g) {
		this.gavePriser = new ArrayList<>(gavePriser);
		this.gaveæske = g;
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

	public double getRundVisPris() {
		return RPris;
	}

	public Rundvisning getRundvisning() {
		return rv;
	}

	public ArrayList<Pris> getGavePriser() {
		return new ArrayList<>(gavePriser);
	}

	public Gaveæske getGaveæske() {
		return gaveæske;
	}

	@Override
	public String toString() {
		if (rabatGivet > 0) {
			if (pris != null && pris.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
				return pris.toString() + ", " + antal + " dage, \n" + rabatGivet + " kr i rabat";
			} else if (rv != null) {
				return rv.toString() + ", " + this.getRundVisPris() + " kr, \n" + rabatGivet + " kr i rabat";
			} else if (gaveæske != null) {
				String i = gaveæske.toString();
				for (Pris p : gaveæske.getIndhold()) {
					i += "\n" + p.toString();
				}
				return i + ", \n" + rabatGivet + " kr i rabat";
			} else
				return pris.toString() + ", " + antal + " styk, \n" + rabatGivet + " kr i rabat";
		} else {
			if (pris != null && pris.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
				return pris.toString() + ", " + antal + " dage";
			} else if (rv != null) {
				return rv.toString() + ", " + this.getRundVisPris() + " kr";
			} else if (gaveæske != null) {
				String i = gaveæske.toString();
				for (Pris p : gaveæske.getIndhold()) {
					i += "\n" + p.toString();
				}
				return i;
			} else
				return pris.toString() + ", " + antal + " styk";
		}
	}
}
