package gui;

import java.time.LocalDate;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Pris;
import model.PrisListe;
import model.Produkt;

public class CreateFadølsanlægUdlejningDialog extends Stage {
	private Controller controller;
	private Label lblProd = new Label("Vælg Produkt:");
	private ComboBox<Produkt> produktCombo = new ComboBox<>();
	private Button btnCreate = new Button("Opret Udlejning");
	private DatePicker Dp = new DatePicker();
	private TextField txtSt = new TextField();
	private Label lblSt = new Label("Indtast størrelse i ltr:");

	public CreateFadølsanlægUdlejningDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Udlejning");

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

		pane.add(Dp, 0, 0);
		pane.add(lblProd, 0, 1);
		lblProd.setStyle("-fx-font-weight: bold");
		pane.add(produktCombo, 0, 2);
		lblSt.setStyle("-fx-font-weight: bold");
		pane.add(lblSt, 0, 3);
		pane.add(txtSt, 0, 4);
		pane.add(btnCreate, 0, 5);

		btnCreate.setOnAction(event -> actionCreateFadølsUdlejning());

		Dp.setValue(LocalDate.now());

		for (PrisListe pl : controller.getAllPrisLister()) {
			for (Pris p : pl.getAllPriser()) {
				if (p.getProdukt().getProduktGruppe().getNavn().equals("Øl på fustage")) {
					produktCombo.getItems().add(p.getProdukt());
				}
			}
		}
		produktCombo.getSelectionModel().selectFirst();
	}

	private void actionCreateFadølsUdlejning() {
		if (Double.parseDouble(txtSt.getText()) > 0) {
			controller.createUdlejning(produktCombo.getSelectionModel().getSelectedItem(), Dp.getValue(),
					Double.parseDouble(txtSt.getText()));
			controller.saveStorage();
			this.hide();
		} else
			lblSt.setTextFill(Color.RED);
	}
}
