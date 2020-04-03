package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Salg implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SalgsLinje> salgsLinjer;
	private BetalingsFormer betalingsform;
	private LocalDate dato;
	private double reelPris;

	public Salg(ArrayList<SalgsLinje> salgslinjer, BetalingsFormer betalingsform, LocalDate dato) {
		this.salgsLinjer = salgslinjer;
		this.betalingsform = betalingsform;
		this.dato = dato;
	}

	/**
	 * Pre: salgslinjer != null
	 * 
	 * @return Returnerer samlet pris for en salgslinje.
	 */
	public double beregnSamletListePris() {
		double i = 0;
		for (SalgsLinje s : salgsLinjer) {
			if (s.getRundvisning() != null) {
				i += s.getRundVPris();
			} else
				i += (s.getPris().getPris() * s.getAntal());
		}
		this.reelPris = i;
		return i;
	}

	public double beregnSamletRabat() {
		double i = 0;
		for (SalgsLinje s : salgsLinjer) {
			i += s.getRabatGivet();
		}
		return i;
	}

	/**
	 * Tilføjer salgslinje s1 til ArrayListen salgslinjer.
	 * 
	 * @param sl - Salgslinje
	 */
	public void addSalgsLinje(SalgsLinje sl) {
		if (!salgsLinjer.contains(sl))
			salgsLinjer.add(sl);
	}

	/**
	 * Fjerner salgslinje s1 fra ArrayListen salgslinjer.
	 * 
	 * @param sl - Salgslinje
	 */
	public void removeSalgsLinje(SalgsLinje sl) {
		if (salgsLinjer.contains(sl)) {
			salgsLinjer.remove(sl);
		}
	}

	public ArrayList<SalgsLinje> getSalgsLinjer() {
		return new ArrayList<>(salgsLinjer);
	}

	public BetalingsFormer getBetalingsform() {
		return betalingsform;
	}

	public LocalDate getDato() {
		return dato;
	}

	public double getReelPris() {
		return reelPris;
	}
}
