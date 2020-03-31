package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Salg {
//	TODO implement Serializable
	private List<SalgsLinje> salgsLinjer;
	private BetalingsFormer betalingsform;
	private LocalDate dato;
	private double reelPris;

	public Salg(ArrayList<SalgsLinje> salgslinjer, BetalingsFormer betalingsform, LocalDate dato) {
		this.salgsLinjer = salgslinjer;
		this.betalingsform = betalingsform;
		this.dato = dato;
	}

	public double beregnSamletListePris() {
		double i = 0;
		for (SalgsLinje s : salgsLinjer) {
			i += (s.getPris().getPris() * s.getAntal());
		}
		this.reelPris = i;
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
		return reelPris;
	}
}
