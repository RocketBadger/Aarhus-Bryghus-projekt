package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Klippekort;
import model.Produkt;

public class OversigtPane extends GridPane {
	private Controller controller;
	private Button btnUbrugteKlippekort = new Button("Vis ubrugte klippekort");
	private Label lblAntal = new Label();
	private DatePicker datePicker = new DatePicker();
	private ListView<Produkt> lvProdukt = new ListView<Produkt>();
	private ListView<Produkt> lvKlippekort = new ListView<Produkt>();

	public OversigtPane() {
		this.controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);
		GridPane gpLeft = new GridPane();
		GridPane gpRight = new GridPane();
		
		this.add(gpLeft, 0, 0);
		this.add(gpRight, 1, 0);

		gpLeft.add(btnUbrugteKlippekort, 0, 0);
		
		btnUbrugteKlippekort.setOnAction(e -> {
			int antal = 0;
			if (controller.getAllKlippekort() != null) {
				for (Klippekort k : controller.getAllKlippekort()) {
					k.getAntalKlip();
					antal++;
				}	
			}
			lblAntal.setText(antal+"");
		});
	}
}
