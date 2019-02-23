package sample;

import enumeration.Parity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import static java.awt.event.KeyEvent.VK_BACK_SPACE;


public class ComInterface {
    private COMPort comPort;
    private Packet packet;
    private AnchorPane layer;

    private Label incomeLabel;
    private Label outcomeLabel;
    private Label debugLabel;
    private Label sourceAddressLabel;
    private Label destinationAddressLabel;
    private Label hexPacketLabel;

    private TextField sourceAddressField;
    private TextField destinationAddressField;

    private CheckBox errorBox;

    private TextArea hexPacketData;
    private TextArea incomeData;
    private TextArea outcomeData;

    private ComboBox<String> nameComboBox;
    private ComboBox<Parity> parityComboBox;
    private ComboBox<Integer> speedComboBox;
    private ComboBox<Integer> dataBitsComboBox;
    private ComboBox<Double> stopBitsComboBox;

    private Button sendButton;
    private Button cleanOutcomeButton;
    private Button cleanIncomeButton;
    private Button startButton;
    private Button endButton;
    private Button applyDestAddress;
    private Button applySrcAddress;
    private Button clearDestAddress;
    private Button clearSrcAddress;
    private Button clearHexPacket;

    ComInterface() {
        this.packet = new Packet();
        this.incomeLabel = new Label("Income messages");
        this.outcomeLabel = new Label("Outcome messages");
        this.debugLabel = new Label("Debug area");
        this.sourceAddressLabel = new Label("Source Address");
        this.destinationAddressLabel = new Label("Destination address");
        this.hexPacketLabel = new Label("Hex Packet Per Line");
        this.sourceAddressField = new TextField();
        this.sourceAddressField.setTooltip(new Tooltip("Current value is missed"));
        this.destinationAddressField = new TextField();
        this.destinationAddressField.setTooltip(new Tooltip("Current value is missed"));
        this.errorBox = new CheckBox("Generate errors");
        this.hexPacketData = new TextArea();
        this.hexPacketData.setEditable(false);
        this.incomeData = new TextArea();
        this.outcomeData = new TextArea();
        this.incomeData.setEditable(false);
        this.sendButton = new Button("Send");
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
        this.stopBitsComboBox = new ComboBox<>(FXCollections.observableArrayList((double) 1,
                (double) 1.5, (double)2));
        this.stopBitsComboBox.setDisable(true);
        this.sendButton = new Button("Send");
        this.sendButton.setDisable(true);
        this.cleanOutcomeButton = new Button("Clear");
        this.cleanIncomeButton = new Button("Clear");
        this.startButton = new Button("Start");
        this.startButton.setDisable(true);
        this.endButton = new Button("End");
        this.endButton.setDisable(true);
        this.applyDestAddress = new Button("Apply");
        this.applyDestAddress.setDisable(true);
        this.applySrcAddress = new Button("Apply");
        this.applySrcAddress.setDisable(true);
        this.clearDestAddress = new Button("Clear");
        this.clearSrcAddress = new Button("Clear");
        this.clearHexPacket = new Button("Clear");
        createGui();
    }

    private void createGui() {
        this.incomeLabel.setLayoutX(380.0);
        this.incomeLabel.setLayoutY(192.0);

        this.outcomeLabel.setLayoutX(375.0);
        this.outcomeLabel.setLayoutY(14.0);

        this.debugLabel.setLayoutX(97.0);
        this.debugLabel.setLayoutY(14.0);

        this.sourceAddressLabel.setLayoutX(57.0);
        this.sourceAddressLabel.setLayoutY(357.0);

        this.destinationAddressLabel.setLayoutX(45.0);
        this.destinationAddressLabel.setLayoutY(431.0);

        this.hexPacketLabel.setLayoutX(376.0);
        this.hexPacketLabel.setLayoutY(370.0);

        this.sourceAddressField.setLayoutX(23.0);
        this.sourceAddressField.setLayoutY(374.0);

        this.destinationAddressField.setLayoutX(23.0);
        this.destinationAddressField.setLayoutY(448.0);

        this.errorBox.setLayoutX(23.0);
        this.errorBox.setLayoutY(518.0);

        this.hexPacketData.setPrefSize(300.0, 135.0);
        this.hexPacketData.setFont(new Font(12));
        this.hexPacketData.setLayoutX(274.0);
        this.hexPacketData.setLayoutY(387.0);

        this.incomeData.setPrefSize(300.0, 135.0);
        this.incomeData.setFont(new Font(12));
        this.incomeData.setLayoutX(274.0);
        this.incomeData.setLayoutY(209.0);

        this.outcomeData.setPrefSize(300.0, 135.0);
        this.outcomeData.setFont(new Font(12));
        this.outcomeData.setLayoutX(274.0);
        this.outcomeData.setLayoutY(30.0);

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

        this.sendButton.setLayoutX(274.0);
        this.sendButton.setLayoutY(165.0);
        this.sendButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.cleanOutcomeButton.setLayoutX(531.0);
        this.cleanOutcomeButton.setLayoutY(165.0);
        this.cleanOutcomeButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.cleanIncomeButton.setLayoutX(531.0);
        this.cleanIncomeButton.setLayoutY(344.0);
        this.cleanIncomeButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.startButton.setLayoutX(22.0);
        this.startButton.setLayoutY(307.0);
        this.startButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.endButton.setLayoutX(136.0);
        this.endButton.setLayoutY(307.0);
        this.endButton.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.applyDestAddress.setLayoutX(23.0);
        this.applyDestAddress.setLayoutY(473.0);
        this.applyDestAddress.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.applySrcAddress.setLayoutX(23.0);
        this.applySrcAddress.setLayoutY(399.0);
        this.applySrcAddress.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.clearDestAddress.setLayoutX(128.0);
        this.clearDestAddress.setLayoutY(473.0);
        this.applyDestAddress.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.clearSrcAddress.setLayoutX(128.0);
        this.clearSrcAddress.setLayoutY(399.0);
        this.clearSrcAddress.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.clearHexPacket.setLayoutX(531.0);
        this.clearHexPacket.setLayoutY(522.0);
        this.clearHexPacket.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.layer = new AnchorPane();
    }

    public Parent getLayerCustom() {
        this.layer.getChildren().addAll(
                incomeLabel,
                outcomeLabel,
                debugLabel,
                sourceAddressLabel,
                destinationAddressLabel,
                hexPacketLabel,
                sourceAddressField,
                destinationAddressField,
                errorBox,
                hexPacketData,
                incomeData,
                outcomeData,
                nameComboBox,
                parityComboBox,
                speedComboBox,
                dataBitsComboBox,
                stopBitsComboBox,
                sendButton,
                cleanOutcomeButton,
                cleanIncomeButton,
                startButton,
                endButton,
                applyDestAddress,
                applySrcAddress,
                clearDestAddress,
                clearSrcAddress,
                clearHexPacket
        );
        return this.layer;
    }

    public COMPort getComPort() {
        return comPort;
    }

    public TextField getSourceAddressField() { return sourceAddressField;}

    public TextField getDestinationAddressField() { return destinationAddressField; }

    public CheckBox getErrorBox() { return errorBox; }

    public TextArea getHexPacketData() { return hexPacketData; }

    public TextArea getIncomeData() {
        return incomeData;
    }

    public TextArea getOutcomeData() {
        return outcomeData;
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

    public ComboBox<Double> getStopBitsComboBox() {
        return stopBitsComboBox;
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

    public Button getApplyDestAddress() { return applyDestAddress; }

    public Button getApplySrcAddress() { return applySrcAddress; }

    public Button getClearDestAddress() { return clearDestAddress; }

    public Button getClearSrcAddress() { return clearSrcAddress; }

    public void createComPort(String portName) {
        this.comPort = new COMPort(portName);
    }

    public void setIncomeData(String data){
        this.incomeData.setText(data);
    }

    public Packet getPacket() {
        return packet;
    }

    public Button getClearHexPacket() {return clearHexPacket;}


}
