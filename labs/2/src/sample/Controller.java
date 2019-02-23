package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Tooltip;
import jssc.SerialPortException;
import jssc.SerialPortList;

import static java.awt.event.KeyEvent.VK_BACK_SPACE;

public class Controller {


    private static void changePort(ComInterface portInterface) {
        portInterface.getNameComboBox().setOnAction(event -> {
            portInterface.createComPort(portInterface.getNameComboBox().getSelectionModel().getSelectedItem());
            portInterface.getParityComboBox().setDisable(false);
        });
    }

    private static void changeParity(ComInterface portInterface) {
        portInterface.getParityComboBox().setOnAction(event -> {
            portInterface.getComPort().setPortParity(
                    portInterface.getParityComboBox().getSelectionModel().getSelectedItem());
            portInterface.getSpeedComboBox().setDisable(false);
        });
    }

    private static void changeSpeed(ComInterface portInterface) {
        portInterface.getSpeedComboBox().setOnAction(event -> {
            portInterface.getComPort().setPortSpeed(
                portInterface.getSpeedComboBox().getSelectionModel().getSelectedItem());
            portInterface.getDataBitsComboBox().setDisable(false);
        });
    }

    private static void changeDataBits(ComInterface portInterface) {
        portInterface.getDataBitsComboBox().setOnAction(event -> {
            portInterface.getComPort().setDataBits(
                portInterface.getDataBitsComboBox().getSelectionModel().getSelectedItem());
            portInterface.getStopBitsComboBox().setDisable(false);
        });
    }

    private static void changeNumOfStopBits(ComInterface portInterface) {
        portInterface.getStopBitsComboBox().setOnAction(event -> {
            if(portInterface.getStopBitsComboBox().getSelectionModel().getSelectedItem() == 1.5)
                portInterface.getComPort().setNumOfStopBits(3);
            else if(portInterface.getStopBitsComboBox().getSelectionModel().getSelectedItem() == 1.0)
                portInterface.getComPort().setNumOfStopBits(1);
            else
                portInterface.getComPort().setNumOfStopBits(2);
            portInterface.getStartButton().setDisable(false);
        });
    }

    private static void sendButtonAction(ComInterface portInterface) {
       portInterface.getSendButton().setOnAction(event -> {
           String temp = "";
           if (portInterface.getErrorBox().isSelected()) {
               portInterface.getPacket().setErrorFCS();
               portInterface.getPacket().encode(portInterface.getOutcomeData().getText());
           }
           else {
               portInterface.getPacket().setCorrectFCS();
               portInterface.getPacket().encode(portInterface.getOutcomeData().getText());
               portInterface.getPacket().decodeAll(portInterface.getPacket().getBytePackets());
               portInterface.getComPort().setByteData(portInterface.getPacket().getBytePackets());
               portInterface.getComPort().sendBytes();
           }

           for (int i = 0; i < portInterface.getPacket().getPackets().size(); i++)
               temp = temp + portInterface.getPacket().HexStringPacket(portInterface.getPacket().getBytePackets().get(i)) + "\n";
           portInterface.getHexPacketData().setText(temp);

           portInterface.getOutcomeData().setText("");
           portInterface.getComPort().setData("");
       });

    }

    private static void cleanOutcomeButtonAction(ComInterface portInterface) {
        portInterface.getCleanOutcomeButton().setOnAction(event -> {
            portInterface.getOutcomeData().setText("");
            portInterface.getComPort().setData("");
        });
    }

    private static void cleanIncomeButtonAction(ComInterface portInterface) {
        portInterface.getCleanIncomeButton().setOnAction(event -> {
            portInterface.getIncomeData().setText("");
            portInterface.getComPort().setData("");
            portInterface.getComPort().setChatHistory("");
        });
    }

    private static void startButtonAction(ComInterface portInterface) {
        portInterface.getStartButton().setOnMouseClicked(event -> {
            portInterface.getComPort().openPort();
            portInterface.getSendButton().setDisable(false);
            portInterface.getEndButton().setDisable(false);
            portInterface.getStartButton().setDisable(true);
            portInterface.getNameComboBox().setDisable(true);
            portInterface.getParityComboBox().setDisable(true);
            portInterface.getSpeedComboBox().setDisable(true);
            portInterface.getDataBitsComboBox().setDisable(true);
            portInterface.getStopBitsComboBox().setDisable(true);
            portInterface.getOutcomeData().setEditable(true);
            try {
                portInterface.getComPort().getPort().addEventListener(
                        new Communication(portInterface.getComPort(), portInterface)
                );
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            if(portInterface.getComPort().getData().isEmpty()) {
                portInterface.getSendButton().setDisable(true);
            }
            portInterface.setIncomeData(portInterface.getIncomeData().getText());
        });
    }

    private static void endButtonAction(ComInterface portInterface) {
        portInterface.getEndButton().setOnAction(event -> {
            portInterface.getComPort().closePort();
            portInterface.getStartButton().setDisable(false);
            portInterface.getEndButton().setDisable(true);
            portInterface.getNameComboBox().setDisable(false);
            portInterface.getParityComboBox().setDisable(false);
            portInterface.getSpeedComboBox().setDisable(false);
            portInterface.getDataBitsComboBox().setDisable(false);
            portInterface.getStopBitsComboBox().setDisable(false);
            portInterface.getOutcomeData().setEditable(false);
        });
    }

    private static void outcomeDataAction(ComInterface portInterface) {
        portInterface.getOutcomeData().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty() && (!portInterface.getPacket().getSrcAddress().isEmpty() ||
                !portInterface.getPacket().getDestAddress().isEmpty()))
                    portInterface.getSendButton().setDisable(false);
                else
                    portInterface.getSendButton().setDisable(true);
            }
        });
    }

    private static void incomeDataAction(ComInterface portInterface) { }

    private static void clearSrcAddressButtonAction(ComInterface portInterface) {
        portInterface.getClearSrcAddress().setOnAction(event -> {
            portInterface.getSourceAddressField().setText("");
        });
    }

    private static void clearDestAddressButtonAction(ComInterface portInterface) {
        portInterface.getClearDestAddress().setOnAction(event -> {
            portInterface.getDestinationAddressField().setText("");
        });
    }

    private static void clearHexPacketArea(ComInterface portInterface) {
        portInterface.getClearHexPacket().setOnAction(event -> {
            portInterface.getHexPacketData().setText("");
        });
    }

    private static void srcAddressListener(ComInterface portInterface) {
        portInterface.getSourceAddressField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty())
                    portInterface.getApplySrcAddress().setDisable(false);
                else
                    portInterface.getApplySrcAddress().setDisable(true);
                if(!newValue.matches("^$|^[0-9]$|^[1-9][0-9]$|^1[0-9][0-9]$|^2[0-4][0-9]$|^25[0-5]$"))
                    portInterface.getSourceAddressField().setText(oldValue);
            }
        });
    }

    private static void destAddressListener(ComInterface portInterface) {
        portInterface.getDestinationAddressField().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty())
                    portInterface.getApplyDestAddress().setDisable(false);
                else
                    portInterface.getApplyDestAddress().setDisable(true);
                if(!newValue.matches("^$|^[0-9]$|^[1-9][0-9]$|^1[0-9][0-9]$|^2[0-4][0-9]$|^25[0-5]$"))
                    portInterface.getDestinationAddressField().setText(oldValue);
            }
        });
    }

    private static void applySourceButtonAction(ComInterface portInterface) {
        portInterface.getApplySrcAddress().setOnAction(event -> {
            portInterface.getPacket().setScrAddress(Integer.parseInt(portInterface.getSourceAddressField().getText()));
            if(!portInterface.getPacket().getDestAddress().isEmpty())
                portInterface.getSendButton().setDisable(false);
            try {
                System.out.println("\n" + portInterface.getPacket().fromIntToByte(Integer.parseInt(portInterface.getSourceAddressField().getText()))+ "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            portInterface.getSourceAddressField().getTooltip().setText("Current value is: " +
                    portInterface.getSourceAddressField().getText());
        });
    }

    private static void applyDestButtonAction(ComInterface portInterface) {
        portInterface.getApplyDestAddress().setOnAction(event -> {
            portInterface.getPacket().setDestAddress(Integer.parseInt(portInterface.getDestinationAddressField().getText()));
            if(!portInterface.getPacket().getSrcAddress().isEmpty())
                portInterface.getSendButton().setDisable(false);
            try {
                System.out.println("\n" + portInterface.getPacket().fromIntToByte(Integer.parseInt(portInterface.getDestinationAddressField().getText()))+ "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            portInterface.getDestinationAddressField().getTooltip().setText("Current value is: " +
                    portInterface.getDestinationAddressField().getText());
        });
    }

    public static void init(ComInterface portInterface) {
        changePort(portInterface);
        changeParity(portInterface);
        changeSpeed(portInterface); 
        changeDataBits(portInterface);
        changeNumOfStopBits(portInterface);
        cleanIncomeButtonAction(portInterface);
        cleanOutcomeButtonAction(portInterface);
        sendButtonAction(portInterface);
        startButtonAction(portInterface);
        endButtonAction(portInterface);
        outcomeDataAction(portInterface);
        incomeDataAction(portInterface);
        clearDestAddressButtonAction(portInterface);
        clearSrcAddressButtonAction(portInterface);
        srcAddressListener(portInterface);
        destAddressListener(portInterface);
        applySourceButtonAction(portInterface);
        applyDestButtonAction(portInterface);
        clearHexPacketArea(portInterface);
    }

}
