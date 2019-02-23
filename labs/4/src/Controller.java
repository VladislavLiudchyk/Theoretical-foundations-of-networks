import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Random;

public class Controller {
    private static boolean flag;
    private static Thread calc;
    private static String buf;

    public static Thread getCalc() {return calc;}

    private static void textControl(GUI gui) {
        calc = new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    if(flag) {
                        while (true) {
                            if (isChannelFree()) {
                                sendData(gui);
                                for (int i = 0; i < 10; i++) {
                                    if (isCollision()) {
                                        try {
                                            Thread.sleep(800);
                                            Thread.sleep((int) Math.pow(0.2, i + 1));
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        gui.getDebugData().appendText("X ");
                                        continue;
                                    } else {
                                        try {
                                            Thread.sleep(800);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        gui.getIncomeData().appendText(buf);
                                        break;
                                    }
                                }
                                gui.getOutcomeData().setEditable(true);
                                gui.getDebugData().appendText("\n");
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }

        });
        calc.start();

        gui.getOutcomeData().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("^$|^.$"))
                    gui.getOutcomeData().setText(oldValue);
                if(!gui.getOutcomeData().getText().isEmpty()) {
                    flag = true;
                }
            }
        });
    }



    private static boolean isChannelFree() {
        final Random random = new Random();
        return random.nextBoolean();
    }

    private static boolean isCollision() {
        final Random random = new Random();
        Integer rand = (int)(Math.random() * 100);
        System.out.println("Random value is " + rand);
        if(rand > 25)
            return true;
        else
            return false;
    }

    private static void sendData(GUI gui) {
        buf = gui.getOutcomeData().getText();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gui.getOutcomeData().setText("");
        gui.getOutcomeData().setEditable(false);
        flag = false;
    }

    public static void init(GUI gui) {
        textControl(gui);
    }
}
