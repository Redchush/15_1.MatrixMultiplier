package root.view.impl;

import root.view.View;

import java.util.Arrays;

public class ConsoleView implements View {

    private static final String GREETING = "Hello, User!";
    private static final String DEFINE_DEGREE = "Please, define matrixes degree";
    private static final String ENTER_MATRIX = "Please, enter the quadratic matrix body. Print numbers in row with space " +
            "delimiter. Each row must be ended with enter";
    private static final String DELEMITER = " ";
    private static final String RESULT = "The result of calculation is: ";
    private static final String EXIT = "EXIT";
    private static final String EXIT_MESSAGE = "If you press any time " + EXIT + " the program will be completed.";

    public void printGreeting(){
        ConsoleHelper.writeMessage(GREETING);
        ConsoleHelper.writeMessage(EXIT_MESSAGE);
    }

    public int defineDegree(){
        ConsoleHelper.writeMessage(DEFINE_DEGREE);
        return ConsoleHelper.readInt();
    }

    public int[][] getMatrix(int degree){
        ConsoleHelper.writeMessage(ENTER_MATRIX);
        int[][] result = new int[degree][degree];
        for (int i = 0; i < degree; i++) {
            int[] temp = ConsoleHelper.readIntsLineWithDelimiter(DELEMITER, degree);
            result[i] = temp;
        }
        return result;
    }
    public void printResult(int[][] result) {
        ConsoleHelper.writeMessage(RESULT);
        int degree = result.length;
        for (int i = 0; i < degree; i++) {
            ConsoleHelper.writeMessage(Arrays.toString(result[i]));
        }
    }


}
