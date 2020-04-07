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

	public Salg(ArrayList<SalgsLinje> salgslinjer, BetalingsFormer betalingsform, LocalDate dato) {
		this.salgsLinjer = salgslinjer;
		this.betalingsform = betalingsform;
		this.dato = dato;
	}

	/**
	 * Pre: salgslinjer != null
	 * 
	 * Beregner den samlede pris for alle salgslinjer tilhørende salget
	 * 
	 * @return Returnerer samlet pris.
	 */
	public double beregnSamletListePris() {
		double i = 0;
		for (SalgsLinje s : salgsLinjer) {
			if (s.getRundvisning() != null) {
				i += s.getRundVisPris();
			} else if (s.getGavePriser() != null) {
				for (Pris p : s.getGavePriser()) {
					i += p.getPris();
				}
			} else if (s.getKlippekort() != null) {
				i += s.getKlipPris();
			} else
				i += (s.getPris().getPris() * s.getAntal());
		}
		return i;
	}

	/**
	 * Pre: salgslinjer != null
	 * 
	 * beregner den samlede rabat for alle salgslinjer tilhørende salget
	 * 
	 * @return samlet rabat
	 */
	public double beregnSamletRabat() {
		double i = 0;
		for (SalgsLinje s : salgsLinjer) {
			i += s.getRabatGivet();
		}
		return i;
	}

	public void addSalgsLinje(SalgsLinje sl) {
		if (!salgsLinjer.contains(sl))
			salgsLinjer.add(sl);
	}

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
		return beregnSamletListePris() - beregnSamletRabat();
	}
}
