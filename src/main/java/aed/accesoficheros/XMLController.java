package aed.accesoficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class XMLController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private TextField rutaText;

	@FXML
	private Button verContenidoButton;

	@FXML
	private TextField futbolistaText;

	@FXML
	private TextField fechaInicioText;

	@FXML
	private TextField fechaFinText;

	@FXML
	private Button insertarContrato;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField copasText;

	@FXML
	private Button modificarCopasButton;

	@FXML
	private Button eliminarEquipoButton;

	@FXML
	private Button escribirFichero;

	@FXML
	private TextArea contenidoFicheroText;

	public XMLController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/XMLView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	void onEliminarAction(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document documentJDOM = builder.build(new FileInputStream("Equipos.XML"));

		Element raiz = documentJDOM.getRootElement();

		List<Element> hijosRaiz = raiz.getChildren();

		Element hijoEliminar = null;

		for (Element hijo : hijosRaiz) {
			String nombre = hijo.getAttributeValue("nomEquipo");
			if (nombre != null) {
				if (nombre.equals(nombreText.textProperty().get()))
					hijoEliminar = hijo;
			}
		}
		hijosRaiz.remove(hijoEliminar);

		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream file = new FileOutputStream("Equipos.XML");
		out.output(documentJDOM, file);

	}

	@FXML
	void onEscribirOtroFicheroAction(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document documentJDOM = builder.build(new FileInputStream("Equipos.XML"));

		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream file = new FileOutputStream(rutaText.textProperty().get());
		out.output(documentJDOM, file);
	}

	@FXML
	void onInsertarContratoAction(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document documentJDOM = builder.build(new FileInputStream("Equipos.XML"));

		Element raiz = documentJDOM.getRootElement();

		List<Element> hijosRaiz = raiz.getChildren();

		for (Element hijo : hijosRaiz) {
			String nombre = hijo.getAttributeValue("nomEquipo");
			if (nombre != null) {
				if (nombre.equals(nombreText.textProperty().get())) {
					Element etiquetaNueva = new Element("Futbolista");

					etiquetaNueva.setAttribute("fechaInicio", fechaInicioText.textProperty().get());
					etiquetaNueva.setAttribute("fechaFin", fechaFinText.textProperty().get());
					etiquetaNueva.setText(futbolistaText.textProperty().get());

					Element etiquetaHija = (Element) hijo.getChild("Contratos");
					etiquetaHija.addContent(etiquetaNueva);
				}
			}
		}
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream file = new FileOutputStream("Equipos.XML");
		out.output(documentJDOM, file);
	}

	@FXML
	void onModificarCopasAction(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document documentJDOM = builder.build(new FileInputStream("Equipos.XML"));

		Element raiz = documentJDOM.getRootElement();

		List<Element> hijosRaiz = raiz.getChildren();

		for (Element hijo : hijosRaiz) {
			String nombre = hijo.getAttributeValue("nomEquipo");
			if (nombre != null) {
				if (nombre.equals(nombreText.textProperty().get()))
					hijo.setAttribute("copasGanadas", copasText.textProperty().get());
			}
		}

		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream file = new FileOutputStream("Equipos.XML");
		out.output(documentJDOM, file);
	}

	@FXML
	void onVerContenidoAction(ActionEvent event) throws FileNotFoundException, JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document documentJDOM = builder.build(new FileInputStream("Equipos.XML"));
		String contenido = "";
		Element raiz = documentJDOM.getRootElement();

		List<Element> hijosRaiz = raiz.getChildren();

		for (Element hijo : hijosRaiz) {
			String nombre = hijo.getName();
			String texto = hijo.getValue();

			contenido += ("\nEtiqueta: " + nombre);

			String id = hijo.getAttributeValue("nomEquipo");
			if (id != null)
				contenido += ("\nNombre Equipo: " + id);
			id = hijo.getAttributeValue("copasGanadas");
			if (id != null)
				contenido += ("\nCopas Ganadas: " + id);

			List<Element> hijosHijo = hijo.getChildren();

			for (Element hijo2 : hijosHijo) {
				String nombre2 = hijo2.getName();
				String texto2 = hijo2.getValue();

				if (nombre2 == "Contratos") {
					contenido += ("\nEtiqueta: " + nombre2);
					List<Element> hijos3 = hijo2.getChildren();

					for (Element hijo3 : hijos3) {
						String nombre3 = hijo3.getName();
						String texto3 = hijo3.getValue();

						contenido += ("\n	Etiqueta: " + nombre3 + ". Texto: " + texto3);

						id = hijo3.getAttributeValue("fechaInicio");
						if (id != null)
							contenido += ("\tFecha Inicio: " + id);

						id = hijo3.getAttributeValue("fechaFin");
						if (id != null)
							contenido += ("\tFecha Fin: " + id);
					}
				} else
					contenido += ("\nEtiqueta: " + nombre2 + ". Texto: " + texto2);
			}
		}
		contenidoFicheroText.setText(contenido);
	}

	public GridPane getView() {
		return view;
	}

}
