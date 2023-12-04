package softwareengineeringgruppo05;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rules.RuleManager;

/**
 *
 * @author maria
 */
public class SoftwareEngineeringGruppo05 extends Application {
    
    private RuleManager ruleManager;
    
    @Override
    public void start(Stage stage) throws Exception {
        ruleManager = RuleManager.getInstance();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Your rules!");
        // Imposta l'icona della finestra (accanto al titolo)
        stage.getIcons().add(new Image("/css/book.png"));
        stage.show();
        
        // Imposta l'azione di chiusura della finestra
        stage.setOnCloseRequest(this::handleWindowClose);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void handleWindowClose(WindowEvent event) {
        ruleManager.interrupThread();
        Platform.exit(); //chiede a javaFX l'uscita dalla piattaforma: chiude JavaFX
        System.exit(0); //termina il processo Java
    }
    
}
