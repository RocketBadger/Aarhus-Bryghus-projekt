package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CreateProduktGruppePane extends GridPane {
	private Controller controller;

	public CreateProduktGruppePane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Opret produktgruppe"), 0, 0);
	}
}
