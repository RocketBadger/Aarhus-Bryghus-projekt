package gui;

import java.io.File;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	private Controller controller;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() {
		controller = Controller.getController();
		controller.createSomeObjects();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Aarhus Bryghus Kasseapparat");
		BorderPane pane = new BorderPane();
		this.initContent(pane);

		Scene scene = new Scene(pane, 1000, 600);
		pane.setId("pane");
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(true);
		stage.show();
		stage.setTitle("Sidebar test");

		stage.setScene(scene);
		stage.show();

	}

	private void initContent(BorderPane pane) {
		File saveFile = new File("src/storage.ser");
		if (saveFile.exists()) {
			controller.loadStorage();
		}

		SalgsPane salgspane = new SalgsPane();
		ProduktPane produktpane = new ProduktPane();
		PrisPane prispane = new PrisPane();
		OversigtPane oversigtspane = new OversigtPane();
		pane.setCenter(salgspane);

		// ---------- Side menu start ----------
		DropShadow dropshadow = new DropShadow(BlurType.GAUSSIAN, Color.color(0.25, 0.25, 0.25, 0.15), 2, 5, 5, 0);
		VBox vbxButtons = new VBox();
		vbxButtons.setEffect(dropshadow);
		vbxButtons.setPadding(new Insets(50, 20, 50, 20));
		vbxButtons.setSpacing(50);
		vbxButtons.setStyle("-fx-background-color: #a7adb4");

		Button btnSalg = new Button("Udfør salg");
		btnSalg.setId("MainButton");
		btnSalg.setOnAction(e -> pane.setCenter(salgspane));

		Button btnProdukt = new Button("Produkter og produktgrupper");
		btnProdukt.setId("MainButton");
		btnProdukt.setOnAction(e -> pane.setCenter(produktpane));

		Button btnPris = new Button("Prislister og priser");
		btnPris.setId("MainButton");
		btnPris.setOnAction(e -> pane.setCenter(prispane));

		Button btnOversigt = new Button("Oversigter");
		btnOversigt.setId("MainButton");
		btnOversigt.setOnAction(e -> pane.setCenter(oversigtspane));

		vbxButtons.getChildren().addAll(btnSalg, btnProdukt, btnPris, btnOversigt);
		pane.setLeft(vbxButtons);
		// ---------- Side menu slut ----------

	}
}
