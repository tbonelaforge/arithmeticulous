package arithmeticulous;

public class Exercise {
    private int operationCount;
    private long duration;

    public Exercise(int operationCount, long duration) {
        this.operationCount = operationCount;
        this.duration = duration;
    }

    public int getOperationCount() {
        return operationCount;
    }

    public double getDurationInSeconds() {
        return duration / 1000.0;
    }

    public void print() {
        System.out.printf("(%d, %f)", getOperationCount(), getDurationInSeconds());
    }
}
