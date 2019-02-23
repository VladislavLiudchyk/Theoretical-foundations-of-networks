package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import sample.ComInterface;

import java.util.Scanner;

public class Main extends Application {

    private ComInterface comport;

    @Override
    public void start(Stage primaryStage) throws Exception{
        comport = new ComInterface();
        Controller.init(comport);

        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 600);
        root.getChildren().add(comport.getLayerCustom());
        primaryStage.setOnCloseRequest(event -> {
            if(comport.getNameComboBox().getSelectionModel().getSelectedIndex() >= 0)
                if(comport.getComPort().getPort().isOpened())
                    comport.getComPort().closePort();
        });
        primaryStage.setTitle("Serial Port Communication");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

