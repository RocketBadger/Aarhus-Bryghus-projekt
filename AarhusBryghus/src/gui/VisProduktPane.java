package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Produkt;
import model.ProduktGruppe;

public class VisProduktPane extends GridPane {
	private Controller controller;
	private ListView<ProduktGruppe> produktGruppeList = new ListView<>();
	private ListView<Produkt> produktList = new ListView<>();

	public VisProduktPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Vælg produktgruppe"), 0, 0);
		this.add(produktGruppeList, 0, 1);
		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectedIndexProperty().addListener(observable -> {
			produktList.getItems().setAll(produktGruppeList.getSelectionModel().getSelectedItem().getProdukter());
		});
		produktGruppeList.getSelectionModel().selectFirst();

		this.add(new Label("Produkter"), 1, 0);
		this.add(produktList, 1, 1);
//		produktList.getSelectionModel().selectedItemProperty().addListener(observable -> {
//			updateDetails();
//		});
	}

	public void updateDetails() {
//		Produkt produkt = produktList.getSelectionModel().getSelectedItem();
		// TODO hvis nødvendig
	}
}
