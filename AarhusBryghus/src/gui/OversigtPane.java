package gui;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.BetalingsFormer;
import model.Salg;

public class OversigtPane extends GridPane {
	private Controller controller;
	private DatePicker datepicker = new DatePicker();
	private DatePickerSkin datePickerSkin = new DatePickerSkin(datepicker);
	private Node popupContent = datePickerSkin.getPopupContent();
	private TableView<TableColumns> tableView;

	@SuppressWarnings("unchecked")
	public OversigtPane() {
		this.controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setGridLinesVisible(false);

		TableColumn<TableColumns, String> navnColumn = new TableColumn<>("Produkt:");
		navnColumn.setMinWidth(140);
		navnColumn.setCellValueFactory(new PropertyValueFactory<>("produktNavn"));

		TableColumn<TableColumns, Integer> antalColumn = new TableColumn<>("Antal:");
		antalColumn.setMinWidth(140);
		antalColumn.setCellValueFactory(new PropertyValueFactory<>("produktAntal"));

		TableColumn<TableColumns, Double> prisColumn = new TableColumn<>("Stk. Pris:");
		prisColumn.setMinWidth(140);
		prisColumn.setCellValueFactory(new PropertyValueFactory<>("produktPris"));

		TableColumn<TableColumns, Double> samletPrisColumn = new TableColumn<>("Samlet Pris:");
		samletPrisColumn.setMinWidth(140);
		samletPrisColumn.setCellValueFactory(new PropertyValueFactory<>("samletPris"));

		TableColumn<TableColumns, String> betalingsformColumn = new TableColumn<>("Betalingsform:");
		betalingsformColumn.setMinWidth(140);
		betalingsformColumn.setCellValueFactory(new PropertyValueFactory<>("betalingsform"));

		tableView = new TableView<>();
		tableView.setPrefWidth(710);
		tableView.getColumns().addAll(navnColumn, antalColumn, prisColumn, samletPrisColumn, betalingsformColumn);

		this.add(tableView, 0, 0);
		this.add(popupContent, 0, 1);

		popupContent.setOnMouseClicked(e -> tableView.setItems(getColumns()));

	}

	public ObservableList<TableColumns> getColumns() {
		ObservableList<TableColumns> tableColumns = FXCollections.observableArrayList();
		for (Salg s : controller.getAllSalg()) {
			if (datepicker.getValue().equals(s.getDato())) {
				for (int i = 0; i < s.getSalgsLinjer().size(); i++) {
					if (s.getSalgsLinjer().get(i).getPris() != null
							&& s.getSalgsLinjer().get(i).getPris().getProdukt() != null) {
						String navn = s.getSalgsLinjer().get(i).getPris().getProdukt().getNavn();
						int antal = s.getSalgsLinjer().get(i).getAntal();
						double enkelPris = s.getSalgsLinjer().get(i).getPris().getPris();
						double samletPris = s.getReelPris();
						BetalingsFormer betalingsform = s.getBetalingsform();
						tableColumns.add(new TableColumns(navn, antal, enkelPris, samletPris, betalingsform));
					} else if (s.getSalgsLinjer().get(i).getPris() != null
							&& s.getSalgsLinjer().get(i).getPris().getKlippekort() != null) {
						String navn = s.getSalgsLinjer().get(i).getPris().getKlippekort().toString();
						int antal = s.getSalgsLinjer().get(i).getAntal();
						double enkelPris = s.getSalgsLinjer().get(i).getPris().getPris();
						double samletPris = s.getReelPris();
						BetalingsFormer betalingsform = s.getBetalingsform();
						tableColumns.add(new TableColumns(navn, antal, enkelPris, samletPris, betalingsform));
					}
				}
			}
		}
		return tableColumns;
	}
}
