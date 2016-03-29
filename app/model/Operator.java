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
        String editModeName = getEditMode().name();
        String dataAsHTML = String.format(
                                          dataTemplate,
                                          type,
                                          editModeName
                                          );
        return dataAsHTML;
    }

    public String getData() {
        return type;
    }
}
