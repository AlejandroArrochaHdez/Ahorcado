package dad.javafx.root;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.palabras.PalabrasController;
import dad.javafx.partida.PartidaController;
import dad.javafx.puntuaciones.PuntuacionesController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController implements Initializable{
	
	private RootModel model = new RootModel(); 
	
	private PartidaController partidaController = new PartidaController(this);
	private PalabrasController palabrasController = new PalabrasController();
	private PuntuacionesController puntuacionesController = new PuntuacionesController();
	
	@FXML
    private TabPane view;
	
    @FXML
    private Tab partidaTab;

    @FXML
    private Tab palabrasTab;

    @FXML
    private Tab puntuacionesTab;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		palabrasController.setListaPalabras(model.rootLista);
		partidaController.setListaPalabras(model.getRootLista());
		puntuacionesController.setListaPuntuaciones(model.rootListaPuntuaciones);

		
		partidaTab.setContent(partidaController.getView());
		puntuacionesTab.setContent(puntuacionesController.getView());
		palabrasTab.setContent(palabrasController.getView());
		
	}
	
	public void introducirListas(final ObservableList<String> listaPalabras, final ObservableList<String> listaPuntuaciones) {
	
		model.rootLista.set(listaPalabras);
		model.rootListaPuntuaciones.set(listaPuntuaciones);
	}
	
	public TabPane getView() {
		return view;
	}
	
	public RootController(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RootModel getModel() {
		return model;
	}
	
}
