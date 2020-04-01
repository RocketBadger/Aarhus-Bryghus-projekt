package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	Scene scene;
	Scene salgScene;
	Scene produktScene;
	Scene prisScene;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		window = stage;
		stage.setTitle("Aarhus Bryghus Kasseapparat");
		BorderPane pane = new BorderPane();
		this.initContent(pane);
		
		scene = new Scene(pane, 600, 500);
		pane.setId("pane");
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
	}
	
	private void initContent(BorderPane pane) throws FileNotFoundException{
		VBox vbx = new VBox();
		vbx.setPadding(new Insets(20, 0, 20, 0));
		vbx.setSpacing(30);
		
		Image headerLogo = new Image(new FileInputStream("src/gui/ABLOGO1.png"));
		ImageView imgView = new ImageView(headerLogo);
		imgView.setFitHeight(150);
		imgView.setFitWidth(300);
		
		Button btn1 = new Button("Udfør Salg");
		Button btn2 = new Button("Produkter og produktgrupper");
		Button btn3 = new Button("Prislister og priser");
		btn1.setStyle("-fx-background-color: white");
		btn2.setStyle("-fx-background-color: white");
		btn3.setStyle("-fx-background-color: white");
		btn1.setPrefSize(300, 50);
		btn2.setPrefSize(300, 50);
		btn3.setPrefSize(300, 50);
		vbx.getChildren().addAll(imgView,btn1, btn2, btn3);
		
		vbx.setAlignment(Pos.TOP_CENTER);
		pane.setCenter(vbx);
		
		btn1.setOnAction(e -> salgGui());
		btn2.setOnAction(e -> produktGui());
		btn3.setOnAction(e -> prisGui());
		
	}
	
	public void salgGui() {
		SalgNyGui salg = new SalgNyGui();
		GridPane pane = new GridPane();
		pane.add(salg, 0, 0);
		salgScene = new Scene(pane, 600, 500);

		window.setScene(salgScene);
	}
	
	public void produktGui() {
		GridPane pane = new GridPane();
		produktScene = new Scene(pane, 600, 500);
		pane.add(new Label("Dette er en ny produkt scene"), 0, 0);
		Button back = new Button("Back");
		back.setOnAction(e -> window.setScene(scene));
		pane.add(back, 0, 1);
		window.setScene(produktScene);
		
	}
	
	public void prisGui() {
		GridPane pane = new GridPane();
		prisScene = new Scene(pane, 600, 500);
		pane.add(new Label("Dette er en ny pris scene"), 0, 0);
		Button back = new Button("Back");
		back.setOnAction(e -> window.setScene(scene));
		pane.add(back, 0, 1);
		window.setScene(prisScene);
		
	}
	
	
}
