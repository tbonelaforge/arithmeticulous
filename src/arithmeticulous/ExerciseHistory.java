package arithmeticulous;

import arithmeticulous.Exercise;

public class ExerciseHistory {
    private Exercise[] exercises;
    private int count;

    private ExerciseHistory(int length) {
        this.exercises = new Exercise[length];
    }

    public static ExerciseHistory allocate(int length) {
        return new ExerciseHistory(length);
    }

    public void push(Exercise exercise) {
        int bound = exercises.length - 1;
        if (count < exercises.length) {
            bound = count;
        }
        for (int i = bound; i > 0; i--) {
            exercises[i] = exercises[i - 1];
        }
        exercises[0] = exercise;
        if (count < exercises.length) {
            count += 1;
        }
    }

    public double getOperationsPerSecond() {
        int totalOperationCount = 0;
        double totalSeconds = 0.0;
        for (int i = 0; i < count; i++) {
            totalOperationCount += exercises[i].getOperationCount();
            totalSeconds += exercises[i].getDurationInSeconds();
        }
        if (totalSeconds > 0.0) {
            return totalOperationCount / totalSeconds;
        } else {
            return 0.0;
        }
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            exercises[i].print();
            if (i < count - 1) {
                System.out.printf(", ");                
            }
        }
        System.out.printf("%n");
    }
}
