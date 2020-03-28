package gui;

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
	public void start(Stage stage) {
		stage.setTitle("Aarhus Bryghus Bogføring");
		BorderPane pane = new BorderPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setHeight(500);
		stage.setWidth(600);
		stage.show();
	}

	private void initContent(BorderPane pane) {
		TabPane tabPane = new TabPane();
		this.initTabPane(tabPane);
		pane.setCenter(tabPane);
	}

	private void initTabPane(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tabOpretProdukt = new Tab("Opret produkt/gruppe");
		Tab tabVis = new Tab("Vis produkter og produktgrupper");
		Tab tabPriser = new Tab("Prislister og priser");

		tabOpretProdukt.setContent(new CreateProduktPane());
		VisProduktPane visProduktPane = new VisProduktPane();
		tabVis.setContent(visProduktPane);
		tabPriser.setContent(new PrisListePane());

		tabPane.getTabs().add(tabVis);
		tabPane.getTabs().add(tabOpretProdukt);
		tabPane.getTabs().add(tabPriser);

		tabVis.setOnSelectionChanged(event -> visProduktPane.updateGruppeList());
	}
}
