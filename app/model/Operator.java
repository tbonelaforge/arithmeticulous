package model;

public class Operator extends Node {
    private String type;
    
    public Operator(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDataAsHTML() {
        String dataAsHTML = String.format(dataTemplate, type);
        return dataAsHTML;
    }

    public String getData() {
        return type;
    }

    public String getText() {
        String text = getLeftChild().getText() + type + getRightChild().getText();
        return text;
    }
}
