public class Operator extends Node {
    private String type;
    
    public Operator(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDataAsHTML() {
        return type;
    }
}
