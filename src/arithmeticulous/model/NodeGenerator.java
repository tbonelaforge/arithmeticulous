package arithmeticulous.model;

public class NodeGenerator {
    private String desiredOperatorType;
    private int desiredDepth;

    public NodeGenerator(String desiredOperatorType, int desiredDepth) {
        this.desiredOperatorType = desiredOperatorType;
        this.desiredDepth = desiredDepth;
    }

    public static Node generateRandomExpression(int desiredDepth) {
        String operatorType = Math.random() < 0.5 ? "*" : "+";
        NodeGenerator g = new NodeGenerator(operatorType, desiredDepth);
        return g.generate();
    }

    public static Node generateEasyExpression() {
        String expressionKey;
        if (Math.random() < 0.5) {
            expressionKey = "*<+";
        } else {
            expressionKey = "+>*";
        }
        return generateEasyExpression(expressionKey);
    }

    public static Node generateMediumExpression() {
        String expressionKey;
        if (Math.random() < 0.5) {
            expressionKey = "+<*";
        } else {
            expressionKey = "*>+";
        }
        return generateMediumExpression(expressionKey);
    }

    public static Node generateEasyOrMediumExpression() {
        String expressionKey;
        if (Math.random() < 0.5) {
            return generateEasyExpression();
        } else {
            return generateMediumExpression();
        }
    }

    public static Node generateEasyOrMediumOrHardExpression() {
        return generateRandomExpression(2);
    }

    public static Node generateEasyExpression(String expressionKey) {
        Node expression;
        if (expressionKey.equals("*<+")) {
            Node node1 = new Natural(pickRandomInt(2, 5));
            Node node3 = new Natural(pickRandomInt(2, 5));
            Node node2 = new Operator("*");
            node2.setLeftChild(node1);
            node2.setRightChild(node3);
            Node node4 = new Natural(pickRandomInt(1, 9));
            Node node5 = new Operator("+");
            node5.setLeftChild(node4);
            node5.setRightChild(node2);
            expression = node5;
        } else if (expressionKey.equals("+>*")) {
            Node node1 = new Natural(pickRandomInt(2, 5));
            Node node3 = new Natural(pickRandomInt(2, 5));
            Node node2 = new Operator("*");
            node2.setLeftChild(node1);
            node2.setRightChild(node3);
            Node node4 = new Natural(pickRandomInt(1, 9));
            Node node5 = new Operator("+");
            node5.setLeftChild(node2);
            node5.setRightChild(node4);
            expression = node5;
        } else {
            throw new RuntimeException("Inside generateEasyExpression, expression key not recognized: " + expressionKey);
        }
        return expression;
    }

    public static Node generateMediumExpression(String expressionKey) {
        Node expression;
        if (expressionKey.equals("+<*")) {
            Node node1 = new Natural(pickRandomInt(1, 9));
            Node node3 = new Natural(pickRandomInt(1, 9));
            Node node2 = new Operator("+");
            node2.setLeftChild(node1);
            node2.setRightChild(node3);
            Node node4 = new Natural(pickRandomInt(2, 5));
            Node node5 = new Operator("*");
            node5.setLeftChild(node2);
            node5.setRightChild(node4);
            expression = node5;
        } else if (expressionKey.equals("*>+")) {
            Node node1 = new Natural(pickRandomInt(1, 9));
            Node node3 = new Natural(pickRandomInt(1, 9));
            Node node2 = new Operator("+");
            node2.setLeftChild(node1);
            node2.setRightChild(node3);
            Node node4 = new Natural(pickRandomInt(2, 5));
            Node node5 = new Operator("*");
            node5.setLeftChild(node4);
            node5.setRightChild(node2);
            expression = node5;
        } else {
            throw new RuntimeException("Inside generateMediumExpression, expression key not recognized: " + expressionKey);
        }
        return expression;
    }

    public Node generate() {
        int level = 0;
        Node a = (Node) generateRandomNatural();
        if (desiredDepth == 0) {
            return a;
        }
        while (level < desiredDepth) {
            Operator op = generateOperator(level + 1);
            int randomDepth = pickRandomInt(0, level);
            NodeGenerator g = makeChildGenerator(op, randomDepth);
            Node b = g.generate();
            if (Math.random() < 0.5) {
                op.setLeftChild(a);
                op.setRightChild(b);
            } else {
                op.setLeftChild(b);
                op.setRightChild(a);
            }
            a = op;
            level = level + 1;
        }
        return a;
    }

    private Natural generateRandomNatural() {
        int value = pickRandomInt(2, 9);
        return new Natural(value);
    }

    private Operator generateOperator(int level) {
        String operatorType;
        if (isOdd(level)) {
            operatorType = isOdd(desiredDepth) ? getDesiredOperatorType() : getOppositeOperatorType();
        } else { // level is even
            operatorType = isEven(desiredDepth) ? getDesiredOperatorType() : getOppositeOperatorType();
        }
        return new Operator(operatorType);
    }

    private static int pickRandomInt(int lo, int hi) {
        double r = Math.random() * (hi - lo + 1);
        int randomInt = lo + (int) Math.floor(r);
        return randomInt;
    }

    private NodeGenerator makeChildGenerator(Operator parent, int desiredDepth) {
        String childOperatorType = getOppositeOperatorType(parent.getType());
        NodeGenerator childGenerator = new NodeGenerator(childOperatorType, desiredDepth);
        return childGenerator;
    }

    private static boolean isOdd(int n) {
        return n % 2 == 0 ? false : true;
    }

    private static boolean isEven(int n) {
        return !isOdd(n);
    }

    private String getDesiredOperatorType() {
        return desiredOperatorType;
    }

    private String getOppositeOperatorType() {
        return getOppositeOperatorType(desiredOperatorType);
    }

    private static String getOppositeOperatorType(String operatorType) {
        if (operatorType == "*") {
            return "+";
        } else if (operatorType == "+") {
            return "*";
        } else {
            throw new RuntimeException("Unrecognized Operator Type: " + operatorType);
        }
    }
}
