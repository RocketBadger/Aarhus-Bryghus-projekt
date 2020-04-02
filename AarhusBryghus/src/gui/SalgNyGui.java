package gui;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.BetalingsFormer;
import model.Salg;
import model.SalgsLinje;

public class SalgNyGui extends GridPane {
	private Controller controller;

	private ListView<SalgsLinje> linjeView = new ListView<>();
	private ToggleGroup toggles = new ToggleGroup();
	private RadioButton rbKontant = new RadioButton("Kontant");
	private RadioButton rbDan = new RadioButton("Dankort");
	private RadioButton rbRegning = new RadioButton("Regning");
	private RadioButton rbMobile = new RadioButton("MobilePay");
	private RadioButton rbKlip = new RadioButton("Klippekort");
	private DatePicker salgsDato = new DatePicker();
	private Button btnAddSL = new Button("Tilføj vare til kurv");
	private Button btnDeleteSL = new Button("Fjern vare fra kurv");
	private Button btnCreateSalg = new Button("Afslut salg");
	private TextField txtTilBetaling = new TextField();
	private Label lblBetalingsform = new Label("Angiv betalingsform");
	private Label lblDato = new Label("Angiv dato");
	private Label lblVarer = new Label("Varer i kurven");
	private Label lblTilBetaling = new Label("Til betaling: ");
	private Label lblRabat = new Label("Rabat? (per styk. Vælg et produkt)");
	private ToggleGroup toggleRabat = new ToggleGroup();
	private RadioButton rbFlad = new RadioButton("Flad");
	private RadioButton rbProcent = new RadioButton("Procent");
	private TextField txtRabat = new TextField();

	private ArrayList<SalgsLinje> salgsLinjer = new ArrayList<>();
	private Salg s = null;
	String kr = " kr";
	double i = 0;
	double givetRabat = 0;

	public SalgNyGui(Stage stage, Scene scene) {
		controller = Controller.getController();
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		rbKontant.setUserData(BetalingsFormer.KONTANT);
		rbDan.setUserData(BetalingsFormer.DANKORT);
		rbRegning.setUserData(BetalingsFormer.REGNING);
		rbMobile.setUserData(BetalingsFormer.MOBILEPAY);
		rbKlip.setUserData(BetalingsFormer.KLIPPEKORT);
		rbFlad.setUserData("flad");
		rbProcent.setUserData("procent");

		lblBetalingsform.setId("text");
		lblDato.setId("text");
		lblVarer.setId("text");
		rbKontant.setId("text");
		rbDan.setId("text");
		rbRegning.setId("text");
		rbMobile.setId("text");
		rbKlip.setId("text");
		lblTilBetaling.setId("text");
		lblRabat.setId("text");
		rbFlad.setId("text");
		rbProcent.setId("text");

		toggles.getToggles().addAll(rbKontant, rbDan, rbRegning, rbMobile, rbKlip);
		toggleRabat.getToggles().addAll(rbFlad, rbProcent);

		salgsDato.setValue(LocalDate.now());

		linjeView.setMaxSize(270, 290);

		btnCreateSalg.setMinSize(250, 20);

		txtTilBetaling.setEditable(false);
		txtTilBetaling.setText(i + kr);

		txtRabat.setDisable(true);
		txtRabat.setVisible(false);
		rbFlad.setVisible(false);
		rbProcent.setVisible(false);
		lblRabat.setVisible(false);

		GridPane leftPane = new GridPane();
		leftPane.setVgap(10);
		leftPane.setPadding(new Insets(5));
		leftPane.setMaxHeight(140);
		leftPane.add(lblBetalingsform, 0, 0);
		leftPane.add(rbKontant, 0, 1);
		leftPane.add(rbDan, 0, 2);
		leftPane.add(rbRegning, 0, 3);
		leftPane.add(rbMobile, 0, 4);
		leftPane.add(rbKlip, 0, 5);
		leftPane.add(lblDato, 0, 7);
		leftPane.add(salgsDato, 0, 8);
		leftPane.add(lblTilBetaling, 0, 10);
		leftPane.add(txtTilBetaling, 0, 11);

		GridPane rightPane = new GridPane();
		rightPane.setVgap(10);
		rightPane.setPadding(new Insets(5));
		rightPane.add(lblVarer, 1, 0);
		rightPane.add(linjeView, 1, 1);

		GridPane btmLeftPane = new GridPane();
		btmLeftPane.setVgap(10);
		btmLeftPane.setHgap(20);
		btmLeftPane.setPadding(new Insets(5));
		btmLeftPane.add(lblRabat, 0, 0, 2, 1);
		btmLeftPane.add(rbFlad, 0, 1);
		btmLeftPane.add(rbProcent, 1, 1);
		btmLeftPane.add(txtRabat, 0, 2, 2, 1);

		GridPane btmRightPane = new GridPane();
		btmRightPane.setVgap(10);
		btmRightPane.setHgap(30);
		btmRightPane.setPadding(new Insets(5));
		btmRightPane.add(btnAddSL, 0, 0);
		btmRightPane.add(btnDeleteSL, 1, 0);
		btmRightPane.add(btnCreateSalg, 0, 1, 2, 1);

		GridPane backPane = new GridPane();
		backPane.setVgap(10);
		backPane.setPadding(new Insets(5));
		Button back = new Button("BACK");
		backPane.add(back, 0, 1);

		back.setOnAction(e -> stage.setScene(scene));

		RowConstraints row1 = new RowConstraints();
		row1.setValignment(VPos.TOP);
		getRowConstraints().add(row1);

		this.add(leftPane, 1, 0);
		this.add(rightPane, 6, 0);
		this.add(btmLeftPane, 1, 1);
		this.add(btmRightPane, 6, 1);
		this.add(backPane, 1, 2);

		btnAddSL.setId("secButton");
		btnCreateSalg.setId("secButton");
		btnDeleteSL.setId("secButton");
		back.setId("secButton");

		btnAddSL.setOnAction(event -> actionOpenCreateSalgslinjeDialog());

		btnCreateSalg.setDisable(true);
		btnCreateSalg.setStyle("-fx-font-weight: bold;");
		btnCreateSalg.setOnAction(event -> actionFinishSalg());

		btnDeleteSL.setOnAction(event -> actionDeleteSL());
		btnDeleteSL.setDisable(true);

		txtRabat.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.ENTER)) {
					actionAddRabat();
				}
			}
		});

		toggleRabat.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				txtRabat.setVisible(true);
				txtRabat.setDisable(false);
			}
		});
	}

//	TODO
	private void actionAddRabat() {
		int selected = 0;
		if (linjeView.getSelectionModel().getSelectedItem() != null) {
			selected = linjeView.getSelectionModel().getSelectedIndex();
		}
		linjeView.getSelectionModel().select(selected);
		if (!txtRabat.getText().isEmpty()) {
			if (toggleRabat.getSelectedToggle().getUserData().equals("flad")) {
				linjeView.getSelectionModel().getSelectedItem().givRabat(Integer.parseInt(txtRabat.getText())
						* linjeView.getSelectionModel().getSelectedItem().getAntal());

			}
			if (toggleRabat.getSelectedToggle().getUserData().equals("procent")) {
				double proc = Double.parseDouble(txtRabat.getText()) / 100;
				double pris = linjeView.getSelectionModel().getSelectedItem().getPris().getPris()
						* linjeView.getSelectionModel().getSelectedItem().getAntal();
				double procentRabat = proc * pris;
				linjeView.getSelectionModel().getSelectedItem().givRabat(procentRabat);

			}
			updateInfo();
			txtRabat.clear();
		} else {
			txtRabat.setStyle("-fx-text-fill: red");
			txtRabat.setText("Indtast et tal og tryk ENTER");
		}
	}

	private void actionDeleteSL() {
		s.removeSalgsLinje(linjeView.getSelectionModel().getSelectedItem());
		this.updateInfo();
		if (s.getSalgsLinjer().isEmpty()) {
			btnCreateSalg.setDisable(true);
			btnDeleteSL.setDisable(true);
			rbFlad.setVisible(false);
			rbProcent.setVisible(false);
			lblRabat.setVisible(false);
		}
	}

	private void actionOpenCreateSalgslinjeDialog() {
		if (toggles.getSelectedToggle() != null) {
			CreateSalgslinjeDialog di = new CreateSalgslinjeDialog();
			di.setOnShowing(event -> this.startSalg());
			di.setOnHidden(event -> this.updateSalg(di.passLinjer()));
			di.showAndWait();
		} else {
			lblBetalingsform.setTextFill(Color.RED);
		}
	}

	private void startSalg() {
		if (this.s == null) {
			this.s = controller.createSalg(salgsLinjer, (BetalingsFormer) toggles.getSelectedToggle().getUserData(),
					salgsDato.getValue());
		}
	}

	private void updateSalg(ArrayList<SalgsLinje> linjer) {
		for (SalgsLinje sl : linjer) {
			s.addSalgsLinje(sl);
		}
		if (!s.getSalgsLinjer().isEmpty()) {
			linjeView.getSelectionModel().selectFirst();
			btnCreateSalg.setDisable(false);
			btnDeleteSL.setDisable(false);
			rbFlad.setVisible(true);
			rbProcent.setVisible(true);
			lblRabat.setVisible(true);
		}
		this.updateInfo();
	}

	private void updateInfo() {
		linjeView.getItems().setAll(s.getSalgsLinjer());
		if (s.getSalgsLinjer().isEmpty()) {
			btnCreateSalg.setDisable(true);
			btnDeleteSL.setDisable(true);
			rbFlad.setVisible(false);
			rbProcent.setVisible(false);
			lblRabat.setVisible(false);
			txtRabat.clear();
			txtRabat.setVisible(false);
		}
		i = s.beregnSamletListePris() - s.beregnSamletRabat();
		txtTilBetaling.setText(i + kr);
	}

	private void actionFinishSalg() {
		controller.storeSalg(s);
		controller.saveStorage();
		linjeView.getItems().clear();
		btnCreateSalg.setDisable(true);
		btnDeleteSL.setDisable(true);
		rbFlad.setVisible(false);
		rbProcent.setVisible(false);
		lblRabat.setVisible(false);
		txtRabat.clear();
		txtRabat.setVisible(false);
		s = null;
		i = 0;
		txtTilBetaling.setText(i + kr);
	}
}
