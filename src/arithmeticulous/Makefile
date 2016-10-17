all: Exercise.class ExerciseHistory.class ExerciseHistoryTest.class

Exercise.class: Exercise.java
	javac -cp ../ Exercise.java

ExerciseHistory.class: ExerciseHistory.java Exercise.class
	javac -cp ../ ExerciseHistory.java

ExerciseHistoryTest.class: ExerciseHistoryTest.java ExerciseHistory.class
	javac -cp ../ ExerciseHistoryTest.java
