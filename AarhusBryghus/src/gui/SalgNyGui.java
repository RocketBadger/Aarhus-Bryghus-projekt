package gui;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
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

	private ArrayList<SalgsLinje> salgsLinjer = new ArrayList<>();
	private Salg s = null;
	String kr = " kr";
	double i = 0;

	public SalgNyGui() {
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

		toggles.getToggles().addAll(rbKontant, rbDan, rbRegning, rbMobile, rbKlip);

		salgsDato.setValue(LocalDate.now());

		linjeView.setMaxSize(270, 290);

		btnCreateSalg.setMinSize(250, 20);

		txtTilBetaling.setEditable(false);
		txtTilBetaling.setText(i + kr);

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
		leftPane.add(new Label("Til betaling:"), 0, 11);
		leftPane.add(txtTilBetaling, 0, 12);
//		leftPane.setStyle("-fx-border-color: grey;");

		GridPane rightPane = new GridPane();
		rightPane.setVgap(10);
		rightPane.setPadding(new Insets(5));
		rightPane.add(lblVarer, 1, 0);
		rightPane.add(linjeView, 1, 1);
//		rightPane.setStyle("-fx-border-color: grey;");

		GridPane btmLeftPane = new GridPane();
		btmLeftPane.setVgap(10);
		btmLeftPane.setHgap(10);
		btmLeftPane.setPadding(new Insets(5));
		btmLeftPane.add(btnAddSL, 0, 0);
		btmLeftPane.add(btnDeleteSL, 1, 0);
//		btmLeftPane.setStyle("-fx-border-color: grey;");

		GridPane btmRightPane = new GridPane();
		btmRightPane.setVgap(10);
		btmRightPane.setPadding(new Insets(5));
		btmRightPane.add(btnCreateSalg, 0, 0);
//		btmRightPane.setStyle("-fx-border-color: grey;");

		RowConstraints row1 = new RowConstraints();
		row1.setValignment(VPos.TOP);
		getRowConstraints().add(row1);

		this.add(leftPane, 0, 0);
		this.add(rightPane, 1, 0);
		this.add(btmLeftPane, 0, 1);
		this.add(btmRightPane, 1, 1);

		btnAddSL.setOnAction(event -> actionOpenCreateSalgslinjeDialog());

		btnCreateSalg.setDisable(true);
		btnCreateSalg.setStyle("-fx-font-weight: bold;");
		btnCreateSalg.setOnAction(event -> actionFinishSalg());

		btnDeleteSL.setOnAction(event -> actionDeleteSL());
	}

	private void actionDeleteSL() {
		s.removeSalgsLinje(linjeView.getSelectionModel().getSelectedItem());
		this.updateInfo();
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
			btnCreateSalg.setDisable(true);
		}
		this.updateInfo();
	}

	private void updateInfo() {
		linjeView.getItems().setAll(s.getSalgsLinjer());
		i = s.beregnSamletListePris();
		txtTilBetaling.setText(i + kr);
	}

	private void actionFinishSalg() {
		controller.storeSalg(s);
		linjeView.getItems().clear();
		s = null;
		i = 0;
		txtTilBetaling.setText(i + kr);
	}
}
