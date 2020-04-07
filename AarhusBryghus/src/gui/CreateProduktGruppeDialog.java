package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateProduktGruppeDialog extends Stage {
	private Controller controller;
	private TextField txtNavn = new TextField();
	private Button btnCreate = new Button("Opret produktgruppe");
	private Label lblNavn = new Label("Indtast navn på produktgruppe:");

	public CreateProduktGruppeDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Opret Produktgruppe");

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
		lblNavn.setStyle("-fx-font-weight: bold");
		pane.add(txtNavn, 0, 1);

		pane.add(btnCreate, 0, 2);
		btnCreate.setOnAction(event -> opretAction());
	}

	private void opretAction() {
		if (!txtNavn.getText().isEmpty()) {
			controller.createProduktGruppe(txtNavn.getText());
			controller.saveStorage();
			this.hide();
		} else if (txtNavn.getText().isEmpty()) {
			lblNavn.setTextFill(Color.RED);
		}
	}
}