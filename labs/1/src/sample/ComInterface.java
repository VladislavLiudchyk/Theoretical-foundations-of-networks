package sample;

import enumeration.Parity;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import sample.COMPort;


public class ComInterface {
    private COMPort comPort;
    private AnchorPane layer;

    private Label incomeLabel;
    private Label outcomeLabel;
    private Label debugLabel;

    private TextArea incomeData;
    private TextArea outcomeData;


    private ComboBox<String> nameComboBox;
    private ComboBox<Integer> speedComboBox;
    private ComboBox<Parity> parityComboBox;
    private ComboBox<Integer> dataBitsComboBox;
    private ComboBox<Integer> stopBitsComboBox;

    private Button sendButton;
    private Button cleanOutcomeButton;
    private Button cleanIncomeButton;
    private Button startButton;
    private Button endButton;

    ComInterface() {
        this.incomeLabel = new Label("Income messages");
        this.outcomeLabel = new Label("Outcome messages");
        this.debugLabel = new Label("Debug area");
        this.incomeData = new TextArea();
        this.outcomeData = new TextArea();
        this.incomeData.setEditable(false);
        this.nameComboBox = new ComboBox<>(FXCollections.observableArrayList("COM1", "COM2", "COM3", "COM4",
                "COM5", "COM6", "COM7", "COM8", "COM9", "COM10", "COM11", "COM12"));
        this.speedComboBox = new ComboBox<>(FXCollections.observableArrayList(SerialPort.BAUDRATE_110,
                SerialPort.BAUDRATE_300, SerialPort.BAUDRATE_600, SerialPort.BAUDRATE_1200, SerialPort.BAUDRATE_4800,
                SerialPort.BAUDRATE_9600, SerialPort.BAUDRATE_14400, SerialPort.BAUDRATE_19200,
                SerialPort.BAUDRATE_38400, SerialPort.BAUDRATE_57600, SerialPort.BAUDRATE_115200,
                SerialPort.BAUDRATE_128000, SerialPort.BAUDRATE_256000));
        this.speedComboBox.setDisable(true);
        this.parityComboBox = new ComboBox<>(FXCollections.observableArrayList(Parity.NONE, Parity.ODD, Parity.EVEN,
        Parity.MARK, Parity.SPACE));
        this.parityComboBox.setDisable(true);
        this.dataBitsComboBox = new ComboBox<>(FXCollections.observableArrayList(SerialPort.DATABITS_5,
                SerialPort.DATABITS_6, SerialPort.DATABITS_7, SerialPort.DATABITS_8));
        this.dataBitsComboBox.setDisable(true);
        this.stopBitsComboBox = new ComboBox<>(FXCollections.observableArrayList(SerialPort.STOPBITS_1,
                SerialPort.STOPBITS_2, SerialPort.STOPBITS_1_5));
        this.stopBitsComboBox.setDisable(true);
        this.sendButton = new Button("Send");
        this.sendButton.setDisable(true);
        this.cleanOutcomeButton = new Button("Clear");
        this.cleanIncomeButton = new Button("Clear");
        this.startButton = new Button("Start");
        this.startButton.setDisable(true);
        this.endButton = new Button("End");
        this.endButton.setDisable(true);
        createGui();
    }

    private void createGui() {
        this.outcomeLabel.setLayoutX(375.0);
        this.outcomeLabel.setLayoutY(14.0);

        this.outcomeData.setPrefSize(300.0, 135.0);
        this.outcomeData.setFont(new Font(12));
        this.outcomeData.setLayoutX(274.0);
        this.outcomeData.setLayoutY(30.0);

        this.sendButton.setLayoutX(274.0);
        this.sendButton.setLayoutY(165.0);
        this.sendButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.cleanOutcomeButton.setLayoutX(531.0);
        this.cleanOutcomeButton.setLayoutY(165.0);
        this.cleanOutcomeButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.incomeLabel.setLayoutX(380.0);
        this.incomeLabel.setLayoutY(192.0);

        this.incomeData.setPrefSize(300.0, 135.0);
        this.incomeData.setFont(new Font(12));
        this.incomeData.setLayoutX(274.0);
        this.incomeData.setLayoutY(209.0);

        this.cleanIncomeButton.setLayoutX(531.0);
        this.cleanIncomeButton.setLayoutY(344.0);
        this.cleanIncomeButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.debugLabel.setLayoutX(97.0);
        this.debugLabel.setLayoutY(14.0);

        this.nameComboBox.setPromptText("Port Name");
        this.nameComboBox.setPrefWidth(150.0);
        this.nameComboBox.setPrefHeight(25.0);
        this.nameComboBox.setLayoutX(22.0);
        this.nameComboBox.setLayoutY(31.0);

        this.parityComboBox.setPromptText("Parity");
        this.parityComboBox.setPrefWidth(150.0);
        this.parityComboBox.setPrefHeight(25.0);
        this.parityComboBox.setLayoutX(22.0);
        this.parityComboBox.setLayoutY(85.0);

        this.speedComboBox.setPromptText("Speed");
        this.speedComboBox.setPrefWidth(150.0);
        this.speedComboBox.setPrefHeight(25.0);
        this.speedComboBox.setLayoutX(22.0);
        this.speedComboBox.setLayoutY(140.0);

        this.dataBitsComboBox.setPromptText("Data bits");
        this.dataBitsComboBox.setPrefWidth(150.0);
        this.dataBitsComboBox.setPrefHeight(25.0);
        this.dataBitsComboBox.setLayoutX(22.0);
        this.dataBitsComboBox.setLayoutY(197.0);

        this.stopBitsComboBox.setPromptText("Num of stop bits");
        this.stopBitsComboBox.setPrefWidth(150.0);
        this.stopBitsComboBox.setPrefHeight(25.0);
        this.stopBitsComboBox.setLayoutX(22.0);
        this.stopBitsComboBox.setLayoutY(252.0);

        this.startButton.setLayoutX(22.0);
        this.startButton.setLayoutY(344.0);
        this.startButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.endButton.setLayoutX(136.0);
        this.endButton.setLayoutY(344.0);
        this.endButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.layer = new AnchorPane();
    }

    public Parent getLayerCustom() {
        this.layer.getChildren().addAll(
                debugLabel,
                nameComboBox,
                parityComboBox,
                speedComboBox,
                dataBitsComboBox,
                stopBitsComboBox,
                startButton,
                endButton,
                outcomeLabel,
                outcomeData,
                sendButton,
                cleanOutcomeButton,
                incomeLabel,
                incomeData,
                cleanIncomeButton
        );
        return this.layer;
    }

    public COMPort getComPort() {
        return comPort;
    }

    public TextArea getIncomeData() {
        return incomeData;
    }

    public TextArea getOutcomeData() {
        return outcomeData;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public Button getCleanOutcomeButton() {
        return cleanOutcomeButton;
    }

    public Button getCleanIncomeButton() {
        return cleanIncomeButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getEndButton() {
        return endButton;
    }

    public ComboBox<String> getNameComboBox() {
        return nameComboBox;
    }

    public ComboBox<Integer> getSpeedComboBox() {
        return speedComboBox;
    }

    public ComboBox<Parity> getParityComboBox() {
        return parityComboBox;
    }

    public ComboBox<Integer> getDataBitsComboBox() {
        return dataBitsComboBox;
    }

    public ComboBox<Integer> getStopBitsComboBox() {
        return stopBitsComboBox;
    }

    public void createComPort(String portName) {
        this.comPort = new COMPort(portName);
    }

    public void setIncomeData(String data){
        this.incomeData.setText(data);
    }


}
