package gui;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Pris;
import model.PrisListe;
import model.SalgsLinje;

public class CreateSalgslinjeDialog extends Stage {
	private Controller controller;
	private ComboBox<PrisListe> comboPrisListe = new ComboBox<>();
	private Label lblLCombo = new Label("Vælg prisliste");
	private ComboBox<Pris> comboPris = new ComboBox<>();
	private Label lblPCombo = new Label("Vælg vare");
	private Button btnAnother = new Button("Tilføj vare");
	private Button btnNoMore = new Button("Afslut");
	private Label lblAntal = new Label("Antal");
	private TextField txtAntal = new TextField();
	private List<SalgsLinje> linjer = new ArrayList<>();

	public CreateSalgslinjeDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Tilføj Vare");

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

		ColumnConstraints c1 = new ColumnConstraints();
		c1.setHalignment(HPos.CENTER);
		pane.getColumnConstraints().add(c1);

		comboPrisListe.getItems().setAll(controller.getAllPrisLister());
		comboPrisListe.getSelectionModel().selectFirst();
		comboPrisListe.setOnAction(event -> updateComboPris());

		comboPris.getItems().setAll(comboPrisListe.getSelectionModel().getSelectedItem().getAllPriser());
		comboPris.getSelectionModel().selectFirst();

		btnAnother.setOnAction(event -> actionCreateSalgsLinje());
		btnNoMore.setOnAction(event -> this.hide());

		pane.add(lblLCombo, 0, 0);
		lblLCombo.setStyle("-fx-font-weight: bold");
		pane.add(comboPrisListe, 0, 1);
		pane.add(lblPCombo, 0, 2);
		lblPCombo.setStyle("-fx-font-weight: bold");
		pane.add(comboPris, 0, 3);
		pane.add(lblAntal, 0, 4);
		lblAntal.setStyle("-fx-font-weight: bold");
		pane.add(txtAntal, 0, 5);

		pane.add(btnAnother, 0, 7);
		pane.add(btnNoMore, 0, 8);
	}

	private void actionCreateSalgsLinje() {
		if (!txtAntal.getText().isEmpty()) {
			SalgsLinje sl = controller.createSalgsLinje(Integer.parseInt(txtAntal.getText()),
					comboPris.getSelectionModel().getSelectedItem());
			linjer.add(sl);
			txtAntal.clear();
		} else
			lblAntal.setTextFill(Color.RED);
	}

	private void updateComboPris() {
		comboPris.getItems().setAll(comboPrisListe.getSelectionModel().getSelectedItem().getAllPriser());
		comboPris.getSelectionModel().selectFirst();
	}

	protected ArrayList<SalgsLinje> passLinjer() {
		return new ArrayList<>(linjer);
	}
}
