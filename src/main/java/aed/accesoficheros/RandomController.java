package aed.accesoficheros;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RandomController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private TextField rutaText;

	@FXML
	private Button verContenidoButton;

	@FXML
	private TextField idText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField ligaText;

	@FXML
	private TextField localidadText;

	@FXML
	private TextField copasText;

	@FXML
	private TextField internacionalText;

	@FXML
	private Button insertarRegistro;

	@FXML
	private Button modificarCopasButton;

	@FXML
	private Button verEquipoButton;

	@FXML
	private TextArea contenidoFicheroText;

	public RandomController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RandomView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void onInsertarRegistroAction(ActionEvent event) throws IOException {

		String[] datos = new String[5];
		datos[0] = (nombreText.textProperty().get() + "                                                  ").substring(0,
				40);
		datos[1] = (ligaText.textProperty().get() + "     ").substring(0, 5);
		datos[2] = (localidadText.textProperty().get() + "                                                                                     ")
				.substring(0, 60);
		datos[3] = copasText.textProperty().get();
		datos[4] = internacionalText.textProperty().get();

		RandomAccessFile fichero = new RandomAccessFile(rutaText.textProperty().get(), "rw");
		int id = 0;
		char separador = ',';

		if (fichero.length() == 0)
			id = 1;
		else {
			fichero.seek(fichero.length() - 231);
			id = fichero.readInt() + 1;
			fichero.seek(fichero.length());
		}

		fichero.writeInt(id);
		fichero.writeChar(separador);
		// nombre
		fichero.writeChars(datos[0]);
		fichero.writeChar(separador);
		// liga
		fichero.writeChars(datos[1]);
		fichero.writeChar(separador);
		// localidad
		fichero.writeChars(datos[2]);
		fichero.writeChar(separador);
		// Copas
		fichero.writeInt(Integer.parseInt(datos[3]));
		fichero.writeChar(separador);
		// Internacional
		fichero.writeBoolean(Boolean.parseBoolean(datos[4]));
		fichero.writeChar(separador);
	}

	@FXML
	void onModificarCopasAction(ActionEvent event) throws IOException {
		RandomAccessFile fichero = new RandomAccessFile(rutaText.textProperty().get(), "rw");
		int id = Integer.parseInt(idText.textProperty().get());
		int Copas = Integer.parseInt(copasText.textProperty().get());
		Charset charset = StandardCharsets.UTF_8;

		char Separador = ',';
		String s1 = "";
		if (fichero.length() == 0)
			contenidoFicheroText.setText("No hay nada que modificar");
		else {
			id = (id - 1) * 231;
			fichero.seek(id + 231);

			fichero.writeInt(Copas);
		}
	}

	@FXML
	void onVerContenidoAction(ActionEvent event) throws IOException {
		int ID;

		RandomAccessFile fichero = new RandomAccessFile(rutaText.textProperty().get(), "r");

		Charset charset = StandardCharsets.UTF_8;

		char separador = ',';
		String s1 = "";
		String contenido = "";

		if (fichero.length() == 0)
			contenidoFicheroText.setText("No hay nada que visualizar");
		else {
			while (fichero.getFilePointer() < fichero.length()) {

				contenido += ("ID: " + fichero.readInt());
				contenido += fichero.readChar();

				byte[] arr1 = new byte[40];
				fichero.readFully(arr1);
				s1 = new String(arr1, charset);
				contenido += ("Nombre Equipo: " + s1);

				contenido += fichero.readChar();

				byte[] arr2 = new byte[5];
				fichero.readFully(arr2);
				s1 = new String(arr2, charset);
				contenido += ("Codigo Liga: " + s1);

				contenido += fichero.readChar();

				byte[] arr3 = new byte[60];
				fichero.readFully(arr3);
				s1 = new String(arr3, charset);
				contenido += ("Localidad: " + s1);

				contenido += fichero.readChar();

				contenido += ("Copas ganadas: " + fichero.readInt());

				contenido += fichero.readChar();

				contenido += ("Internacional " + fichero.readBoolean());

				contenido += fichero.readChar() + "\n";

			}
			contenidoFicheroText.setText(contenido);
		}
	}

	@FXML
	void onVerDatosEquipoAction(ActionEvent event) throws IOException {
		int id = Integer.parseInt(idText.textProperty().get());

		RandomAccessFile fichero = new RandomAccessFile(rutaText.textProperty().get(), "rw");

		Charset charset = StandardCharsets.UTF_8;

		char separador = ',';
		String s1 = "";
		String contenido = "";
		if (fichero.length() == 0)
			contenidoFicheroText.setText("No hay datos a visualizar");
		else {
			id = (id - 1) * 231;
			fichero.seek(id);

			contenido += ("ID: " + fichero.readInt());

			contenido += fichero.readChar();

			byte[] arr1 = new byte[40];
			fichero.readFully(arr1);
			s1 = new String(arr1, charset);
			contenido += ("Nombre Equipo: " + s1);

			contenido += fichero.readChar();

			byte[] arr2 = new byte[5];
			fichero.readFully(arr2);
			s1 = new String(arr2, charset);
			contenido += ("Codigo Liga: " + s1);

			contenido += fichero.readChar();

			byte[] arr3 = new byte[60];
			fichero.readFully(arr3);
			s1 = new String(arr3, charset);
			contenido += ("Localidad: " + s1);

			contenido += fichero.readChar();

			contenido += ("Copas ganadas: " + fichero.readInt());

			contenido += fichero.readChar();

			contenido += ("Internacional " + fichero.readBoolean());

			contenido += fichero.readChar();

		}
	}

	public GridPane getView() {
		return view;
	}
}
