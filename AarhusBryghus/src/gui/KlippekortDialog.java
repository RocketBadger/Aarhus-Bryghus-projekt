package gui;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SalgsLinje;


public class KlippekortDialog  {

	private Controller controller;
	private TextField txfAntal = new TextField();
	private Button btnOk = new Button("Ok");
	private List<SalgsLinje> linjer = new ArrayList<>();
	private Stage klipStage = new Stage();

	public void Display() {
		this.controller = Controller.getController();
		klipStage.initModality(Modality.APPLICATION_MODAL);
		klipStage.setResizable(false);
		klipStage.setTitle("Opret klippekort");
		klipStage.setMinWidth(200);

		GridPane pane = new GridPane();
		
		pane.setPadding(new Insets(20));
		pane.setHgap(20);
		pane.setVgap(10);
		
		txfAntal.setPromptText("Antal klippekort");
		pane.add(txfAntal, 0, 0);
		
		btnOk.requestFocus();
		btnOk.setOnAction(e -> actionCreateKlippekort());
		pane.add(btnOk, 0, 1);
		
		
		Scene scene = new Scene(pane);
		klipStage.setScene(scene);
		klipStage.showAndWait();
		
	}
	
	private void actionCreateKlippekort() {
		int antal = 0;
		if (!txfAntal.getText().trim().isEmpty()) {
			antal = Integer.parseInt(txfAntal.getText().trim());
			for (int i = 1; i <= antal; i++) {
				System.out.println(antal);
				SalgsLinje sl = controller.createSalgsLinje(100, controller.createKlippekort());
				linjer.add(sl);
				controller.saveStorage();
			}
		}
		klipStage.close();
	}
	
	protected ArrayList<SalgsLinje> passLinjer() {
		return new ArrayList<>(linjer);
	}
}
