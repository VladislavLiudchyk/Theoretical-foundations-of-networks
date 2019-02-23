import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class GUI {

    private AnchorPane layer;

    private Label incomeLabel;
    private Label outcomeLabel;
    private Label debugLabel;

    private TextArea incomeData;
    private TextArea outcomeData;
    private TextArea debugData;

    //private Button sendButton;

    GUI() {
        this.incomeLabel = new Label("Input");
        this.outcomeLabel = new Label("Output");
        this.debugLabel = new Label("Debug");
        this.incomeData = new TextArea();
        this.incomeData.setText("");
        this.outcomeData = new TextArea();
        this.incomeData.setEditable(false);
        this.outcomeData.setText("");
        this.debugData = new TextArea();
        this.debugData.setEditable(false);
        this.debugData.setText("");

        createGui();
    }

    private void createGui() {
        this.outcomeLabel.setLayoutX(23.0);
        this.outcomeLabel.setLayoutY(135.0);

        this.outcomeData.setPrefSize(335.0, 82.0);
        this.outcomeData.setFont(new Font(12));
        this.outcomeData.setLayoutX(23.0);
        this.outcomeData.setLayoutY(44.0);

        this.incomeLabel.setLayoutX(23.0);
        this.incomeLabel.setLayoutY(25.0);

        this.incomeData.setPrefSize(335.0, 82.0);
        this.incomeData.setFont(new Font(12));
        this.incomeData.setLayoutX(23.0);
        this.incomeData.setLayoutY(152.0);

        this.debugLabel.setLayoutX(23.0);
        this.debugLabel.setLayoutY(240.0);

        this.debugData.setPrefSize(335.0, 82.0);
        this.debugData.setFont(new Font(12));
        this.debugData.setLayoutX(23.0);
        this.debugData.setLayoutY(258.0);

        this.layer = new AnchorPane();
    }

    private void controller() {
        
    }

    public Parent getLayerCustom() {
        this.layer.getChildren().addAll(
                debugLabel,
                outcomeLabel,
                outcomeData,
                incomeLabel,
                incomeData,
                debugData
        );
        return this.layer;
    }


    public TextArea getIncomeData() {
        return incomeData;
    }

    public TextArea getOutcomeData() {
        return outcomeData;
    }

    public TextArea getDebugData() {
        return debugData;
    }

    public void setIncomeData(String data){
        this.incomeData.setText(data);
    }

}
