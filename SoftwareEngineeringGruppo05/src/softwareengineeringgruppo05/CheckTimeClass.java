package softwareengineeringgruppo05;

import javafx.scene.control.Spinner;

/**
 *
 * @author maria
 */
public class CheckTimeClass {
    
    public void checkTime(Spinner<Integer> timeSpinner, int minValue, int maxValue){
        String inputText = timeSpinner.getEditor().getText();

        // Se l'inputText è vuoto, non fare nulla
        if (!inputText.isEmpty()) {
            // Se l'inputText non contiene solo cifre
            if (!inputText.matches("\\d+")) {
                // Reimposta il testo a un valore predefinito
                timeSpinner.getEditor().setText(String.valueOf(minValue));
            } else {
                int value = Integer.parseInt(inputText);
                if (value < minValue || value > maxValue) {
                    // Reimposta il testo a un valore predefinito
                    timeSpinner.getEditor().setText(String.valueOf(minValue));
                }
            }
        }
    }
}
    
