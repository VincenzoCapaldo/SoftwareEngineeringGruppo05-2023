package softwareengineeringgruppo05;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class RepetitionController implements Initializable {

    @FXML
    private Spinner<Integer> daySpinnerTime;
    @FXML
    private Spinner<Integer> hourSpinnerTime;
    @FXML
    private Spinner<Integer> minuteSpinnerTime;
    @FXML
    private HBox HRepetition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Hsleeping.getChildren().clear();
            // Creazione di nuove SpinnerValueFactory con valori massimo e minimo
            SpinnerValueFactory<Integer> valueFactoryDay = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365, 0);
            SpinnerValueFactory<Integer> valueFactoryHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
            SpinnerValueFactory<Integer> valueFactoryMinutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

            // Impostazione delle nuove SpinnerValueFactory per gli Spinner
            daySpinnerTime.setValueFactory(valueFactoryDay);
            hourSpinnerTime.setValueFactory(valueFactoryHours);
            minuteSpinnerTime.setValueFactory(valueFactoryMinutes);
        
    }    

    @FXML
    private void onDayChanged(KeyEvent event) {
        CheckTimeClass check= new CheckTimeClass();
        check.checkTime(daySpinnerTime, 0, 365);
    }

    @FXML
    private void onHourChanged(KeyEvent event) {
        CheckTimeClass check= new CheckTimeClass();
        check.checkTime(hourSpinnerTime, 0, 23);
    }

    @FXML
    private void onMinuteChaged(KeyEvent event) {
        CheckTimeClass check= new CheckTimeClass();
        check.checkTime(minuteSpinnerTime, 0, 59);
    }
    
    public int getDaysSleeping(){
        return Integer.valueOf(daySpinnerTime.getEditor().getText());
    }
        
    public int getHoursSleeping(){
        return Integer.valueOf(hourSpinnerTime.getEditor().getText());
    }
 
    public int getMinutesSleeping(){
        return Integer.valueOf(minuteSpinnerTime.getEditor().getText());
    }
    
}