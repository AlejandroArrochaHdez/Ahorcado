package dad.javafx.root;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RootModel {
	// model
		public ListProperty<String> rootLista = new  SimpleListProperty<String>(FXCollections.observableArrayList());
		public ListProperty<String> rootListaPuntuaciones = new  SimpleListProperty<String>(FXCollections.observableArrayList());
		private StringProperty palabra = new SimpleStringProperty();
		private StringProperty nombre = new SimpleStringProperty();
		private DoubleProperty puntos = new SimpleDoubleProperty();

		public final ListProperty<String> rootListaProperty() {
			return this.rootLista;
		}
		

		public final ObservableList<String> getRootLista() {
			return this.rootListaProperty().get();
		}
		

		public final void setRootLista(final ObservableList<String> rootLista) {
			this.rootListaProperty().set(rootLista);
		}

		public final ListProperty<String> rootListaPuntuacionesProperty() {
			return this.rootListaPuntuaciones;
		}
		

		public final ObservableList<String> getRootListaPuntuaciones() {
			return this.rootListaPuntuacionesProperty().get();
		}
		

		public final void setRootListaPuntuaciones(final ObservableList<String> rootListaPuntuaciones) {
			this.rootListaPuntuacionesProperty().set(rootListaPuntuaciones);
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
		


		public final StringProperty nombreProperty() {
			return this.nombre;
		}
		


		public final String getNombre() {
			return this.nombreProperty().get();
		}
		


		public final void setNombre(final String nombre) {
			this.nombreProperty().set(nombre);
		}
		


		public final DoubleProperty puntosProperty() {
			return this.puntos;
		}
		


		public final double getPuntos() {
			return this.puntosProperty().get();
		}
		


		public final void setPuntos(final double puntos) {
			this.puntosProperty().set(puntos);
		}
		
}
