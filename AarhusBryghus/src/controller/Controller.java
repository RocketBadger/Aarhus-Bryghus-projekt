package controller;

import java.util.List;

import javafx.scene.control.TextField;
import model.Pris;
import model.PrisListe;
import model.Produkt;
import model.ProduktGruppe;
import storage.Storage;

public class Controller {
	private static Storage storage;
	private static Controller controller;

	private Controller() {
		storage = new Storage();
	}

	public static Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public static Controller getTestController() {
		return new Controller();
	}

	public List<Produkt> getAllProdukter() {
		return storage.getAllProdukter();
	}

	public List<ProduktGruppe> getAllProduktGrupper() {
		return storage.getAllProduktGrupper();
	}

	public List<PrisListe> getAllPrisLister() {
		return storage.getAllPrisLister();
	}

	public Produkt createProdukt(String navn, ProduktGruppe produktGruppe) {
		try {
			Produkt p = new Produkt(navn, produktGruppe);
			produktGruppe.addProdukt(p);
			storage.addProdukt(p);
			return p;
		} catch (IllegalArgumentException i) {
			System.out.println("ProduktGruppe muligvis ikke gyldig");
			System.out.println("Message: " + i);
			return null;
		}
	}

	public ProduktGruppe createProduktGruppe(String navn) {
		try {
			ProduktGruppe pg = new ProduktGruppe(navn);
			storage.addProduktGruppe(pg);
			return pg;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public PrisListe createPrisListe(String navn) {
		try {
			PrisListe pl = new PrisListe(navn);
			storage.addPrisListe(pl);
			return pl;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public Pris createPris(Produkt produkt, PrisListe prisliste, int pris) {
		try {
			Pris samletpris = new Pris(produkt, prisliste, pris);
			prisliste.addPris(samletpris);
			return samletpris;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}

	}

	public void createSomeObjects() {
		// Produktgrupper oprettes
		this.createProduktGruppe("Flaskeøl");
		this.createProduktGruppe("Fadøl");
		this.createProduktGruppe("Øl på fustage");
		this.createProduktGruppe("Spiritus");
		this.createProduktGruppe("Merchandise");

		// Produkter oprettes
		this.createProdukt("Klosterbryg", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Klosterbryg", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Klosterbryg", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Whisky", storage.getAllProduktGrupper().get(3));
		this.createProdukt("Hat med logo", storage.getAllProduktGrupper().get(4));

		// Prislister oprettes
		this.createPrisListe("Fredagsbar");
		this.createPrisListe("Butikssalg");
		this.createPrisListe("Julefrokost");

		// Priser oprettes
		this.createPris(storage.getAllProdukter().get(0), storage.getAllPrisLister().get(0), 15);
		this.createPris(storage.getAllProdukter().get(1), storage.getAllPrisLister().get(0), 40);
		this.createPris(storage.getAllProdukter().get(3), storage.getAllPrisLister().get(0), 300);

		this.createPris(storage.getAllProdukter().get(0), storage.getAllPrisLister().get(1), 10);
		this.createPris(storage.getAllProdukter().get(2), storage.getAllPrisLister().get(1), 150);
	}

	public boolean parseTextField(TextField textField) {
		if (textField.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
