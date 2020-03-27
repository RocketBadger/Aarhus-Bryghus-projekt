package gui;


import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Pris;
import model.PrisListe;

public class PrisListePane extends GridPane {
	private Controller controller;
	private ListView<PrisListe> plView = new ListView<>();
	private ListView<Pris> prisView = new ListView<>();

	
	public PrisListePane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);
		
		this.add(new Label("Prislister"), 0, 0);
		this.add(plView, 0, 1);
		plView.getItems().setAll(controller.getAllPrisLister());
		
		this.add(new Label("Produkter og priser"), 1, 0);
		this.add(prisView, 1, 1);
		
		
	}
}
