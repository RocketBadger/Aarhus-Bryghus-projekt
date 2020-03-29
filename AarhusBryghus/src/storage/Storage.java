package storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Pris;
import model.PrisListe;
import model.Produkt;
import model.ProduktGruppe;

public class Storage implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Storage storage;
	private List<Produkt> produkter;
	private List<ProduktGruppe> produktGrupper;
	private List<PrisListe> prislister;

	public static Storage getStorage() {
		if (storage == null) {
			storage = new Storage();
		}
		return storage;
	}

	public Storage() {
		produkter = new ArrayList<Produkt>();
		produktGrupper = new ArrayList<ProduktGruppe>();
		prislister = new ArrayList<PrisListe>();
	}

// Produkt metoder ----------------------------------------------
	public void addProdukt(Produkt produkt) {
		if (!produkter.contains(produkt)) {
			produkter.add(produkt);
		}
	}

	public List<Produkt> getAllProdukter() {
		return new ArrayList<>(produkter);
	}

// ProduktGruppe metoder -----------------------------------------
	public void addProduktGruppe(ProduktGruppe produktGruppe) {
		if (!produktGrupper.contains(produktGruppe)) {
			produktGrupper.add(produktGruppe);
		}
	}

	public List<ProduktGruppe> getAllProduktGrupper() {
		return new ArrayList<>(produktGrupper);
	}

// PrisListe metoder ---------------------------------------------
	public ArrayList<PrisListe> getAllPrisLister() {
		return new ArrayList<>(prislister);
	}

	public void addPrisListe(PrisListe prisliste) {
		if (!prislister.contains(prisliste)) {
			prislister.add(prisliste);
		}
	}

	public void removePrisListe(PrisListe prisliste) {
		if (prislister.contains(prisliste)) {
			prislister.remove(prisliste);
		}
	}

// Pris metoder ---------------------------------------------------------------

	public static ArrayList<Pris> getAllPriser() {
		ArrayList<Pris> priser = new ArrayList<>();
		for (PrisListe pl : storage.prislister) {
			for (Pris p : pl.getAllPriser()) {
				priser.add(p);
			}
		}
		return priser;
	}
}
