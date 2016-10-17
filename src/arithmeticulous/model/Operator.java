package arithmeticulous.model;

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
        Node leftChild = getLeftChild();
        Node rightChild = getRightChild();
        String leftChildText = leftChild.getText();
        String rightChildText = rightChild.getText();
        if (leftChild.getPrecedence() < getPrecedence()) {
            leftChildText = "(" + leftChildText + ")";
        }
        if (rightChild.getPrecedence() < getPrecedence()) {
            rightChildText = "(" + rightChildText + ")";
        }
        String text = leftChildText + type + rightChildText;
        return text;
    }

    public Node evaluate() {
        Natural arg1 = (Natural) getLeftChild().evaluate();
        Natural arg2 = (Natural) getRightChild().evaluate();
        int value;
        if (type == "+") {
            value = arg1.getValue() + arg2.getValue();
        } else if (type == "*") {
            value = arg1.getValue() * arg2.getValue();
        } else {
            throw new RuntimeException("Cannot evaluate type: " + type);
        }
        Natural result = new Natural(value);
        return result;
    }

    public int getPrecedence() {
        if (type == "*") {
            return 2;
        } else if (type == "+") {
            return 1;
        } else {
            throw new RuntimeException("Precedence not defined for operator: " + type);
        }
    }
}
