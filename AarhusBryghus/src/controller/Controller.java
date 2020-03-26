package controller;

import java.util.List;

import javafx.scene.control.TextField;
import model.Produkt;
import model.ProduktGruppe;
import storage.Storage;

public class Controller {
	private Storage storage;
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

	public Produkt createProdukt(String navn, String nr, ProduktGruppe produktGruppe) {
		try {
			Produkt p = new Produkt(navn, nr, produktGruppe);
			produktGruppe.addProdukt(p);
			storage.addProdukt(p);
			return p;
		} catch (IllegalArgumentException i) {
			System.out.println("ProduktGruppe muligvis ikke gyldig");
			System.out.println("Message: " + i);
			return null;
		}
	}

	public ProduktGruppe createProduktGruppe(String type, String beskrivelse) {
		try {
			ProduktGruppe pg = new ProduktGruppe(type, beskrivelse);
			storage.addProduktGruppe(pg);
			return pg;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public void createSomeObjects() {
		this.createProduktGruppe("Drikkevarer", "Øl, whisky osv");
		this.createProdukt("Klosterbryg", "1", storage.getAllProduktGrupper().get(0));
		this.createProduktGruppe("Merchandise", "Hatte, t-shirts");
		this.createProdukt("Hat med logo", "2", storage.getAllProduktGrupper().get(1));
	}

	public boolean parseTextField(TextField textField) {
		if (textField.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
