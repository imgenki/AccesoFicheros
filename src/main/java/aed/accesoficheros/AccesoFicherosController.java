package aed.accesoficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class AccesoFicherosController implements Initializable {
	private ListProperty<String> ficherosList = new SimpleListProperty<>();
	private StringProperty contenidoFichero = new SimpleStringProperty();
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
	private TextArea contenidoFicheroText;

	@FXML
	private Button verContenidoButton;

	@FXML
	private Button modificarFicheroButton;

	@FXML
	private Text existeCreadoText;

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

		ficherosCarpetasList.itemsProperty().bind(ficherosList);
		contenidoFicheroText.textProperty().bind(contenidoFichero);
	}

	@FXML
	void onCrearAction(ActionEvent event) {

		try {
			File f1 = new File(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get());
			if (ficheroCheckbox.isSelected())
				if (f1.createNewFile())
					existeCreadoText.setText("No existe - creado");
				else
					existeCreadoText.setText("Ya existe");
			if (carpetaCheckbox.isSelected())
				if (f1.mkdir())
					existeCreadoText.setText("No existe - creado");
				else
					existeCreadoText.setText("Ya existe");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		File f1 = new File(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get());
		if (carpetaCheckbox.isSelected())
			try {
				borrarDirectorio(f1);
				if (f1.delete())
					existeCreadoText.setText("Borrado");
				else
					existeCreadoText.setText("No borrado");
			} catch (Exception e) {
				System.err.println(e);
			}
		if (ficheroCheckbox.isSelected())
			try {
				if (f1.delete())
					existeCreadoText.setText("Borrado");
				else
					existeCreadoText.setText("No borrado");
			} catch (Exception e) {
				System.err.println(e);
			}
	}

	@FXML
	void onModificarFicheroAction(ActionEvent event) throws IOException {
		
		File archivo = new File(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get());
		FileWriter escribir = new FileWriter(archivo);
		String texto = "";
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Modificar fichero");
		dialog.setHeaderText("Introduce el nuevo texto");
		dialog.setContentText("Texto:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    texto = result.get();
		}
		System.out.println(texto);
		for(int i=0; i<texto.length();i++){
		escribir.write(texto.charAt(i));
		}
		escribir.close();
	}

	@FXML
	void onMoverAction(ActionEvent event) {

	}

	@FXML
	void onVerContenidoAction(ActionEvent event) {
		File f1 = new File(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get());

		if (f1.canRead()) {
			String cadena;
			FileReader f = null;
			try {
				f = new FileReader(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader b = new BufferedReader(f);
			try {
				while ((cadena = b.readLine()) != null) {
					contenidoFichero.set(cadena);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onVerFicherosCarpetasAction(ActionEvent event) {
		if (!ficherosList.isEmpty())
			ficherosList = null;
		try {
			// Get the file
			File f = new File(rutaText.textProperty().get() + "\\" + carpetaFicheroText.textProperty().get());
			if (f.exists()) {
				File[] ficheros = f.listFiles();
				for (int x = 0; x < ficheros.length; x++) {
					ficherosList.add(ficheros[x].getName());
				}

			} else {
				existeCreadoText.setText("No existe");
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void borrarDirectorio(File directorio) {
		try {

			File[] ficheros = directorio.listFiles();

			for (int x = 0; x < ficheros.length; x++) {
				if (ficheros[x].isDirectory())
					borrarDirectorio(ficheros[x]);

				ficheros[x].delete();
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
