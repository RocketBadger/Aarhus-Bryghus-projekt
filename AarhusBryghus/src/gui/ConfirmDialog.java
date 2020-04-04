package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmDialog {
	private Label lblSikker = new Label("Er du sikker på at du vil slette?");
	private Button btnJa = new Button("Ja");
	private Button btnNej = new Button("Nej");
	private boolean result;

	public boolean Display() {
		Stage confirmStage = new Stage();
		confirmStage.initModality(Modality.APPLICATION_MODAL);
		confirmStage.setResizable(false);
		confirmStage.setTitle("Er du sikker");
		confirmStage.setMinWidth(200);

		GridPane pane = new GridPane();
		
		pane.setPadding(new Insets(20));
		pane.setHgap(20);
		pane.setVgap(10);

		pane.add(lblSikker, 0, 0);
		
		pane.add(btnJa, 0, 1);
		btnJa.setOnAction(e -> {
			result = true;
			confirmStage.close();
		});
		
		pane.add(btnNej, 1, 1);
		btnNej.setOnAction(e -> {
			result = false;
			confirmStage.close();
		});
		btnNej.setStyle("-fx-font-weight: bold; -fx-background-color: #c3c4c4,\r\n" + 
				"        linear-gradient(#b01030 45%, white 100%),\r\n" + 
				"        radial-gradient(center 50% -40%, radius 200%, #dc143c 45%, rgba(230,230,230,0) 50%);");
		
		Scene scene = new Scene(pane);
		confirmStage.setScene(scene);
		confirmStage.showAndWait();
		
		return result;
	}

}
