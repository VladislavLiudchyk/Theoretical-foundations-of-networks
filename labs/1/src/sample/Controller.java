package sample;

import javafx.fxml.FXML;
import jssc.SerialPortException;
import jssc.SerialPortList;

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
            portInterface.getComPort().setNumOfStopBits(
                    portInterface.getStopBitsComboBox().getSelectionModel().getSelectedItem());
            portInterface.getStartButton().setDisable(false);
        });
    }

    private static void sendButtonAction(ComInterface portInterface) {
       portInterface.getSendButton().setOnAction(event -> {
           portInterface.getComPort().sendBytes();
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
        portInterface.getOutcomeData().setOnKeyReleased(event -> {
            portInterface.getComPort().setData(
                    portInterface.getOutcomeData().getText());
            if(portInterface.getComPort().getData().isEmpty()) {
                portInterface.getSendButton().setDisable(true);
                return;
            }
            portInterface.getSendButton().setDisable(false);
        });
    }

    private static void incomeDataAction(ComInterface portInterface) { }

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
    }

}
