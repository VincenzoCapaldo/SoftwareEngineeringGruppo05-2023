/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package triggers.FileSizeTrigger;

/**
 *
 * @author enzo0
 */
public enum FileSizeUnit {
    B("Byte", 1),
    KB("Kilobyte", (long) Math.pow(10, 3)),
    MB("Megabyte", (long) Math.pow(10, 6)),
    GB("Gigabyte", (long) Math.pow(10, 9)),
    TB("Terabyte", (long) Math.pow(10, 12));

    private final String label;
    private final long multiplier;

    FileSizeUnit(String label, long multiplier) {
        this.label = label;
        this.multiplier = multiplier;
    }

    public String getLabel() {
        return label;
    }

    public long getMultiplier() {
        return multiplier;
    }
    
}

