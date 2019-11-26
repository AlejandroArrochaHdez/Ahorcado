package dad.javafx.puntuaciones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class PuntuacionesController implements Initializable {
	
	//model
	private ListProperty<String> listaPuntuaciones = new SimpleListProperty<String>(FXCollections.observableArrayList());
	
	@FXML
    private ListView<String> view;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		view.itemsProperty().bind(listaPuntuaciones);

	}
	
	public ListView<?> getView() {
		return view;
	}
	
	public PuntuacionesController(){

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PuntuacionesView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final ListProperty<String> listaPuntuacionesProperty() {
		return this.listaPuntuaciones;
	}
	

	public final ObservableList<String> getListaPuntuaciones() {
		return this.listaPuntuacionesProperty().get();
	}
	

	public final void setListaPuntuaciones(final ObservableList<String> listaPuntuaciones) {
		this.listaPuntuacionesProperty().set(listaPuntuaciones);
	}
	
}
