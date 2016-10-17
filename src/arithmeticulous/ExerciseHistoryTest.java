package arithmeticulous;

import arithmeticulous.Exercise;
import arithmeticulous.ExerciseHistory;

public class ExerciseHistoryTest {
    public static void main(String[] args) {
        ExerciseHistory test = ExerciseHistory.allocate(3);
        Exercise exercise1 = new Exercise(1, 1000);
        Exercise exercise2 = new Exercise(2, 2000);
        Exercise exercise3 = new Exercise(3, 3000);
        Exercise exercise4 = new Exercise(4, 4000);
        Exercise exercise5 = new Exercise(5, 5000);
        Exercise exercise6 = new Exercise(6, 6500);
        System.out.printf("After storing nothing, ops/sec is: %f, and the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();

        test.push(exercise1);
        System.out.printf("After storing exercise 1, ops/sec is: %f, the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();

        test.push(exercise2);
        System.out.printf("After storing exercise 2, ops/sec is: %f, the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();

        test.push(exercise3);
        System.out.printf("After storing exercise 3, ops/sec is: %f, the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();

        test.push(exercise4);
        System.out.printf("After storing exercise 4, ops/sec is: %f, the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();

        test.push(exercise5);
        System.out.printf("After storing exercise 5, ops/sec is: %f, the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();

        test.push(exercise6);
        System.out.printf("After storing exercise 6, ops/sec is: %f, the exercise history looks like:%n", test.getOperationsPerSecond());
        test.print();
    }
}
