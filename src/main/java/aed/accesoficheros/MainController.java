package aed.accesoficheros;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.spi.InitialContextFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable{

	private AccesoFicherosController accesoFicherosController = new AccesoFicherosController();
    private RandomController randomController = new RandomController();
    private XMLController xmlController = new XMLController();
    
	@FXML
    private TabPane view;

    @FXML
    private Tab accesoFicherosTab;

    @FXML
    private Tab accesoAleatorioTab;

    @FXML
    private Tab accesoXMLTab;

    public MainController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
    	loader.setController(this);
    	loader.load();
    }
    
    public TabPane getView() {
    	return view;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accesoFicherosTab.setContent(accesoFicherosController.getView());
		accesoAleatorioTab.setContent(randomController.getView());
		accesoXMLTab.setContent(xmlController.getView());
	}
	
	
}
