package dad.javafx.palabras;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

public class PalabrasController implements Initializable {
	
	private ListProperty<String> listaPalabras = new SimpleListProperty<String>(FXCollections.observableArrayList());
	
	@FXML
    private HBox view;

    @FXML
    private ListView<String> palabrasList;

    @FXML
    private Button añadirButton;

    @FXML
    private Button quitarButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		palabrasList.itemsProperty().bind(listaPalabras);

	}
	
	public HBox getView() {
		return view;
	}
	
	public PalabrasController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PalabrasView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
    void onAñadirButton(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Nueva Palabra");
		dialog.setContentText("Introduce una nueva palabra");
		dialog.setHeaderText("Palabra");
		Optional<String> resultado = dialog.showAndWait();
		if (resultado.isPresent()) {
			listaPalabras.add(resultado.get().toUpperCase());
		}
    }

    @FXML
    void onQuitarButton(ActionEvent event) {
    	listaPalabras.remove(palabrasList.getSelectionModel().getSelectedItem());
    }

	public final ListProperty<String> listaPalabrasProperty() {
		return this.listaPalabras;
	}
	

	public final ObservableList<String> getListaPalabras() {
		return this.listaPalabrasProperty().get();
	}
	

	public final void setListaPalabras(final ObservableList<String> listaPalabras) {
		this.listaPalabrasProperty().set(listaPalabras);
	}
	
}
