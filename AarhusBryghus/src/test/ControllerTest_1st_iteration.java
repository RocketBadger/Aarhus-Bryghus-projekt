package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.Produkt;
import model.ProduktGruppe;

public class ControllerTest_1st_iteration {
	private Controller controller;
	private Produkt produkt;
	private ProduktGruppe produktGruppe;

	@Before
	public void setUp() throws Exception {
		controller = Controller.getTestController();
		this.produktGruppe = controller.createProduktGruppe("Øl", "Det øl");
		this.produkt = controller.createProdukt("Klosterbryg", "1", produktGruppe);
	}

	@Test
	public void test() {
		// Test ProduktGruppe oprettelse og forbindelse
		assertEquals("Øl", produktGruppe.getType());
		assertEquals("Det øl", produktGruppe.getBeskrivelse());
		assertTrue(produktGruppe.getProdukter().contains(produkt));
		// Test Produkt oprettelse og forbindelse
		assertEquals("Klosterbryg", produkt.getNavn());
		assertEquals("1", produkt.getNr());
		assertTrue(produkt.getProduktGruppe() == produktGruppe);
	}

}
