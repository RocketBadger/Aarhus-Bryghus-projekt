package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Rundvisning;
import model.SalgsLinje;

public class CreateRundvisningDialog extends Stage {
	private Controller controller;
	private Label lblDato = new Label("Vælg Dato");
	private Label lblTid = new Label("Indtast tid i form af fire tal, eks. 1530");
	private TextField txtTid = new TextField();
	private DatePicker dp = new DatePicker();
	private Button btnCreate = new Button("Opret rundvisning");
	private List<SalgsLinje> linjer = new ArrayList<>();

	public CreateRundvisningDialog() {
		this.controller = Controller.getController();

		this.initStyle(StageStyle.DECORATED);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		this.setTitle("Rundvisning");

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

		pane.add(lblDato, 0, 0);
		pane.add(dp, 0, 1);

		pane.add(lblTid, 0, 3);
		pane.add(txtTid, 0, 4);

		pane.add(btnCreate, 0, 6);

		dp.setValue(LocalDate.now());
		btnCreate.setOnAction(event -> actionCreateRundvisning());
	}

	private void actionCreateRundvisning() {
		if (!txtTid.getText().isEmpty()) {
			Rundvisning r = controller.createRundvisning(dp.getValue(),
					LocalTime.of(Integer.parseInt(txtTid.getText().substring(0, 2)),
							Integer.parseInt(txtTid.getText().substring(2, 4))));
			SalgsLinje sl = controller.createSalgsLinje(250, r);
			linjer.add(sl);
			controller.saveStorage();
			this.hide();
		} else
			lblTid.setTextFill(Color.RED);
	}

	protected ArrayList<SalgsLinje> passLinjer() {
		return new ArrayList<>(linjer);
	}
}
