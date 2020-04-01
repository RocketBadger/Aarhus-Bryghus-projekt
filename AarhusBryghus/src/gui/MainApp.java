package gui;

import java.io.File;
import java.io.FileNotFoundException;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
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
	public void start(Stage stage) throws FileNotFoundException {
		stage.setTitle("Aarhus Bryghus Kasseapparat");
		BorderPane pane = new BorderPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setHeight(500);
		stage.setWidth(600);
		stage.show();
	}

	private void initContent(BorderPane pane)  throws FileNotFoundException{
		File saveFile = new File("src/storage.ser");
		if (saveFile.exists()) {
			controller.loadStorage();
		}
		TabPane tabPane = new TabPane();
		this.initTabPane(tabPane);
		pane.setCenter(tabPane);
		
	}

	private void initTabPane(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tabProdukt = new Tab("Produkter og produktgrupper");
		Tab tabPriser = new Tab("Prislister og priser");
		Tab tabSalg = new Tab("Udfør salg");

		ProduktPane ProduktTab = new ProduktPane();
		tabProdukt.setContent(ProduktTab);
		tabPriser.setContent(new PrisListePane());
		tabSalg.setContent(new SalgPane());

		tabPane.getTabs().add(tabSalg);
		tabPane.getTabs().add(tabProdukt);
		tabPane.getTabs().add(tabPriser);

		tabProdukt.setOnSelectionChanged(event -> ProduktTab.updateGruppeList());
	}
}
