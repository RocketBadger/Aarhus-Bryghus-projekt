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
	private TextField txtType = new TextField();
	private TextField txtBeskrivelse = new TextField();
	private Button btnCreate = new Button("Opret produktgruppe");
	private Button btnNvm = new Button("Fortryd");
	private Label lblType = new Label("Indast type");
	private Label lblBeskrivelse = new Label("Indtast beskrivelse");

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

		pane.add(lblType, 0, 0);
		pane.add(txtType, 1, 0);
		pane.add(lblBeskrivelse, 0, 1);
		pane.add(txtBeskrivelse, 1, 1);

		pane.add(btnNvm, 0, 2);
		btnNvm.setOnAction(event -> this.hide());
		pane.add(btnCreate, 1, 2);
		btnCreate.setOnAction(event -> opretAction());
	}

	private void opretAction() {
		if (controller.parseTextField(txtType) && controller.parseTextField(txtBeskrivelse)) {
			controller.createProduktGruppe(txtType.getText(), txtBeskrivelse.getText());
			this.hide();
		} else if (!controller.parseTextField(txtType) && !controller.parseTextField(txtBeskrivelse)) {
			lblType.setTextFill(Color.RED);
			lblBeskrivelse.setTextFill(Color.RED);
		} else if (!controller.parseTextField(txtType)) {
			lblType.setTextFill(Color.RED);
		} else if (!controller.parseTextField(txtBeskrivelse)) {
			lblBeskrivelse.setTextFill(Color.RED);
		}
	}
}
