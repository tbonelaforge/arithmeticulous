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
