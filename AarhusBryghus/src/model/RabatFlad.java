package model;

public class RabatFlad implements Rabat {

	private String navn;
	private double fladrabat;

	public RabatFlad(String navn, double fladrabat) {
		this.navn = navn;
		this.fladrabat = fladrabat;
	}

	@Override
	public double calcRabat(Double pris, int antal) {
		double givetRabat = pris - fladrabat;
		double sum = givetRabat * antal;

		return sum;
	}

	@Override
	public String toString() {
		return "Rabat: " + fladrabat + "kr, " + "Navn: " + navn;
	}
}
