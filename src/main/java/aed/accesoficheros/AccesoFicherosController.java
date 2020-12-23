package aed.accesoficheros;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AccesoFicherosController implements Initializable {

	// model
	private ObjectProperty<FicheroModel> fichero = new SimpleObjectProperty<>();
	// view
	@FXML
	private GridPane view;

	@FXML
	private TextField rutaText;

	@FXML
	private Button crearButton;

	@FXML
	private Button eliminarButton;

	@FXML
	private Button moverButton;

	@FXML
	private CheckBox carpetaCheckbox;

	@FXML
	private CheckBox ficheroCheckbox;

	@FXML
	private TextField carpetaFicheroText;

	@FXML
	private Button verFicherosCarpetasButton;

	@FXML
	private ListView<String> ficherosCarpetasList;

	@FXML
	private ListView<String> contenidoFicheroList;

	@FXML
	private Button verContenidoButton;

	@FXML
	private Button modificarFicheroButton;

	public AccesoFicherosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/accesoFicherosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ficheroCheckbox.disableProperty().bind(carpetaCheckbox.selectedProperty().asObject());
		carpetaCheckbox.disableProperty().bind(ficheroCheckbox.selectedProperty().asObject());
	}

	@FXML
	void onCrearAction(ActionEvent event) {
		if(ficheroCheckbox.isSelected())
		 try { 
	         File f1 = new File(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get()); 
	     } 
	     catch (Exception e) { 
	         System.err.println(e); 
	     } 
		
		//if(carpetaCheckbox.isSelected())
			
	}

	@FXML
	void onEliminarAction(ActionEvent event) {

	}

	@FXML
	void onModificarFicheroAction(ActionEvent event) {

	}

	@FXML
	void onMoverAction(ActionEvent event) {

	}

	@FXML
	void onVerContenidoAction(ActionEvent event) {

	}

	@FXML
	void onVerFicherosCarpetasAction(ActionEvent event) {

	}
}
