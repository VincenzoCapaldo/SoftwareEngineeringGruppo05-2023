package softwareengineeringgruppo05;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import rule.RuleManager;

/**
 *
 * @author maria
 */
public class SoftwareEngineeringGruppo05 extends Application {
    
    private RuleManager ruleManager;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        ruleManager = RuleManager.getInstance(); //istanza del RuleManager
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); //FXML principale
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Your rules!"); //titolo della finestra
        stage.getIcons().add(new Image("css/book.png")); //icona della finestra
        stage.show();
        
        stage.setOnCloseRequest(this::handleWindowClose); // Imposta l'azione di chiusura della finestra
       
    }

    private void handleWindowClose(WindowEvent event) {
        ruleManager.interruptThread(); //interrompe tutti i thread in esecuzione
        Platform.exit(); //chiude JavaFX
        System.exit(0); //termina il processo
    }
    
}
