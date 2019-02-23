package enumeration;
import jssc.SerialPort;

public enum Parity {
    NONE("NONE", SerialPort.PARITY_NONE),
    ODD("ODD", SerialPort.PARITY_ODD),
    EVEN("EVEN", SerialPort.PARITY_EVEN),
    MARK("MARK", SerialPort.PARITY_MARK),
    SPACE("SPACE", SerialPort.PARITY_SPACE);

    private String name;
    private int amountsOfBits;

    Parity(String name, int amountOfBits) {
        this.name = name;
        this.amountsOfBits = amountOfBits;
    }

    public int getAmountsOfBits() {
        return amountsOfBits;
    }
}

