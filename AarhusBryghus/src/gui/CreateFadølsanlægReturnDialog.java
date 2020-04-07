package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import model.SalgsLinje;
import model.Udlejning;

public class CreateFadølsanlægReturnDialog extends Stage {
	private Controller controller;
	private ComboBox<Udlejning> udlejninger = new ComboBox<>();
	private DatePicker endDate = new DatePicker();
	private Label lblDate = new Label("Dato for retur");
	private Label lblUdl = new Label("Vælg udlejning");
	private Label lblRest = new Label("Indtast rest i litr");
	private TextField txtRest = new TextField();
	private Button btnDone = new Button("Returnér");

	private List<SalgsLinje> linjer = new ArrayList<>();

	public CreateFadølsanlægReturnDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Returnering");

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

		pane.add(lblDate, 0, 0);
		lblDate.setStyle("-fx-font-weight: bold");
		pane.add(endDate, 0, 1);
		pane.add(lblRest, 0, 2);
		lblRest.setStyle("-fx-font-weight: bold");
		pane.add(txtRest, 0, 3);
		pane.add(lblUdl, 0, 5);
		lblUdl.setStyle("-fx-font-weight: bold");
		pane.add(udlejninger, 0, 6);
		pane.add(btnDone, 0, 8);

		endDate.setValue(LocalDate.now());

		for (Udlejning u : controller.getAllUdlejning()) {
			if (u.getReturDato() == null) {
				udlejninger.getItems().add(u);
			}
		}

		udlejninger.getSelectionModel().selectFirst();
		btnDone.setOnAction(event -> actionReturnUdlejning());
	}

	private void actionReturnUdlejning() {
		if (Double.parseDouble(txtRest.getText()) > -1) {
			Pris p = null;
			for (PrisListe pl : controller.getAllPrisLister()) {
				for (Pris pr : pl.getAllPriser()) {
					if (pr.getProdukt().equals(udlejninger.getSelectionModel().getSelectedItem().getProdukt())) {
						p = pr;
					}
				}
			}
			udlejninger.getSelectionModel().getSelectedItem().setReturDato(endDate.getValue());
			udlejninger.getSelectionModel().getSelectedItem().calcRemainder(Double.parseDouble(txtRest.getText()));
			SalgsLinje sl = controller
					.createSalgsLinje(udlejninger.getSelectionModel().getSelectedItem().getUdlejningsDato()
							.until(udlejninger.getSelectionModel().getSelectedItem().getReturDato()).getDays() + 1, p);
			linjer.add(sl);
			controller.saveStorage();
			this.hide();
		} else
			lblRest.setTextFill(Color.RED);
	}

	protected ArrayList<SalgsLinje> passLinjer() {
		return new ArrayList<>(linjer);
	}
}
