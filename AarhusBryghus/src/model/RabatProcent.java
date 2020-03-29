package model;

public class RabatProcent implements Rabat {

	private String navn;
	private double procentrabat;
	
	public RabatProcent(String navn, double procentrabat) {
		this.navn = navn;
		this.procentrabat = procentrabat;
	}
	
	@Override 
	public double calcRabat(Double pris, int antal) {
		double rabat = (procentrabat / 100) * pris;
		double givetRabat = pris - rabat;
		double sum = givetRabat * antal;
		
		return sum;
	}
	
	@Override
	public String toString() {
		return "Rabat: " + procentrabat + "%, " + "Navn: " + navn;
	}
}
