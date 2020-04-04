package storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Gaveæske;
import model.Pris;
import model.PrisListe;
import model.Produkt;
import model.ProduktGruppe;
import model.Rundvisning;
import model.Salg;
import model.Udlejning;

public class Storage implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Storage storage;
	private List<Produkt> produkter;
	private List<ProduktGruppe> produktGrupper;
	private List<PrisListe> prislister;
	private List<Pris> priser;
	private List<Salg> solgt;
	private List<Udlejning> udlejninger;
	private List<Rundvisning> rundvisninger;
	private List<Gaveæske> gaveæsker;

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
		udlejninger = new ArrayList<Udlejning>();
		rundvisninger = new ArrayList<Rundvisning>();
		gaveæsker = new ArrayList<Gaveæske>();
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

//	Udlejning metoder ---------------------------------------------------------------
	public void storeUdlejning(Udlejning ud) {
		if (!udlejninger.contains(ud)) {
			udlejninger.add(ud);
		}
	}

	public void removeUdlejning(Udlejning ud) {
		if (udlejninger.contains(ud)) {
			udlejninger.remove(ud);
		}
	}

	public List<Udlejning> getAllUdlejning() {
		return new ArrayList<>(udlejninger);
	}

//	Rundvisning metoder ---------------------------------------------------------------
	public void storeRundvisning(Rundvisning r) {
		if (!rundvisninger.contains(r)) {
			rundvisninger.add(r);
		}
	}

	public List<Rundvisning> getAllRundvisning() {
		return new ArrayList<>(rundvisninger);
	}

//	Gaveæske metoder ---------------------------------------------------------------
	public void storeGaveæske(Gaveæske g) {
		if (!gaveæsker.contains(g)) {
			gaveæsker.add(g);
		}
	}

	public List<Gaveæske> getAllGaveæske() {
		return new ArrayList<>(gaveæsker);
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