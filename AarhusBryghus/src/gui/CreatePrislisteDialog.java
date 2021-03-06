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

public class CreatePrislisteDialog extends Stage {
	private Controller controller;
	private TextField txtNvn = new TextField();
	private Label lblNvn = new Label("Indtast navn:");
	private Button btnCreate = new Button("Opret");

	public CreatePrislisteDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Opret Prisliste");

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

		pane.add(lblNvn, 0, 0);
		lblNvn.setStyle("-fx-font-weight: bold");
		pane.add(txtNvn, 0, 1);
		pane.add(btnCreate, 0, 2);

		btnCreate.setOnAction(event -> opretAction());
	}

	private void opretAction() {
		if (!txtNvn.getText().isEmpty()) {
			controller.createPrisListe(txtNvn.getText());
			controller.saveStorage();
			this.hide();
		} else if (txtNvn.getText().isEmpty()) {
			lblNvn.setTextFill(Color.RED);
		}
	}
}
