package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.BetalingsFormer;
import model.Gaveæske;
import model.Klippekort;
import model.Pris;
import model.PrisListe;
import model.Produkt;
import model.ProduktGruppe;
import model.Rundvisning;
import model.Salg;
import model.SalgsLinje;
import model.Udlejning;
import storage.Storage;

public class Controller {
	private static Storage storage;
	private static Controller controller;

	private Controller() {
		Controller.storage = Storage.getStorage();
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

	public List<Salg> getAllSalg() {
		return storage.getAllSalg();
	}

	public List<Udlejning> getAllUdlejning() {
		return storage.getAllUdlejning();
	}

	public List<Klippekort> getAllKlippekort() {
		return storage.getAllKlippekort();
	}

//	Produkt metoder ----------------------------------------------
	public Produkt createProdukt(String navn, ProduktGruppe produktGruppe) {
		try {
			Produkt p = new Produkt(navn, produktGruppe);
			produktGruppe.addProdukt(p);
			storage.storeProdukt(p);
			return p;
		} catch (IllegalArgumentException i) {
			System.out.println("ProduktGruppe muligvis ikke gyldig");
			System.out.println("Message: " + i);
			return null;
		}
	}

	public void deleteProdukt(Produkt produkt) {
		storage.removeProdukt(produkt);
		produkt.removeProduktGruppe();
		;
	}

//	ProduktGruppe metoder ----------------------------------------------
	public ProduktGruppe createProduktGruppe(String navn) {
		try {
			ProduktGruppe pg = new ProduktGruppe(navn);
			storage.storeProduktGruppe(pg);
			return pg;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public void deleteProduktGruppe(ProduktGruppe pg) {
		storage.removeProduktGruppe(pg);
	}

//	Udlejning metoder ----------------------------------------------
	public Udlejning createUdlejning(Produkt pr, LocalDate dato, Double ltr) {
		try {
			Udlejning f = new Udlejning(pr, dato, ltr);
			storage.storeUdlejning(f);
			return f;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

//	Rundvisning metoder ----------------------------------------------
	public Rundvisning createRundvisning(LocalDate dato, LocalTime tid) {
		try {
			Rundvisning r = new Rundvisning(dato, tid);
			storage.storeRundvisning(r);
			return r;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

//	Gaveæske metoder ----------------------------------------------
	public Gaveæske createGaveæske(List<Pris> indhold) {
		try {
			Gaveæske g = new Gaveæske(indhold);
			storage.storeGaveæske(g);
			return g;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

//	PrisListe metoder ----------------------------------------------
	public PrisListe createPrisListe(String navn) {
		try {
			PrisListe pl = new PrisListe(navn);
			storage.storePrisListe(pl);
			return pl;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public void deletePrisListe(PrisListe pl) {
		storage.removePrisListe(pl);
	}

//	Pris metoder ----------------------------------------------
	public Pris createPris(Produkt produkt, PrisListe prisliste, int pris) {
		try {
			Pris samletpris = new Pris(produkt, prisliste, pris);
			prisliste.addPris(samletpris);
			storage.storePris(samletpris);
			return samletpris;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public Pris createPris(Klippekort klip, int pris) {
		try {
			Pris samletpris = new Pris(klip, pris);
			storage.storePris(samletpris);
			return samletpris;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public void deletePris(Pris p) {
		storage.removePris(p);
		p.getPrisListe().removePris(p);
		p.setPrisListe(null);
	}

//	SalgsLinje metoder ----------------------------------------------
	public SalgsLinje createSalgsLinje(int antal, Pris pris) {
		try {
			SalgsLinje sl = new SalgsLinje(antal, pris);
			return sl;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public SalgsLinje createSalgsLinje(double pris, Rundvisning r) {
		try {
			SalgsLinje sl = new SalgsLinje(pris, r);
			return sl;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public SalgsLinje createSalgsLinje(List<Pris> indhold, Gaveæske g) {
		try {
			SalgsLinje sl = new SalgsLinje(indhold, g);
			return sl;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

//	Salg metoder ----------------------------------------------
	public Salg createSalg(ArrayList<SalgsLinje> salgslinjer, BetalingsFormer betalingsform, LocalDate dato) {
		try {
			Salg s = new Salg(salgslinjer, betalingsform, dato);
			return s;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}
	}

	public void storeSalg(Salg s) {
		try {
			storage.storeSalg(s);
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
		}
	}

//	Salg metoder ----------------------------------------------
	public Klippekort createKlippekort() {
		try {
			Klippekort k = new Klippekort();
			storage.storeKlippekort(k);
			return k;
		} catch (IllegalArgumentException i) {
			System.out.println("Message: " + i);
			return null;
		}

	}

	public void removeKlippekort(Klippekort klip) {
		storage.removeKlippekort(klip);
	}

	public void createSomeObjects() {
		// Produktgrupper oprettes
		this.createProduktGruppe("Flaskeøl");
		this.createProduktGruppe("Fadøl");
		this.createProduktGruppe("Øl på fustage");
		this.createProduktGruppe("Spiritus");
		this.createProduktGruppe("Merchandise");
		this.createProduktGruppe("Ikke alkoholisk");
		this.createProduktGruppe("Malt");

		// Produkter oprettes
		this.createProdukt("Klosterbryg", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Sweet Georgia Brown", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Extra Pilsner", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Celebration", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Blondie", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Forårsbryg", storage.getAllProduktGrupper().get(0));
		this.createProdukt("India Pale Ale", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Julebryg", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Juletønden", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Old Strong Ale", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Fregatten Jylland", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Imperial Stout", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Tribute", storage.getAllProduktGrupper().get(0));
		this.createProdukt("Black Monster", storage.getAllProduktGrupper().get(0));

		this.createProdukt("Klosterbryg", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Jazz Classic", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Extra Pilsner", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Celebration", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Blondie", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Forårsbryg", storage.getAllProduktGrupper().get(1));
		this.createProdukt("India Pale Ale", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Julebryg", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Imperial Stout", storage.getAllProduktGrupper().get(1));
		this.createProdukt("Special", storage.getAllProduktGrupper().get(1));

		this.createProdukt("Æblebrus", storage.getAllProduktGrupper().get(5));
		this.createProdukt("Chips", storage.getAllProduktGrupper().get(5));
		this.createProdukt("Peanuts", storage.getAllProduktGrupper().get(5));
		this.createProdukt("Cola", storage.getAllProduktGrupper().get(5));
		this.createProdukt("Nikoline", storage.getAllProduktGrupper().get(5));
		this.createProdukt("7-Up", storage.getAllProduktGrupper().get(5));
		this.createProdukt("Vand", storage.getAllProduktGrupper().get(5));

		this.createProdukt("Spirit of Aarhus", storage.getAllProduktGrupper().get(3));
		this.createProdukt("SOA med pind", storage.getAllProduktGrupper().get(3));
		this.createProdukt("Whisky", storage.getAllProduktGrupper().get(3));
		this.createProdukt("Liquor of Aarhus", storage.getAllProduktGrupper().get(3));

		this.createProdukt("Klosterbryg", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Jazz Classic", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Extra Pilsner", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Celebration", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Blondie", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Forårsbryg", storage.getAllProduktGrupper().get(2));
		this.createProdukt("India Pale Ale", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Julebryg", storage.getAllProduktGrupper().get(2));
		this.createProdukt("Imperial Stout", storage.getAllProduktGrupper().get(2));

		this.createProdukt("25kg sæk", storage.getAllProduktGrupper().get(6));

		this.createProdukt("T-shirt", storage.getAllProduktGrupper().get(4));
		this.createProdukt("Polo", storage.getAllProduktGrupper().get(4));
		this.createProdukt("Cap", storage.getAllProduktGrupper().get(4));
		this.createProdukt("Glas", storage.getAllProduktGrupper().get(4));

		// Prislister oprettes
		this.createPrisListe("Fredagsbar");
		this.createPrisListe("Butikssalg");

		// Priser oprettes
		this.createPris(storage.getAllProdukter().get(0), storage.getAllPrisLister().get(0), 70);
		for (int i = 0; i < 13; i++) {
			this.createPris(storage.getAllProdukter().get(i), storage.getAllPrisLister().get(0), 70);
			this.createPris(storage.getAllProdukter().get(i), storage.getAllPrisLister().get(1), 36);
		}

		this.createPris(storage.getAllProdukter().get(13), storage.getAllPrisLister().get(0), 100);
		this.createPris(storage.getAllProdukter().get(13), storage.getAllPrisLister().get(1), 60);

		for (int i = 14; i < 24; i++) {
			this.createPris(storage.getAllProdukter().get(i), storage.getAllPrisLister().get(0), 38);
		}

		this.createPris(storage.getAllProdukter().get(24), storage.getAllPrisLister().get(0), 15);
		this.createPris(storage.getAllProdukter().get(25), storage.getAllPrisLister().get(0), 10);
		this.createPris(storage.getAllProdukter().get(26), storage.getAllPrisLister().get(0), 15);
		this.createPris(storage.getAllProdukter().get(27), storage.getAllPrisLister().get(0), 15);
		this.createPris(storage.getAllProdukter().get(28), storage.getAllPrisLister().get(0), 15);
		this.createPris(storage.getAllProdukter().get(29), storage.getAllPrisLister().get(0), 15);
		this.createPris(storage.getAllProdukter().get(30), storage.getAllPrisLister().get(0), 10);

		this.createPris(storage.getAllProdukter().get(31), storage.getAllPrisLister().get(0), 300);
		this.createPris(storage.getAllProdukter().get(31), storage.getAllPrisLister().get(1), 300);
		this.createPris(storage.getAllProdukter().get(32), storage.getAllPrisLister().get(0), 350);
		this.createPris(storage.getAllProdukter().get(32), storage.getAllPrisLister().get(1), 350);
		this.createPris(storage.getAllProdukter().get(33), storage.getAllPrisLister().get(0), 500);
		this.createPris(storage.getAllProdukter().get(33), storage.getAllPrisLister().get(1), 500);
		this.createPris(storage.getAllProdukter().get(34), storage.getAllPrisLister().get(0), 175);
		this.createPris(storage.getAllProdukter().get(34), storage.getAllPrisLister().get(1), 175);

		this.createPris(storage.getAllProdukter().get(35), storage.getAllPrisLister().get(1), 775);
		this.createPris(storage.getAllProdukter().get(36), storage.getAllPrisLister().get(1), 625);
		this.createPris(storage.getAllProdukter().get(37), storage.getAllPrisLister().get(1), 575);
		this.createPris(storage.getAllProdukter().get(38), storage.getAllPrisLister().get(1), 775);
		this.createPris(storage.getAllProdukter().get(39), storage.getAllPrisLister().get(1), 700);
		this.createPris(storage.getAllProdukter().get(40), storage.getAllPrisLister().get(1), 775);
		this.createPris(storage.getAllProdukter().get(41), storage.getAllPrisLister().get(1), 775);
		this.createPris(storage.getAllProdukter().get(42), storage.getAllPrisLister().get(1), 775);
		this.createPris(storage.getAllProdukter().get(43), storage.getAllPrisLister().get(1), 775);

		this.createPris(storage.getAllProdukter().get(44), storage.getAllPrisLister().get(1), 300);

		this.createPris(storage.getAllProdukter().get(45), storage.getAllPrisLister().get(0), 70);
		this.createPris(storage.getAllProdukter().get(45), storage.getAllPrisLister().get(1), 70);
		this.createPris(storage.getAllProdukter().get(46), storage.getAllPrisLister().get(0), 100);
		this.createPris(storage.getAllProdukter().get(46), storage.getAllPrisLister().get(1), 100);
		this.createPris(storage.getAllProdukter().get(47), storage.getAllPrisLister().get(0), 30);
		this.createPris(storage.getAllProdukter().get(47), storage.getAllPrisLister().get(1), 30);
		this.createPris(storage.getAllProdukter().get(48), storage.getAllPrisLister().get(1), 15);

//		Serializable metoder
		File saveFile = new File("src/storage.ser");
		if (!saveFile.exists()) {
			this.saveStorage();
		}
	}

	public void loadStorage() {
		try (FileInputStream fileIn = new FileInputStream("src/storage.ser")) {
			try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
				storage = (Storage) in.readObject();
				System.out.println("Storage loaded from file storage.ser.");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error loading storage object.");
				throw new RuntimeException(ex);
			}
		} catch (IOException ex) {
			System.out.println("Error loading storage object.");
			throw new RuntimeException(ex);
		}

	}

	public void saveStorage() {
		try (FileOutputStream fileOut = new FileOutputStream("src/storage.ser")) {
			try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(storage);
				System.out.println("Storage saved in file storage.ser.");
			}
		} catch (IOException ex) {
			System.out.println("Error saving storage object.");
			throw new RuntimeException(ex);
		}
	}
}
