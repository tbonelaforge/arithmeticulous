package arithmeticulous.model;

public class Node implements PrintableTree {
    private Node leftChild;
    private Node rightChild;

    protected static String dataTemplate = "%s";

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public String getDataAsHTML() {
        throw new RuntimeException("printDataAsHTML not implemented for: " + this);
    }

    public String getData() {
        throw new RuntimeException("printData not implemented for: " + this);
    }

    public String getText() {
        throw new RuntimeException("getText not implemented for: " + this);
    }

    public int getPrecedence() {
        throw new RuntimeException("getPrecedence not implemented for: " + this);
    }

    public Node evaluate() {
        throw new RuntimeException("evaluate not implemented for: " + this);
    }

    public int countOperators() {
        int leftOperators = 0;
        int rightOperators = 0;

        if (getLeftChild() != null) {
            leftOperators = getLeftChild().countOperators();
        }
        if (getRightChild() != null) {
            rightOperators = getRightChild().countOperators();
        }
        int count = leftOperators + rightOperators;
        if (isOperator()) {
            System.out.println("Inside Node.countOperators, realized this is an operator!\n");
            count += 1;
        } else {
            System.out.println("Inside Node.countOperators, I don't think this is an operator!!!\n");
        }
        if (this instanceof Operator) {
            System.out.printf("Inside Node.countOperators, for an OPERATOR about to return count of: %d%n", count);
        } else {
            System.out.printf("Inside Node.countOperators, for a NON-OPERATOR, about to return count of: %d%n", count);
        }
        return count;
    }

    protected boolean isOperator() {
        System.out.println("Inside Node.isOperator, about to return false!\n");
        return false;
    }

    public Node replace(Node target, Node replacement) {
        if (this == target) {
            return replacement;
        }
        if (getLeftChild() != null) {
            setLeftChild(getLeftChild().replace(target, replacement));
        }
        if (getRightChild() != null) {
            setRightChild(getRightChild().replace(target, replacement));
        }
        return this;
    }
}
