package model;

public class Produkt {
	private String navn;
	private String nr;
	private ProduktGruppe produktGruppe;

	public Produkt(String navn, String nr, ProduktGruppe produktGruppe) {
		this.navn = navn;
		this.nr = nr;
		this.produktGruppe = produktGruppe;
	}

	public String getNavn() {
		return navn;
	}

	public String getNr() {
		return nr;
	}

	public ProduktGruppe getProduktGruppe() {
		return produktGruppe;
	}

	@Override
	public String toString() {
		return navn + ", " + nr + ", " + produktGruppe;
	}
}
