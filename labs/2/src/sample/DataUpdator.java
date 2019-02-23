package sample;

public class DataUpdator {
    private ComInterface comInterface;

    public void update(String newData) {
        this.comInterface.getIncomeData().setText(newData);
    }

    public void setError(String newError) {
        this.comInterface.setIncomeData(newError);
        System.out.println("\nERROR!\n");
    }

    public DataUpdator() {
        this.comInterface = new ComInterface();
    }
}
