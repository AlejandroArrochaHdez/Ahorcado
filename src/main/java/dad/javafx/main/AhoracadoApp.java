package dad.javafx.main;



import dad.javafx.persistencia.Persistencia;
import dad.javafx.root.RootController;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhoracadoApp extends Application {
	
	private RootController controller = new RootController();
	
	ListProperty<String> listaPalabras = new SimpleListProperty<String>(FXCollections.observableArrayList());
	ListProperty<String> listaPuntuaciones = new SimpleListProperty<String>(FXCollections.observableArrayList());
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("Acceso a datos");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	@Override
	public void init() throws Exception {
		super.init();
		listaPalabras.set(Persistencia.cargarPalabras());
		listaPuntuaciones.set(Persistencia.cargarPuntuaciones());
		controller.introducirListas(listaPalabras, listaPuntuaciones);
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		Persistencia.guardarPalabras(listaPalabras);
		Persistencia.guardarPuntuaciones(listaPuntuaciones);
	}

}
