package storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Pris;
import model.PrisListe;
import model.Produkt;
import model.ProduktGruppe;
import model.Salg;

public class Storage implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Storage storage;
	private List<Produkt> produkter;
	private List<ProduktGruppe> produktGrupper;
	private List<PrisListe> prislister;
	private List<Pris> priser;
	private List<Salg> solgt;

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
		priser = new ArrayList<Pris>();
		solgt = new ArrayList<Salg>();
	}

// Produkt metoder ----------------------------------------------
	public void storeProdukt(Produkt produkt) {
		if (!produkter.contains(produkt)) {
			produkter.add(produkt);
		}
	}

	public void removeProdukt(Produkt produkt) {
		if (produkter.contains(produkt)) {
			produkter.remove(produkt);
		}
	}

	public List<Produkt> getAllProdukter() {
		return new ArrayList<>(produkter);
	}

// ProduktGruppe metoder -----------------------------------------
	public void storeProduktGruppe(ProduktGruppe produktGruppe) {
		if (!produktGrupper.contains(produktGruppe)) {
			produktGrupper.add(produktGruppe);
		}
	}

	public void removeProduktGruppe(ProduktGruppe pg) {
		if (produktGrupper.contains(pg)) {
			produktGrupper.remove(pg);
		}
	}

	public List<ProduktGruppe> getAllProduktGrupper() {
		return new ArrayList<>(produktGrupper);
	}

// PrisListe metoder ---------------------------------------------
	public void storePrisListe(PrisListe prisliste) {
		if (!prislister.contains(prisliste)) {
			prislister.add(prisliste);
		}
	}

	public void removePrisListe(PrisListe prisliste) {
		if (prislister.contains(prisliste)) {
			prislister.remove(prisliste);
		}
	}

	public ArrayList<PrisListe> getAllPrisLister() {
		return new ArrayList<>(prislister);
	}

// Pris metoder ---------------------------------------------------------------

	public void storePris(Pris pris) {
		if (!priser.contains(pris)) {
			priser.add(pris);
		}
	}

	public void removePris(Pris pris) {
		if (priser.contains(pris)) {
			priser.remove(pris);
		}
	}

//	Salg metoder ---------------------------------------------------------------

	public void storeSalg(Salg salg) {
		if (!solgt.contains(salg)) {
			solgt.add(salg);
		}
	}

	public void removeSalg(Salg salg) {
		if (solgt.contains(salg)) {
			solgt.remove(salg);
		}
	}

	public List<Salg> getAllSalg() {
		return new ArrayList<>(solgt);
	}

//	public ArrayList<Pris> getAllPriser() {
//		ArrayList<Pris> priser = new ArrayList<>();
//		for (PrisListe pl : storage.prislister) {
//			for (Pris p : pl.getAllPriser()) {
//				priser.add(p);
//			}
//		}
//		return priser;
//	}

//	public void removePris(Pris p) {
//		for (PrisListe pl : storage.getAllPrisLister()) {
//			if (p.getPrisListe() == pl) {
//				pl.removePris(p);
//			}
//		}
//	}
}