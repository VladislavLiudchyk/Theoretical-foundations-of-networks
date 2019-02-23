import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

    private GUI gui;

    @Override
    public void start(Stage primaryStage) throws Exception{
        gui = new GUI();
        //Controller.init(gui);

        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 600);
        root.getChildren().add(gui.getLayerCustom());
        primaryStage.setOnCloseRequest(event -> {
            //Controller.getCalc().interrupt();
            primaryStage.close();
        });
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

