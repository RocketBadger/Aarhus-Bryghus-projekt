package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Klippekort;

public class OversigtPane extends GridPane {
	private Controller controller;
	private Button btnUbrugteKlippekort = new Button("Vis ubrugte klippekort");
	private Label lblAntal = new Label();

	public OversigtPane() {
		this.controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);
		
		
		this.add(btnUbrugteKlippekort, 0, 0);
		
		btnUbrugteKlippekort.setOnAction(e -> {
			int antal = 0;
			if (controller.getAllKlippekort() != null) {
				for (Klippekort k : controller.getAllKlippekort()) {
					antal++;
					System.out.println(k.getKlipId());
				}	
			}
			lblAntal.setText(antal+"");
			this.add(lblAntal, 0, 1);
		});
	}
}
