package root.view;


public interface View {
    void printGreeting();

    int defineDegree();

    int[][] getMatrix(int degree);

    void printResult(int[][] result);
}
