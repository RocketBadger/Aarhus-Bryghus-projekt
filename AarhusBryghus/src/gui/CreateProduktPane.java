package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CreateProduktPane extends GridPane {
	private Controller controller;

	public CreateProduktPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Opret produkt"), 0, 0);
	}
}
