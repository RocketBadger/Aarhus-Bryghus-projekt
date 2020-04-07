package gui;

import java.util.ArrayList;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Gaveæske;
import model.Pris;
import model.PrisListe;
import model.SalgsLinje;

public class CreateGaveæskeDialog extends Stage {
	private Controller controller;
	private Label lblPrisListe = new Label("Vælg prislisten:");
	private Label lblProd = new Label("Vælg produkter at tilføje (tryk CTRL imens for multiple selection)");

	private ComboBox<PrisListe> lister = new ComboBox<>();
	private ListView<Pris> produkter = new ListView<>();

	private Button btnCreate = new Button("Tilføj highlightede varer");
	private Button btnDone = new Button("Afslut");

	private ArrayList<Pris> GÆindhold = new ArrayList<>();
	private ArrayList<SalgsLinje> gaveæskeSL = new ArrayList<>();

	public CreateGaveæskeDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Gaveæske");

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

		pane.add(lblPrisListe, 0, 0);
		pane.add(lister, 1, 0);
		pane.add(lblProd, 0, 1);
		pane.add(produkter, 0, 2, 2, 1);
		pane.add(btnCreate, 0, 4);
		pane.add(btnDone, 1, 4);

		lister.getItems().setAll(controller.getAllPrisLister());
		lister.getSelectionModel().selectFirst();
		lister.getSelectionModel().selectedIndexProperty().addListener(observable -> updateProduktList());

		produkter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		produkter.getItems().setAll(lister.getSelectionModel().getSelectedItem().getAllPriser());

		btnCreate.setOnAction(event -> actionTilføjGaveæske());
		btnDone.setOnAction(event -> actionDone());
	}

	private void actionTilføjGaveæske() {
		GÆindhold.addAll(produkter.getSelectionModel().getSelectedItems());
		produkter.getSelectionModel().clearSelection();
	}

	private void actionDone() {
		if (!GÆindhold.isEmpty()) {
			Gaveæske g = controller.createGaveæske(GÆindhold);
			SalgsLinje sl = controller.createSalgsLinje(GÆindhold, g);
			gaveæskeSL.add(sl);
		}
		this.hide();
	}

	private void updateProduktList() {
		produkter.getItems().setAll(lister.getSelectionModel().getSelectedItem().getAllPriser());
	}

	protected ArrayList<SalgsLinje> passLinjer() {
		return new ArrayList<>(gaveæskeSL);
	}
}