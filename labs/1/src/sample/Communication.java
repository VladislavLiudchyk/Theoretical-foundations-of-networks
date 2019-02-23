package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import sample.COMPort;

public class Communication implements SerialPortEventListener {
    private COMPort port;
    private ComInterface comInterface;

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                port.setData(port.getPort().readString());
                port.setChatHistory(port.getData());
                comInterface.getIncomeData().setText("" + port.getChatHistory());
                System.out.println("\nPort Data: " + port.getData());
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            }
        }
    }
    public Communication(COMPort port, ComInterface comInterface) {
        this.port = port;
        this.comInterface = comInterface;
    }
}
