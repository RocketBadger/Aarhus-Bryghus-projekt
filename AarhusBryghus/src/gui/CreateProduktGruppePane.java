package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateProduktGruppePane extends GridPane {
	private Controller controller;
	private TextField txtType = new TextField();
	private TextField txtBeskrivelse = new TextField();

	// OBS eksperimenterer med en dialog i stedet for en hel tab

	public CreateProduktGruppePane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Opret produktgruppe"), 0, 0);
	}
}
