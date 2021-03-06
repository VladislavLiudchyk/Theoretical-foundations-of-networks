package sample;
import enumeration.Parity;
import jssc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class COMPort {
    private String name;
    private SerialPort port;
    private DataUpdator dataUpdator;
    private int portSpeed;
    private Parity portParity;
    private String data;
    private List<byte[]> byteData;
    private  int dataBits;
    private Integer numOfStopBits;
    private String chatHistory;


    public COMPort(String portName) {
        List<byte[]> byteData = new ArrayList<>();
        name = portName;
        data = "";
        chatHistory = "";
        port = new SerialPort(portName);
        portSpeed = SerialPort.BAUDRATE_9600;
        portParity = Parity.NONE;
    }

    public void setData(String data) {
        this.data = data;
        dataUpdator = new DataUpdator();
        dataUpdator.update(chatHistory);

    }


    public void openPort() {
        try {
                this.port.openPort();
                setParams();
        } catch (SerialPortException e) {
            dataUpdator = new DataUpdator();
            dataUpdator.setError("ERROR! Port already opened!");
            e.printStackTrace();
        }
    }

    private void setParams() {
        try {
            this.port.setParams(portSpeed,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    portParity.getAmountsOfBits());
            this.port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    public void closePort() {
        try {
            this.port.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void sendBytes() {
        for(int i = 0; i < byteData.size(); i++) {
            try {
                this.port.writeBytes(byteData.get(i));

                Thread.sleep(100);
            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            this.port.writeByte((byte) 0);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public SerialPort getPort() {
        return port;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPort(String name) {
        this.name = name;
        this.port = new SerialPort(name);
        this.portSpeed = SerialPort.BAUDRATE_9600;
        this.portParity = Parity.NONE;
    }

    public void setPortSpeed (Integer portSpeed) {
        this.portSpeed = portSpeed;
    }

    public void setPortParity (Parity portParity) {
        this.portParity = portParity;
    }

    public void setDataBits (Integer dataBits) {
        this.dataBits = dataBits;
    }

    public void setNumOfStopBits(Integer numOfStopBits) {
        this.numOfStopBits = numOfStopBits;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setByteData(List<byte[]> byteData) {
        this.byteData = byteData;
    }

    public List<byte[]> getByteData() {
        return byteData;
    }

    public void setChatHistory(String chatHistory) {
        if (chatHistory.equals(""))
            this.chatHistory = chatHistory;
        if (this.chatHistory.equals(""))
            this.chatHistory = chatHistory;
        else
            this.chatHistory = this.chatHistory + "\n" + chatHistory;
        System.out.println("\nChat:" + this.chatHistory + "\n");

    }

}
