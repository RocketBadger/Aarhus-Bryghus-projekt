package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.ProduktGruppe;

public class CreateProduktPane extends GridPane {
	private Controller controller;
	private TextField txtNavn = new TextField();
	private ListView<ProduktGruppe> produktGruppeList = new ListView<>();
	private Button btnGruppe = new Button("Opret ny produktgruppe (Åbner nyt vindue)");
	private Button btnCreate = new Button("Opret nyt produkt");
	private Label lblNavn = new Label("Indtast produktnavn:");

	public CreateProduktPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(lblNavn, 0, 0);
		this.add(txtNavn, 0, 1);
		this.add(new Label("Vælg produktgruppe:"), 0, 2);

		this.add(produktGruppeList, 0, 3);
		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectFirst();

		this.add(btnGruppe, 0, 4);
		this.add(btnCreate, 1, 3);

		btnGruppe.setOnAction(event -> actionOpenCreateGruppe());
		btnCreate.setOnAction(event -> actionCreateProdukt());

	}

	public void actionOpenCreateGruppe() {
		CreateProduktGruppeDialog di = new CreateProduktGruppeDialog();
		di.setOnHidden(event -> this.updateGruppeList());
		di.showAndWait();
	}

	public void actionCreateProdukt() {
		if (controller.parseTextField(txtNavn)) {

			controller.createProdukt(txtNavn.getText(), produktGruppeList.getSelectionModel().getSelectedItem());
			txtNavn.clear();
		} else
//				throw new IllegalArgumentException("Der skal angives et gyldigt tal");
			lblNavn.setTextFill(Color.RED);
	}

	public void updateGruppeList() {
		produktGruppeList.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeList.getSelectionModel().selectFirst();
	}
}
