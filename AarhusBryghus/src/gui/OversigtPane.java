package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OversigtPane extends GridPane {
	private Controller controller;
	private Button btnVisSalgPaaDag = new Button("Vis Salg");

	public OversigtPane() {
		this.controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);
		
		this.add(btnVisSalgPaaDag, 0, 0);
	}
}
