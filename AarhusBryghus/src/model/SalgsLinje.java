package model;

import java.io.Serializable;

public class SalgsLinje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int antal;
	private Pris pris;

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

	@Override
	public String toString() {
		return pris.toString() + ", " + antal + " styk";
	}
}
