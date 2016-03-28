public class Natural extends Node {
    private int value;

    public Natural(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public String getDataAsHTML() {
        return String.valueOf(value);
    }
}
