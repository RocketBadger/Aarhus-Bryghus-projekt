package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private Controller controller;
	Stage window;
	Scene scene;
	Scene salgScene;
	Scene produktScene;
	Scene prisScene;
	Scene oversigtScene;

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
		window = stage;
		stage.setTitle("Aarhus Bryghus Kasseapparat");
		BorderPane pane = new BorderPane();
		this.initContent(pane);

		scene = new Scene(pane, 700, 600);
		pane.setId("pane");
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	private void initContent(BorderPane pane) throws FileNotFoundException {
		File saveFile = new File("src/storage.ser");
		if (saveFile.exists()) {
			controller.loadStorage();
		}
		VBox vbx = new VBox();
		vbx.setPadding(new Insets(20, 0, 20, 0));
		vbx.setSpacing(30);

		Image headerLogo = new Image(new FileInputStream("src/gui/ABLOGO.png"));
		ImageView imgView = new ImageView(headerLogo);
		imgView.setFitHeight(150);
		imgView.setFitWidth(320);

		Button btn1 = new Button("Udfør Salg");
		btn1.setId("button");
		Button btn2 = new Button("Produkter og produktgrupper");
		Button btn3 = new Button("Prislister og priser");
		Button btn4 = new Button("Oversigter");
		btn2.setId("button");
		btn3.setId("button");
		btn4.setId("button");
		vbx.getChildren().addAll(imgView, btn1, btn2, btn3, btn4);

		vbx.setAlignment(Pos.TOP_CENTER);
		pane.setCenter(vbx);

		btn1.setOnAction(e -> salgGui());
		btn2.setOnAction(e -> produktGui());
		btn3.setOnAction(e -> prisGui());
		btn4.setOnAction(e -> oversigtGui());

	}

	public void salgGui() {
		SalgGui salg = new SalgGui(window, scene);

		GridPane pane = new GridPane();
		pane.add(salg, 0, 0);

		salgScene = new Scene(pane, 725, 600);
		pane.setId("salgGui");
		salgScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		window.setScene(salgScene);
	}

	public void produktGui() {
		ProduktGui produkt = new ProduktGui(window, scene);

		GridPane pane = new GridPane();
		pane.add(produkt, 0, 0);

		produktScene = new Scene(pane, 600, 500);
		pane.setId("produktGui");
		produktScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		window.setScene(produktScene);
	}

	public void prisGui() {
		PrisGui pris = new PrisGui(window, scene);

		GridPane pane = new GridPane();
		pane.add(pris, 0, 0);

		prisScene = new Scene(pane, 625, 500);
		pane.setId("prisGui");
		prisScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		window.setScene(prisScene);
	}

	public void oversigtGui() {
		OversigtPane oversigter = new OversigtPane(window, scene);
		GridPane pane = new GridPane();
		pane.add(oversigter, 0, 0);
		oversigtScene = new Scene(pane, 960, 450);
		pane.setId("oversigtGui");
		oversigtScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		window.setScene(oversigtScene);
	}

}
