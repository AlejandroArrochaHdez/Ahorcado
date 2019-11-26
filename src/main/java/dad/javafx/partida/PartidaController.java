package dad.javafx.partida;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.root.RootController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class PartidaController implements Initializable {
	private RootController rootController;
	private char[] letrasArray;
	private boolean acierto;
	private IntegerProperty vidasImagen = new SimpleIntegerProperty();
	private int sumLetra;
	private char[] allLetter;
	private int contadorAcierto = 0;

	// model
	private StringProperty palabra = new SimpleStringProperty();
	private StringProperty palabraLineas = new SimpleStringProperty();
	private IntegerProperty puntos = new SimpleIntegerProperty();
	private StringProperty letras = new SimpleStringProperty();
	private IntegerProperty vidas = new SimpleIntegerProperty();
	private ListProperty<String> listaPalabras = new SimpleListProperty<String>(FXCollections.observableArrayList());

	@FXML
	private VBox view;

	@FXML
	private Label puntosLabel;

	@FXML
	private ImageView image;

	@FXML
	private Label palabrasLabel;

	@FXML
	private Label letrasLabel;

	@FXML
	private TextField letrasText;

	@FXML
	private Button letraBtn;

	@FXML
	private Button resolverButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindeos
		puntosLabel.textProperty().bindBidirectional(puntosProperty(), new NumberStringConverter());
		palabraLineasProperty().bindBidirectional(palabrasLabel.textProperty());
//		palabrasLabel.textProperty().bind(palabraLineasProperty());
		letrasLabel.textProperty().bindBidirectional(letrasProperty());

		// listener lista palabras
		rootController.getModel().rootLista.addListener((o, ov, nv) -> {
			setLetras("");
			setListaPalabras(rootController.getModel().getRootLista());
			comenzarPartida();
		});
		vidasImagen.set(1);
		// listener

		vidasImagen.addListener((o, ov, nv) -> {
			try {
				image.setImage(new Image("/imagenes/" + vidasImagen.get() + ".png"));
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Ahorcado");
				alert.setHeaderText("Error");
				alert.setContentText("No se han encontrado imagenes");
				alert.showAndWait();
			}
			if (vidasImagen.get() == 9) {
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Ahorcado");
				dialog.setHeaderText("Game Over");
				dialog.setContentText("Haz agotado las nueve vidas del juego");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()) {
					if (result.get().equals(""))
						rootController.getModel().rootListaPuntuaciones.add("Anónimo: " + getPuntos());
					else
						rootController.getModel().rootListaPuntuaciones.add(result.get() + ": " + getPuntos());
				}
				comenzarPartida();
			}

		});

	}

	private void nuevaPalabra() {
		contadorAcierto = 0;
		setLetras("");
		vidasImagen.set(1);
		sumLetra = 0;
		image.setImage(new Image("/imagenes/" + vidasImagen.get() + ".png"));
		acierto = false;
		try {
			int i = (int) Math.floor(Math.random() * ((listaPalabras.getSize() - 1) - 0 + 1) + 0);
			System.out.println(listaPalabras.get(i) + "f");
			palabra.set(listaPalabras.get(i));
			allLetter = new char[27];
			setLetrasArray(new char[palabra.get().length()]);
			String lineas = "";
			for (int j = 0; j < getPalabra().length(); j++) {
				if (palabra.get().charAt(j) != ' ') {
					lineas = lineas + "_";
				} else {
					lineas = lineas + " ";
				}
			}
			setPalabraLineas(lineas);

		} catch (Exception e) {
			palabraLineas.set("Debe introducir palabras para poder comenzar la partida");
		}
	}

	private void comenzarPartida() {
		setLetras("");
		setVidas(8);
		setPuntos(0);
		vidasImagen.set(1);
		sumLetra = 0;
		image.setImage(new Image("/imagenes/" + vidasImagen.get() + ".png"));
		acierto = false;
		try {
			int i = (int) Math.floor(Math.random() * listaPalabras.getSize());
			palabra.set(listaPalabras.get(i));
			allLetter = new char[27];
			setLetrasArray(new char[palabra.get().length()]);
			String lineas = "";
			for (int j = 0; j < getPalabra().length(); j++) {
				if (palabra.get().charAt(j) != ' ') {
					lineas = lineas + "_";
				} else {
					lineas = lineas + " ";
				}
			}
			setPalabraLineas(lineas);

		} catch (Exception e) {
			palabraLineas.set("Debe introducir palabras para poder comenzar la partida");
		}

	}

	public VBox getView() {
		return view;
	}

	public PartidaController(RootController rootController) {
		this.rootController = rootController;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PartidaView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onLetraAction(ActionEvent event) {
		boolean existe = false;
		if (letrasText.getText().length() == 1) {
			char letraIntroducida = Character.toUpperCase(letrasText.getText().charAt(0));
			for (int i = 0; i < allLetter.length; i++) {
				if (allLetter[i] == letraIntroducida) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Ahorcado");
					alert.setHeaderText("ERROR");
					alert.setContentText("Ya haz introducido la letra" + letraIntroducida);
					alert.showAndWait();
					existe = true;
				}
			}
			if (!existe) {
				String comparar = "";
				allLetter[sumLetra] = letraIntroducida;
				sumLetra++;
				setLetrasArray(allLetter);
				setLetras(getLetras().concat("" + letraIntroducida).concat("-"));
				for (int i = 0; i < getPalabra().length(); i++) {
					if (getPalabra().charAt(i) == letraIntroducida) {
						comparar += letraIntroducida;
						setPuntos(getPuntos() + 1);
						contadorAcierto++;
						acierto = true;
					} else {
						if (Character.isWhitespace(getPalabra().charAt(i))) {
							comparar += " ";
						} else {
							comparar += getPalabraLineas().charAt(i);
						}
					}
				}
				palabraLineas.set(comparar);
				if (!acierto) {
					vidasImagen.set(vidasImagen.get() + 1);
					setVidas(getVidas() - 1);
				}

			}
			letrasText.setText("");
			acierto = false;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ahorcado");
			alert.setHeaderText("ERROR");
			alert.setContentText("Tiene que introducir una sola letra.");
			alert.showAndWait();
		}
	}

	@FXML
	void onResolverAction(ActionEvent event) {
		String srt = letrasText.getText().toUpperCase();
		if (srt.equals(getPalabra())) {
			setPuntos(getPuntos() + (getPalabra().length() - contadorAcierto));
			nuevaPalabra();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ahorcado");
			alert.setHeaderText("ERROR");
			alert.setContentText("La respuesta es incorrecta");
			alert.showAndWait();
			vidasImagen.set(vidasImagen.get() + 1);
			setVidas(getVidas() - 1);
		}
	}

	public final StringProperty palabraProperty() {
		return this.palabra;
	}

	public final String getPalabra() {
		return this.palabraProperty().get();
	}

	public final void setPalabra(final String palabra) {
		this.palabraProperty().set(palabra);
	}

	public final StringProperty palabraLineasProperty() {
		return this.palabraLineas;
	}

	public final String getPalabraLineas() {
		return this.palabraLineasProperty().get();
	}

	public final void setPalabraLineas(final String palabraLineas) {
		this.palabraLineasProperty().set(palabraLineas);
	}

	public final IntegerProperty puntosProperty() {
		return this.puntos;
	}

	public final int getPuntos() {
		return this.puntosProperty().get();
	}

	public final void setPuntos(final int puntos) {
		this.puntosProperty().set(puntos);
	}

	public final StringProperty letrasProperty() {
		return this.letras;
	}

	public final String getLetras() {
		return this.letrasProperty().get();
	}

	public final void setLetras(final String letras) {
		this.letrasProperty().set(letras);
	}

	public final IntegerProperty vidasProperty() {
		return this.vidas;
	}

	public final int getVidas() {
		return this.vidasProperty().get();
	}

	public final void setVidas(final int vidas) {
		this.vidasProperty().set(vidas);
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

	public char[] getLetrasArray() {
		return letrasArray;
	}

	public void setLetrasArray(char[] letrasArray) {
		this.letrasArray = letrasArray;
	}

}
