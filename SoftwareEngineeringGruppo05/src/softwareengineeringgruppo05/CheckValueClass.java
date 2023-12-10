package softwareengineeringgruppo05;

import javafx.scene.control.Spinner;

/**
 *
 * @author maria
 */
public class CheckValueClass {
    
    public void checkValue(Spinner<Integer> timeSpinner, int minValue, int maxValue){
        // Preleva il testo dallo spinner
        String inputText = timeSpinner.getEditor().getText();

        // Se l'inputText è vuoto, non fare nulla
        if (!inputText.isEmpty()) {
            // Se l'inputText non contiene solo cifre
            if (!inputText.matches("\\d+")) {
                // Reimposta il testo a un valore predefinito
                timeSpinner.getEditor().setText(String.valueOf(minValue));
            } else {
                // Converte l'inputText in un integer
                int value = Integer.parseInt(inputText);
                // Se il valore non è consentito
                if (value < minValue || value > maxValue) {
                    // Reimposta il testo a un valore predefinito
                    timeSpinner.getEditor().setText(String.valueOf(minValue));
                }
            }
        }
    }
    
}