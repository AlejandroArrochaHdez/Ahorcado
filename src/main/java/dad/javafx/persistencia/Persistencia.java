package dad.javafx.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Persistencia {

	private static final File FICHEROPUNTUACIONES = new File("puntuaciones.txt");
	private static final File FICHEROPALABRAS = new File("palabras.txt");
	
	public static void guardarPalabras(Collection<String> palabras) throws IOException {
		if (!FICHEROPALABRAS.exists()) 
			FICHEROPALABRAS.createNewFile();
		FileUtils.writeLines(
				FICHEROPALABRAS,
				"UTF-8", 
				palabras, 
				false
			);
	}
	
	public static ObservableList<String> cargarPalabras() throws IOException {
		ListProperty<String> result = new SimpleListProperty<String>(FXCollections.observableArrayList());
		if (!FICHEROPALABRAS.exists()) 
			return result;
		List<String> content = FileUtils.readLines(
				FICHEROPALABRAS, 
				Charset.forName("UTF-8")
			);
		result = new SimpleListProperty<String>(FXCollections.observableArrayList(content));
		return result;
	}
	
	public static void guardarPuntuaciones(Collection<String> puntuaciones) throws IOException {
		if (!FICHEROPALABRAS.exists()) 
			FICHEROPALABRAS.createNewFile();
		FileUtils.writeLines(
				FICHEROPUNTUACIONES,
				"UTF-8", 
				puntuaciones, 
				false
			);
	}
	
	public static ObservableList<String> cargarPuntuaciones() throws IOException {
		ListProperty<String> result = new SimpleListProperty<String>(FXCollections.observableArrayList());
		if (!FICHEROPALABRAS.exists()) 
			return result;
		List<String> content = FileUtils.readLines(
				FICHEROPUNTUACIONES, 
				Charset.forName("UTF-8")
			);
		result = new SimpleListProperty<String>(FXCollections.observableArrayList(content));
		return result;
	}

}
