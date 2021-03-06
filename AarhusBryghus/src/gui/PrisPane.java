package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Pris;
import model.PrisListe;

public class PrisPane extends GridPane {
	private Controller controller;
	private ListView<PrisListe> plView = new ListView<>();
	private ListView<Pris> prisView = new ListView<>();
	private Button btnCreatePrisListe = new Button("Opret ny prisliste");
	private Button btnCreatePris = new Button("Opret ny pris");
	private Button btnDeletePrisliste = new Button("Slet Prisliste");
	private Button btnDeletePris = new Button("Slet pris");

	public PrisPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(50);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblPrisLister = new Label("Prislister");
		lblPrisLister.setId("text");
		this.add(lblPrisLister, 0, 0);
		this.add(plView, 0, 1);

		Label lblProdukterPriser = new Label("Produkter og priser");
		lblProdukterPriser.setId("text");
		this.add(lblProdukterPriser, 1, 0);
		this.add(prisView, 1, 1);

		GridPane leftPane = new GridPane();
		leftPane.setHgap(65);
		leftPane.add(btnCreatePrisListe, 0, 0);
		leftPane.add(btnDeletePrisliste, 1, 0);

		GridPane rightPane = new GridPane();
		rightPane.setHgap(105);
		rightPane.add(btnCreatePris, 0, 0);
		rightPane.add(btnDeletePris, 1, 0);

		this.add(leftPane, 0, 2);
		this.add(rightPane, 1, 2);

		btnCreatePris.setId("secButton");
		btnCreatePrisListe.setId("secButton");
		btnDeletePris.setId("secButton");
		btnDeletePrisliste.setId("secButton");

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
		ConfirmDialog cd = new ConfirmDialog();
		if (cd.Display()) {
			controller.deletePrisListe(plView.getSelectionModel().getSelectedItem());
			this.updatePlView();
			controller.saveStorage();
		}
	}

	public void actionDeletePris() {
		ConfirmDialog cd = new ConfirmDialog();
		if (cd.Display()) {
			if (prisView.getSelectionModel().getSelectedItem() != null) {
				controller.deletePris(prisView.getSelectionModel().getSelectedItem());
				this.updatePrisView();
				controller.saveStorage();
			}
		}
	}
}
