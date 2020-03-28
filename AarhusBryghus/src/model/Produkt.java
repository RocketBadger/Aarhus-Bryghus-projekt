package model;

public class Produkt {
	private String navn;
	private ProduktGruppe produktGruppe;

	public Produkt(String navn, ProduktGruppe produktGruppe) {
		this.navn = navn;
		this.produktGruppe = produktGruppe;
	}

	public String getNavn() {
		return navn;
	}

	public ProduktGruppe getProduktGruppe() {
		return produktGruppe;
	}

	@Override
	public String toString() {
		return navn + ", " + produktGruppe;
	}
}
