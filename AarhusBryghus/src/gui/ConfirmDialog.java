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
import model.PrisListe;
import model.Produkt;

public class ConfirmDialog extends Stage {
	private Controller controller;
	private TextField txtPris = new TextField();
	private ComboBox<Produkt> comboProdukt = new ComboBox<Produkt>();
	private ComboBox<PrisListe> comboPrisliste = new ComboBox<>();
	private Label lblProdukt = new Label("Vælg produkt:");
	private Label lblPrisliste = new Label("Vælg prisliste:");
	private Label lblPris = new Label("Indtast pris:");
	private Button btnCreate = new Button("Opret pris");

	public ConfirmDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Opret Pris");

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

		pane.add(lblProdukt, 0, 0);
		lblProdukt.setStyle("-fx-font-weight: bold");
		comboProdukt.getItems().setAll(controller.getAllProdukter());
		comboProdukt.getSelectionModel().selectFirst();
		pane.add(comboProdukt, 0, 1);
		pane.add(lblPrisliste, 0, 2);
		lblPrisliste.setStyle("-fx-font-weight: bold");
		comboPrisliste.getItems().setAll(controller.getAllPrisLister());
		comboPrisliste.getSelectionModel().selectFirst();
		pane.add(comboPrisliste, 0, 3);

		pane.add(lblPris, 0, 4);
		lblPris.setStyle("-fx-font-weight: bold");
		pane.add(txtPris, 0, 5);

		pane.add(btnCreate, 0, 6);
		btnCreate.setOnAction(event -> opretAction());
	}

	private void opretAction() {
		if (!txtPris.getText().isEmpty() && comboPrisliste.getSelectionModel().getSelectedItem() != null
				&& comboProdukt.getSelectionModel().getSelectedItem() != null) {
			controller.createPris(comboProdukt.getSelectionModel().getSelectedItem(),
					comboPrisliste.getSelectionModel().getSelectedItem(), Integer.parseInt(txtPris.getText()));
			controller.saveStorage();
			this.hide();
		}
		if (txtPris.getText().isEmpty()) {
			lblPris.setTextFill(Color.RED);
		}
		if (comboPrisliste.getSelectionModel().getSelectedItem() == null) {
			lblPrisliste.setTextFill(Color.RED);
		}
		if (comboProdukt.getSelectionModel().getSelectedItem() == null) {
			lblProdukt.setTextFill(Color.RED);
		}
	}
}
