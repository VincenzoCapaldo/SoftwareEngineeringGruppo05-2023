package triggers.fileSizeTrigger;

/**
 *
 * @author enzo0
 */
public enum FileSizeUnit {
    B("Byte", 1),
    KB("Kilobyte", (long) Math.pow(2, 10)),
    MB("Megabyte", (long) Math.pow(2, 20)),
    GB("Gigabyte", (long) Math.pow(2, 30)),
    TB("Terabyte", (long) Math.pow(2, 40));

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

