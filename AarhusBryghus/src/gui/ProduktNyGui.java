package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Produkt;
import model.ProduktGruppe;

public class ProduktNyGui extends GridPane {
	private Controller controller;
	private ListView<ProduktGruppe> produktGruppeList = new ListView<>();
	private ListView<Produkt> produktList = new ListView<>();
	private Button btnGruppe = new Button("Opret ny produktgruppe");
	private Button btnProdukt = new Button("Opret nyt produkt");
	private Button btnDeleteGruppe = new Button("Slet Produktgruppe");
	private Button btnDeleteProdukt = new Button("Slet Produkt");

	public ProduktNyGui(Stage stage, Scene scene) {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Produktgrupper"), 0, 0);
		this.add(produktGruppeList, 0, 1);

		this.add(new Label("Produkter"), 1, 0);
		this.add(produktList, 1, 1);

		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectFirst();
		produktGruppeList.getSelectionModel().selectedIndexProperty().addListener(observable -> updateProduktList());
		produktList.getItems().setAll(produktGruppeList.getSelectionModel().getSelectedItem().getProdukter());

		this.add(btnGruppe, 0, 2);
		this.add(btnProdukt, 1, 2);
		this.add(btnDeleteGruppe, 0, 3);
		this.add(btnDeleteProdukt, 1, 3);
		Button back = new Button("BACK");
		back.setOnAction(e -> stage.setScene(scene));
		this.add(back, 0, 4);

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
		controller.deleteProduktGruppe(produktGruppeList.getSelectionModel().getSelectedItem());
		this.updateGruppeList();
		controller.saveStorage();
	}

	public void actionDeleteProdukt() {
		controller.deleteProdukt(produktList.getSelectionModel().getSelectedItem());
		this.updateProduktList();
		controller.saveStorage();
	}
}
