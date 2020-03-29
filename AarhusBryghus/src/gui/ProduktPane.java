package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

	public ProduktPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);
		initContent();
	}

	private void initContent() {
//		ColumnConstraints column1 = new ColumnConstraints();
//		column1.setMaxWidth(150);
//		ColumnConstraints column2 = new ColumnConstraints();
//		column2.setMaxWidth(150);
//		this.getColumnConstraints().addAll(column1, column2);

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

		btnGruppe.setOnAction(event -> actionOpenCreateGruppe());
		btnProdukt.setOnAction(event -> actionOpenCreateProdukt());
		btnDeleteGruppe.setOnAction(event -> actionDeleteGruppe());
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
