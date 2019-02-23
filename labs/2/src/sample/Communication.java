package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import sample.COMPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Communication implements SerialPortEventListener {
    private COMPort port;
    private ComInterface comInterface;
    List<byte[]> kek = new ArrayList<>();
    byte[] temp;

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                temp = port.getPort().readBytes();
                if(temp.length > 1 && ((Integer.parseUnsignedInt(comInterface.getPacket().getSrcAddress(), 2)) !=
                        Byte.toUnsignedInt(temp[2])))
                    return;
                if (temp.length == 1) {
                    comInterface.getPacket().decodeAll(kek);
                    kek.clear();
                    System.out.println("Income: " + comInterface.getPacket().getData());
                    port.setData(comInterface.getPacket().getData());
                    if(!port.getData().isEmpty()) {
                        port.setChatHistory(port.getData());
                        comInterface.getIncomeData().setText("" + port.getChatHistory());
                        System.out.println("\nPort Data" + port.getData());
                    }
                    comInterface.getPacket().getBytePackets().clear();
                    return;
                }
                System.out.println("Num of bytes was read: " + temp.length);
                kek.add(temp);

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
