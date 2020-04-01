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
	Scene scene1;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		window = stage;
		stage.setTitle("Aarhus Bryghus Kasseapparat");
		BorderPane pane = new BorderPane();
		this.initContent(pane);
		
		Scene scene = new Scene(pane, 600, 500);
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
		imgView.setFitHeight(100);
		imgView.setFitWidth(215);
		
		Button btn1 = new Button("Udfør Salg");
		Button btn2 = new Button("Produkter og produktgrupper");
		Button btn3 = new Button("Prislister og priser");
		btn1.setPrefSize(300, 50);
		btn2.setPrefSize(300, 50);
		btn3.setPrefSize(300, 50);
		vbx.getChildren().addAll(imgView,btn1, btn2, btn3);
		
		vbx.setAlignment(Pos.TOP_CENTER);
		pane.setCenter(vbx);
		
		btn1.setOnAction(e -> produktgui());
		
	}
	
	public void produktgui() {
		GridPane pane = new GridPane();
		scene1 = new Scene(pane, 600, 500);
		pane.add(new Label("Dette er en ny scene"), 0, 0);
		window.setScene(scene1);
	}
}
