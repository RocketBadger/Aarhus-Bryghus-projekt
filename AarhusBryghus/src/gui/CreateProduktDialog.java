package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProduktGruppe;

public class CreateProduktDialog extends Stage {
	private Controller controller;
	private TextField txtNavn = new TextField();
	private Label lblNavn = new Label("Indtast produktnavn:");
	private ComboBox<ProduktGruppe> produktGruppeCombo = new ComboBox<>();
	private Button btnCreate = new Button("Opret Produkt");

	public CreateProduktDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Opret Produkt");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		this.initContent(pane);
		this.setScene(scene);
	}

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(20));
		pane.setHgap(20);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		pane.add(lblNavn, 0, 0);
		pane.add(txtNavn, 0, 1);
		pane.add(new Label("Vælg produktgruppe:"), 0, 2);
		pane.add(produktGruppeCombo, 0, 3);
		pane.add(btnCreate, 0, 4);

		btnCreate.setOnAction(event -> actionCreateProdukt());

		produktGruppeCombo.getItems().setAll(controller.getAllProduktGrupper());
		produktGruppeCombo.getSelectionModel().selectFirst();
	}

	public void actionCreateProdukt() {
		if (!txtNavn.getText().isEmpty()) {
			controller.createProdukt(txtNavn.getText(), produktGruppeCombo.getSelectionModel().getSelectedItem());
			controller.saveStorage();
			this.hide();
		} else
			lblNavn.setTextFill(Color.RED);
	}
}
