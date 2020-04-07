package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
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

public class Test_3rd_iteration {
	private Controller controller;
	private Produkt produkt;
	private ProduktGruppe produktGruppe;
	private PrisListe prisliste;
	private Pris pris;
	private SalgsLinje salgsLinje;
	private ArrayList<SalgsLinje> salgsLinjer = new ArrayList<>();
	private Salg salg;
	private Pris p;
	private SalgsLinje sl;

	@Before
	public void setUp() throws Exception {
		controller = Controller.getTestController();
		this.produktGruppe = controller.createProduktGruppe("fadøl");
		this.produkt = controller.createProdukt("Klosterbryg", produktGruppe);
		this.prisliste = controller.createPrisListe("Butik");
		this.pris = controller.createPris(produkt, prisliste, 10);
		this.salgsLinje = controller.createSalgsLinje(3, pris);
		salgsLinjer.add(salgsLinje);
		this.salg = controller.createSalg(salgsLinjer, BetalingsFormer.KONTANT, LocalDate.of(2020, 04, 01));
		this.p = controller.createPris(produkt, prisliste, 20);
		this.sl = controller.createSalgsLinje(7, p);
	}

//	Tests af metoder tilhørende Salg klassen og standard createSalgslinje --------------------------------------------
	@Test
	public void testCreateSalg() {
		assertTrue(salg.getSalgsLinjer().contains(salgsLinje));
		assertEquals(LocalDate.of(2020, 04, 01), salg.getDato());
		assertEquals(BetalingsFormer.KONTANT, salg.getBetalingsform());
	}

	@Test
	public void testBeregnSamletListePris() {
		assertEquals(30, salg.beregnSamletListePris(), 0.01);
		salg.addSalgsLinje(sl);
		assertEquals(170, salg.beregnSamletListePris(), 0.01);
		sl.givRabat(50);
		assertEquals(120, salg.getReelPris(), 0.01);
	}

	@Test
	public void testBeregnSamletRabat() {
		salg.addSalgsLinje(sl);
		sl.givRabat(50);
		assertEquals(50, salg.beregnSamletRabat(), 0.01);
	}

	@Test // Test af metode til at finde prisen på salget medregnet rabatten givet
	public void testGetReelPris() {
		salg.addSalgsLinje(sl);
		sl.givRabat(50);
		assertEquals(120, salg.getReelPris(), 0.01);
	}

//	Tests af specielle createSalgslinje metoder fks for udlejning og gaveæske --------------------------------------------
	
	//Slettet indtil videre....
//	@Test
//	public void testCreateSalgsLinjeKlippekort() {
//		Klippekort k = controller.createKlippekort();
//		SalgsLinje slKlippekort = controller.createSalgsLinje(10, k);
//		assertEquals(10, slKlippekort.getKlipPris(), 0.01);
//		assertEquals(k, slKlippekort.getKlippekort());
//	}

	//Slettet indtil videre....
//	@Test
//	public void testKlippekort() {
//		Klippekort k = controller.createKlippekort();
//		assertEquals(4, k.getAntalKlip());
//		assertTrue(k.getKlipId() >= 1000);
//	}

	@Test
	public void testBrugKlip() {
		Klippekort k = controller.createKlippekort();
		ArrayList<ProduktGruppe> pg = new ArrayList<>();
		pg.add(produktGruppe);
		assertEquals(true, k.brugKlip(pg));
		assertEquals(3, k.getAntalKlip());
		ProduktGruppe pg1 = controller.createProduktGruppe("flaskeøl");
		ProduktGruppe pg2 = controller.createProduktGruppe("flaskeøl");
		pg.add(pg1);
		pg.add(pg2);
		assertEquals(false, k.brugKlip(pg));
		assertEquals(3, k.getAntalKlip());
	}

	@Test
	public void testCreateSalgsLinjeRundvisning() {
		Rundvisning r = controller.createRundvisning(LocalDate.of(2020, 03, 17), LocalTime.of(14, 30));
		SalgsLinje slRundvisning = controller.createSalgsLinje(10, r);
		assertEquals(10, slRundvisning.getRundVisPris(), 0.01);
		assertEquals(r, slRundvisning.getRundvisning());
	}

	@Test
	public void testRundvisning() {
		Rundvisning r = controller.createRundvisning(LocalDate.of(2020, 03, 17), LocalTime.of(14, 30));
		assertEquals(LocalDate.of(2020, 03, 17), r.getDag());
		assertEquals(LocalTime.of(14, 30), r.getTid());
	}

	@Test
	public void testCreateSalgsLinjeGaveæske() {
		ArrayList<Pris> indhold = new ArrayList<>();
		indhold.add(pris);
		Gaveæske g = controller.createGaveæske(indhold);
		SalgsLinje slGaveæske = controller.createSalgsLinje(indhold, g);
		assertEquals(g, slGaveæske.getGaveæske());
		assertTrue(g.getIndhold().contains(pris));
	}
}
