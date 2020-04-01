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
import model.Produkt;
import model.ProduktGruppe;

public class ProduktPane extends GridPane {
	private Controller controller;
	private ListView<ProduktGruppe> produktGruppeList = new ListView<>();
	private ListView<Produkt> produktList = new ListView<>();
	private Button btnGruppe = new Button("Opret ny produktgruppe");
	private Button btnProdukt = new Button("Opret nyt produkt");
	private Button btnDeleteGruppe = new Button("Slet Produktgruppe");
	private Button btnDeleteProdukt = new Button("Slet Produkt");
	private Label lblGruppe = new Label("Produktgrupper");
	private Label lblProdukt = new Label("Produkter");

	public ProduktPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		lblGruppe.setStyle("-fx-font-weight: bold");
		this.add(lblGruppe, 0, 0);
		this.add(produktGruppeList, 0, 1);

		lblProdukt.setStyle("-fx-font-weight: bold");
		this.add(lblProdukt, 1, 0);
		this.add(produktList, 1, 1);

		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectFirst();
		produktGruppeList.getSelectionModel().selectedIndexProperty().addListener(observable -> updateProduktList());
		produktList.getItems().setAll(produktGruppeList.getSelectionModel().getSelectedItem().getProdukter());

		GridPane leftPane = new GridPane();
		leftPane.setHgap(10);
		leftPane.add(btnGruppe, 0, 0);
		leftPane.add(btnDeleteGruppe, 1, 0);
		btnDeleteGruppe.setStyle("-fx-background-color: crimson");

		GridPane rightPane = new GridPane();
		rightPane.setHgap(55);
		rightPane.add(btnProdukt, 0, 0);
		rightPane.add(btnDeleteProdukt, 1, 0);
		btnDeleteProdukt.setStyle("-fx-background-color: crimson");

		this.add(leftPane, 0, 2);
		this.add(rightPane, 1, 2);

		btnGruppe.setOnAction(event -> actionOpenCreateGruppe());
		btnProdukt.setOnAction(event -> actionOpenCreateProdukt());
		btnDeleteGruppe.setOnAction(event -> actionDeleteGruppe());
		btnDeleteProdukt.setOnAction(event -> actionDeleteProdukt());
	}

	public void updateGruppeList() {
		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectFirst();
	}

	public void updateProduktList() {
		int selected = 0;
		if (produktGruppeList.getSelectionModel().getSelectedItem() != null) {
			selected = produktGruppeList.getSelectionModel().getSelectedIndex();
		}
		produktGruppeList.getSelectionModel().select(selected);
		produktList.getItems().setAll(produktGruppeList.getSelectionModel().getSelectedItem().getProdukter());
	}

	public void actionOpenCreateGruppe() {
		CreateProduktGruppeDialog di = new CreateProduktGruppeDialog();
		di.setOnHidden(event -> this.updateGruppeList());
		di.showAndWait();
	}

	public void actionOpenCreateProdukt() {
		CreateProduktDialog di = new CreateProduktDialog();
		di.setOnHidden(event -> this.updateProduktList());
		di.showAndWait();
	}

	public void actionDeleteGruppe() {
		Alert conf = new Alert(AlertType.CONFIRMATION, "Slet " + produktGruppeList.getSelectionModel().getSelectedItem()
				+ "? (Dette sletter også produktgruppens varer)", ButtonType.YES, ButtonType.CANCEL);
		conf.showAndWait().ifPresent(response -> {
			if (response == ButtonType.YES) {
				controller.deleteProduktGruppe(produktGruppeList.getSelectionModel().getSelectedItem());
				this.updateGruppeList();
				controller.saveStorage();
			}
		});
	}

	public void actionDeleteProdukt() {
		Alert conf = new Alert(AlertType.CONFIRMATION,
				"Slet " + produktList.getSelectionModel().getSelectedItem() + "?", ButtonType.YES, ButtonType.CANCEL);
		conf.showAndWait().ifPresent(response -> {
			if (response == ButtonType.YES) {
				controller.deleteProdukt(produktList.getSelectionModel().getSelectedItem());
				this.updateProduktList();
				controller.saveStorage();
			}
		});
	}
}
