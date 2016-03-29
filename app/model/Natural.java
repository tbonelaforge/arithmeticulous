package model;

public class Natural extends Node {
    private int value;

    public Natural(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public String getDataAsHTML() {
        String valueString = getData();
        String editModeName = getEditMode().name();
        String dataAsHTML = String.format(
                                          dataTemplate,
                                          valueString,
                                          editModeName
                                          );
        return dataAsHTML;
    }

    public String getData() {
        return String.valueOf(value);
    }
}
