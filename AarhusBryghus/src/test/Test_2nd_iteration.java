package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.Pris;
import model.PrisListe;
import model.Produkt;
import model.ProduktGruppe;

public class Test_2nd_iteration {
	private Controller controller;
	private Produkt produkt;
	private ProduktGruppe produktGruppe;
	private PrisListe prisliste;
	private Pris pris;

	@Before
	public void setUp() throws Exception {
		controller = Controller.getTestController();
		this.produktGruppe = controller.createProduktGruppe("Øl");
		this.produkt = controller.createProdukt("Klosterbryg", produktGruppe);
		this.prisliste = controller.createPrisListe("Butik");
		this.pris = controller.createPris(produkt, prisliste, 10);
	}

	@Test
	public void textCreatePrisliste() {
		assertEquals("Butik", prisliste.getNavn());
		assertTrue(prisliste.getAllPriser().contains(pris));
	}

	@Test
	public void testCreatePris() {
		assertEquals(produkt, pris.getProdukt());
		assertEquals(prisliste, pris.getPrisListe());
		assertEquals(10, pris.getPris(), 0.1);
	}

	@Test
	public void testStorage() {
		assertTrue(controller.getAllPrisLister().contains(prisliste));
	}
}
