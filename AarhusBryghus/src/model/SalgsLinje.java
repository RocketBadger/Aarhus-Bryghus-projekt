package model;

public class SalgsLinje {
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
