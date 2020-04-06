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

	public SalgsLinje createSalgsLinje(double pris, Klippekort klip) {
		try {
			SalgsLinje sl = new SalgsLinje(pris, klip);
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
//			storage.storeSalg(s);
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
