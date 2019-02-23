import javafx.geometry.Orientation;
import javafx.geometry.VerticalDirection;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class GUI {

    private AnchorPane layer;

    private Label firstStOutputLabel;
    private Label firstStInputLabel;
    private Label firstStDebugLabel;
    
    private Label secondStOutputLabel;
    private Label secondStInputLabel;
    private Label secondStDebugLabel;
    
    private Label monitorStOutputLabel;
    private Label monitorStInputLabel;
    private Label monitorStDebugLabel;

    private TextArea firstStOutputData;
    private TextArea firstStInputData;
    private TextArea firstStDebugData;
    
    private TextArea secondStOutputData;
    private TextArea secondStInputData;
    private TextArea secondStDebugData;

    private TextArea monitorStOutputData;
    private TextArea monitorStInputData;
    private TextArea monitorStDebugData;
    
    
    private Label firstStSrcLabel;
    private Label firstStDestLabel;
    private Label secondStSrcLabel;
    private Label secondStDestLabel;
    private Label monitorStSrcLabel;
    private Label monitorStDestLabel;
    
    private TextField firstStSrcAddress;
    private TextField firstStDestAddress;
    
    private TextField secondStSrcAddress;
    private TextField secondStDestAddress;
    
    private TextField monitorStSrcAddress;
    private TextField monitorStDestAddress;
    
    
    private Button firstStApplyAddress;
    private Button secondStApplyAddress;
    private Button monitorStApplyAddress;
    
    
    private CheckBox etrBox;
    private Button startButton;
    
    private Separator vertical;
    private Separator horizontal;
    
    
   

    GUI() {
        this.firstStOutputLabel = new Label("Output");
        this.firstStInputLabel = new Label("Input");
        this.firstStDebugLabel = new Label("Debug");
        
        this.secondStOutputLabel = new Label("Output");
        this.secondStInputLabel = new Label("Input");
        this.secondStDebugLabel = new Label("Debug");
        
        this.monitorStOutputLabel = new Label("Output");
        this.monitorStInputLabel = new Label("Input");
        this.monitorStDebugLabel = new Label("Debug");
        
        
        this.firstStOutputData = new TextArea();
        this.firstStInputData = new TextArea();
        this.firstStOutputData.setEditable(false);
        this.firstStDebugData = new TextArea();
        this.firstStDebugData.setEditable(false);
        
        this.secondStOutputData = new TextArea();
        this.secondStInputData = new TextArea();
        this.secondStOutputData.setEditable(false);
        this.secondStDebugData = new TextArea();
        this.secondStDebugData.setEditable(false);
        
        this.monitorStOutputData = new TextArea();
        this.monitorStInputData = new TextArea();
        this.monitorStOutputData.setEditable(false);
        this.monitorStDebugData = new TextArea();
        this.monitorStDebugData.setEditable(false);
        
        
        this.firstStSrcLabel = new Label("Src Address");
        this.firstStDestLabel = new Label("Dest Address");
        this.secondStSrcLabel = new Label("Src Address");
        this.secondStDestLabel = new Label("Dest Address");
        this.monitorStSrcLabel = new Label("Src Address");
        this.monitorStDestLabel = new Label("Dest Address");
        
        
        this.firstStSrcAddress = new TextField();
        this.firstStDestAddress = new TextField();
        
        this.secondStSrcAddress = new TextField();
        this.secondStDestAddress = new TextField();
        
        this.monitorStSrcAddress = new TextField();
        this.monitorStDestAddress = new TextField();
        
        
        this.firstStApplyAddress = new Button("Apply");
        this.secondStApplyAddress = new Button("Apply");
        this.monitorStApplyAddress = new Button("Apply");
        
        
        this.etrBox = new CheckBox("ETR");
        this.startButton = new Button("Start");
        
        this.vertical = new Separator();
        this.horizontal = new Separator();
        

        createGui();
    }

    private void createGui() {
        this.firstStInputLabel.setLayoutX(130.0);
        this.firstStInputLabel.setLayoutY(22.0);
        this.firstStOutputLabel.setLayoutX(130.0);
        this.firstStOutputLabel.setLayoutY(101.0);
        this.firstStDebugLabel.setLayoutX(130.0);
        this.firstStDebugLabel.setLayoutY(168.0);
        
        this.secondStInputLabel.setLayoutX(430.0);
        this.secondStInputLabel.setLayoutY(22.0);
        this.secondStOutputLabel.setLayoutX(430.0);
        this.secondStOutputLabel.setLayoutY(101.0);
        this.secondStDebugLabel.setLayoutX(430.0);
        this.secondStDebugLabel.setLayoutY(168.0);
        
        this.monitorStInputLabel.setLayoutX(130.0);
        this.monitorStInputLabel.setLayoutY(306.0);
        this.monitorStOutputLabel.setLayoutX(130.0);
        this.monitorStOutputLabel.setLayoutY(382.0);
        this.monitorStDebugLabel.setLayoutX(130.0);
        this.monitorStDebugLabel.setLayoutY(452.0);
        
        
        this.firstStInputData.setLayoutX(48.0);
        this.firstStInputData.setLayoutY(39.0);
        this.firstStInputData.setPrefSize(200, 50);
        this.firstStOutputData.setLayoutX(48.0);
        this.firstStOutputData.setLayoutY(115.0);
        this.firstStOutputData.setPrefSize(200, 50);
        this.firstStDebugData.setLayoutX(48.0);
        this.firstStDebugData.setLayoutY(185.0);
        this.firstStDebugData.setPrefSize(200, 50);
        
        this.secondStInputData.setLayoutX(348.0);
        this.secondStInputData.setLayoutY(39.0);
        this.secondStInputData.setPrefSize(200, 50);
        this.secondStOutputData.setLayoutX(348.0);
        this.secondStOutputData.setLayoutY(115.0);
        this.secondStOutputData.setPrefSize(200, 50);
        this.secondStDebugData.setLayoutX(348.0);
        this.secondStDebugData.setLayoutY(185.0);
        this.secondStDebugData.setPrefSize(200, 50);
        
        this.monitorStInputData.setLayoutX(48.0);
        this.monitorStInputData.setLayoutY(323.0);
        this.monitorStInputData.setPrefSize(200, 50);
        this.monitorStOutputData.setLayoutX(48.0);
        this.monitorStOutputData.setLayoutY(399.0);
        this.monitorStOutputData.setPrefSize(200, 50);
        this.monitorStDebugData.setLayoutX(48.0);
        this.monitorStDebugData.setLayoutY(469.0);
        this.monitorStDebugData.setPrefSize(200, 50);
        
        
        this.firstStSrcLabel.setLayoutX(48.0);
        this.firstStSrcLabel.setLayoutY(242.0);
        this.firstStDestLabel.setLayoutX(148.0);
        this.firstStDestLabel.setLayoutY(242.0);
        
        this.secondStSrcLabel.setLayoutX(339.0);
        this.secondStSrcLabel.setLayoutY(242.0);
        this.secondStDestLabel.setLayoutX(431.0);
        this.secondStDestLabel.setLayoutY(242.0);
        
        this.monitorStSrcLabel.setLayoutX(48.0);
        this.monitorStSrcLabel.setLayoutY(526.0);
        this.monitorStDestLabel.setLayoutX(148.0);
        this.monitorStDestLabel.setLayoutY(526.0);
        
        
        this.firstStSrcAddress.setLayoutX(33.0);
        this.firstStSrcAddress.setLayoutY(259.0);
        this.firstStSrcAddress.setPrefSize(93, 25);
        this.firstStDestAddress.setLayoutX(139.0);
        this.firstStDestAddress.setLayoutY(259.0);
        this.firstStDestAddress.setPrefSize(93, 25);
        
        this.secondStSrcAddress.setLayoutX(333.0);
        this.secondStSrcAddress.setLayoutY(259.0);
        this.secondStSrcAddress.setPrefSize(93, 25);
        this.secondStDestAddress.setLayoutX(439.0);
        this.secondStDestAddress.setLayoutY(259.0);
        this.secondStDestAddress.setPrefSize(93, 25);
        
        this.monitorStSrcAddress.setLayoutX(33.0);
        this.monitorStSrcAddress.setLayoutY(543.0);
        this.monitorStSrcAddress.setPrefSize(93, 25);
        this.monitorStDestAddress.setLayoutX(139.0);
        this.monitorStDestAddress.setLayoutY(543.0);
        this.monitorStDestAddress.setPrefSize(93, 25);
        
        
        this.firstStApplyAddress.setLayoutX(244.0);
        this.firstStApplyAddress.setLayoutY(260.0);
        this.secondStApplyAddress.setLayoutX(544.0);
        this.secondStApplyAddress.setLayoutY(260.0);
        this.monitorStApplyAddress.setLayoutX(244.0);
        this.monitorStApplyAddress.setLayoutY(544.0);
        
        this.etrBox.setLayoutX(306.0);
        this.etrBox.setLayoutY(548.0);
        
        this.startButton.setLayoutX(428.0);
        this.startButton.setLayoutY(544.0);
        
        this.vertical.setLayoutX(300.0);
        this.vertical.setOrientation(Orientation.VERTICAL);
        this.vertical.setPrefSize(0.0, 300.0);
        
        this.horizontal.setLayoutY(300);
        this.horizontal.setOrientation(Orientation.HORIZONTAL);
        this.horizontal.setPrefSize(600.0, 0.0);
        
        this.layer = new AnchorPane();
    }

    public Parent getLayerCustom() {
        this.layer.getChildren().addAll(
        		firstStInputLabel,
        		firstStDebugLabel,
        		firstStOutputLabel,
        		
        		secondStInputLabel,
        		secondStDebugLabel,
        		secondStOutputLabel,
        		
        		monitorStInputLabel,
        		monitorStDebugLabel,
        		monitorStOutputLabel,
        		
        		firstStInputData,
        		firstStOutputData,
        		firstStDebugData,
        		
        		secondStInputData,
        		secondStOutputData,
        		secondStDebugData,
        		
        		monitorStInputData,
        		monitorStOutputData,
        		monitorStDebugData,
        		
        		firstStSrcLabel,
        		firstStDestLabel,
        		secondStSrcLabel,
        		secondStDestLabel,
        		monitorStSrcLabel,
        		monitorStDestLabel,
        		
        		firstStSrcAddress,
        		firstStDestAddress,
        		secondStSrcAddress,
        		secondStDestAddress,
        		monitorStSrcAddress,
        		monitorStDestAddress,
        		
        		firstStApplyAddress,
        		secondStApplyAddress,
        		monitorStApplyAddress,
        		
        		etrBox,
        		startButton,
        		
        		vertical,
        		horizontal
        );
        return this.layer;
    }

/*
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
*/
}
