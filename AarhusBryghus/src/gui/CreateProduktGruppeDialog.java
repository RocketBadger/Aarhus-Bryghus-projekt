package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreateProduktGruppeDialog extends Stage {
	@SuppressWarnings("unused")
	private Controller controller;
	private TextField txtType = new TextField();
	private TextField txtBeskrivelse = new TextField();
	private Button btnCreate = new Button("Opret produktgruppe");

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

		pane.add(new Label("Indtast type:"), 0, 0);
		pane.add(txtType, 1, 0);
		pane.add(new Label("Indtast beskrivelse:"), 0, 1);
		pane.add(txtBeskrivelse, 1, 1);

		pane.add(btnCreate, 1, 2);
		btnCreate.setOnAction(event -> opretAction());
	}

	private void opretAction() {

	}
}
