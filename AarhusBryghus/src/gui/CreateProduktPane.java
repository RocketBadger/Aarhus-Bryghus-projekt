package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ProduktGruppe;

public class CreateProduktPane extends GridPane {
	private Controller controller;
	private TextField txtNavn = new TextField();
	private TextField txtNr = new TextField();
	private ListView<ProduktGruppe> produktGruppeList = new ListView<>();

	public CreateProduktPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Indtast produktnavn"), 0, 0);
		this.add(txtNavn, 0, 1);
		this.add(txtNr, 1, 1);
		this.add(new Label("Indtast produktnummer"), 1, 0);
		this.add(new Label("Vælg produktgruppe"), 0, 2);

		this.add(produktGruppeList, 0, 3);
		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectFirst();
	}
}
