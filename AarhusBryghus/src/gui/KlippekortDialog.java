package gui;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Klippekort;
import model.Produkt;
import model.ProduktGruppe;
import model.SalgsLinje;

public class KlippekortDialog extends Stage {

	private Controller controller;
	private TextField txfAntal = new TextField();
	private Label lblAntal = new Label("Antal klippekort: ");
	private Button btnOk = new Button("Ok");
	private List<SalgsLinje> linjer = new ArrayList<>();

	public KlippekortDialog() {
		this.controller = Controller.getController();

		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Opret klippekort");
		this.setMinWidth(200);

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		this.initContent(pane);
		this.setScene(scene);
	}

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(20));
		pane.setHgap(20);
		pane.setVgap(10);

		pane.add(lblAntal, 0, 0);
		pane.add(txfAntal, 0, 1);

		btnOk.setOnAction(e -> actionCreateKlippekort());
		pane.add(btnOk, 0, 2);
	}

	private void actionCreateKlippekort() {
		int antal = 0;
		if (!txfAntal.getText().trim().isEmpty()) {
			antal = Integer.parseInt(txfAntal.getText().trim());
			for (int i = 1; i <= antal; i++) {
				SalgsLinje sl = controller.createSalgsLinje(100, controller.createKlippekort());
				linjer.add(sl);
				controller.saveStorage();
				}
			}
			this.hide();
	}

	protected ArrayList<SalgsLinje> passLinjer() {
		return new ArrayList<>(linjer);
	}
}
