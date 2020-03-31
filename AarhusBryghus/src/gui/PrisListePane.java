package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Pris;
import model.PrisListe;

public class PrisListePane extends GridPane {
	private Controller controller;
	private ListView<PrisListe> plView = new ListView<>();
	private ListView<Pris> prisView = new ListView<>();
	private Button btnCreatePrisListe = new Button("Opret ny prisliste");
	private Button btnCreatePris = new Button("Opret ny pris");
	private Button btnDeletePrisliste = new Button("Slet Prisliste");
	private Button btnDeletePris = new Button("Slet pris");

	public PrisListePane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Prislister"), 0, 0);
		this.add(plView, 0, 1);

		this.add(new Label("Produkter og priser"), 1, 0);
		this.add(prisView, 1, 1);

		this.add(btnCreatePrisListe, 0, 2);
		this.add(btnCreatePris, 1, 2);
		this.add(btnDeletePrisliste, 0, 3);
		this.add(btnDeletePris, 1, 3);

		btnCreatePrisListe.setOnAction(event -> actionOpenCreatePrisliste());
		btnCreatePris.setOnAction(event -> actionOpenCreatePris());
		btnDeletePrisliste.setOnAction(event -> actionDeletePrisliste());
		btnDeletePris.setOnAction(event -> actionDeletePris());

		plView.getItems().setAll(controller.getAllPrisLister());
		plView.getSelectionModel().selectFirst();
		plView.getSelectionModel().selectedIndexProperty().addListener(observable -> updatePrisView());
		prisView.getItems().setAll(plView.getSelectionModel().getSelectedItem().getAllPriser());

	}

	public void actionOpenCreatePrisliste() {
		CreatePrislisteDialog di = new CreatePrislisteDialog();
		di.setOnHidden(event -> this.updatePlView());
		di.showAndWait();
	}

	public void actionOpenCreatePris() {
		CreatePrisDialog di = new CreatePrisDialog();
		di.setOnHidden(event -> this.updatePrisView());
		di.showAndWait();
	}

	public void updatePlView() {
		plView.getItems().setAll(controller.getAllPrisLister());
		plView.getSelectionModel().selectFirst();
	}

	public void updatePrisView() {
		int selected = 0;
		if (plView.getSelectionModel().getSelectedItem() != null) {
			selected = plView.getSelectionModel().getSelectedIndex();
		}
		plView.getSelectionModel().select(selected);
		prisView.getItems().setAll(plView.getSelectionModel().getSelectedItem().getAllPriser());
	}

	public void actionDeletePrisliste() {
		Alert conf = new Alert(AlertType.CONFIRMATION,
				"Slet " + plView.getSelectionModel().getSelectedItem() + "? (Dette sletter også prislistens priser",
				ButtonType.YES, ButtonType.CANCEL);
		conf.showAndWait().ifPresent(response -> {
			if (response == ButtonType.YES) {
				controller.deletePrisListe(plView.getSelectionModel().getSelectedItem());
				this.updatePlView();
				controller.saveStorage();
			}
		});
	}

	public void actionDeletePris() {
		Alert conf = new Alert(AlertType.CONFIRMATION, "Slet " + plView.getSelectionModel().getSelectedItem() + "?",
				ButtonType.YES, ButtonType.CANCEL);
		conf.showAndWait().ifPresent(response -> {
			if (response == ButtonType.YES) {
				controller.deletePris(prisView.getSelectionModel().getSelectedItem());
				this.updatePrisView();
				controller.saveStorage();
			}
		});
	}
}
