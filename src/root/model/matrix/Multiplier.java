package root.model.matrix;

import root.model.matrix.exception.MultipleException;

import java.util.ArrayList;
import java.util.List;


public class Multiplier {

    private static Multiplier instance;

    private Multiplier() {}

    public static Multiplier getInstance() {

        if (instance == null)
            synchronized (Multiplier.class) {
                if (instance == null)
                    instance = new Multiplier();
            }
        return instance;
    }

    public Matrix multiply(final Matrix first, final Matrix second) throws MultipleException {
        return realMultiply(first, second);
    }

    public Matrix multiply(Matrix[] matrixes) throws MultipleException {
        return realMultiply(matrixes[0], matrixes[1]);
    }

    public boolean isMultiplyPossible(Matrix first, Matrix second) {
        int firstDegree = first.getDegree();
        int secondDegree = second.getDegree();
        return firstDegree == secondDegree;
    }

    private Matrix realMultiply(final Matrix first, final Matrix second) throws MultipleException {
        if (first == null || second == null || !isMultiplyPossible(first, second)) {
            throw new MultipleException("Multiplying matrix " + first + " and " + second + "isn't possible");
        }
        if (first.getDegree() == 1) {
            return Matrix.MatrixBuilder.createEmptyMatrix(1)
                                       .setElement(0, 0, first.getElement(0, 0) * second.getElement(0, 0));
        }

        int recommended = Runtime.getRuntime().availableProcessors() + 1; // link to Java Concurrency in Practice
        ThreadManager storage = new ThreadManager(first, second, recommended);
        storage.execute();

        try {
            return storage.complete();
        } catch (InterruptedException e) {
            throw new MultipleException("Multiplying matrix " + first + " and " + second + "was interrupted", e);
        }
    }

    private class ThreadManager {

        private final Matrix first;
        private final Matrix second;
        private final int degree;
        private final Matrix result;
        private List<MultiplyRunner> threads;
        private int maxThreads;

        private ThreadManager(Matrix first, Matrix second, int maxThreads) {
            this.first = first;
            this.second = second;
            this.maxThreads = maxThreads;

            this.degree = first.getDegree();
            this.result = Matrix.MatrixBuilder.createEmptyMatrix(degree);
            threads = new ArrayList<MultiplyRunner>();
        }

        private void execute() {
            int share = degree > maxThreads ? degree / maxThreads : 1;
            for (int i = 0; i < degree; i = i + share) {
                int limit = i + share;
                if (limit > degree) {
                    limit = degree;
                }
                threads.add(new MultiplyRunner(i, limit));
            }
        }

        private Matrix complete() throws InterruptedException {
            for (int i = 0; i < threads.size(); i++) {
                threads.get(i).join();
            }
            return result;
        }

        private class MultiplyRunner extends Thread {
            private int limit;
            private int row;

            MultiplyRunner(int row, int limit) {
                super();
                this.limit = limit;
                this.row = row;
                this.start();
            }

            @Override
            public void run() {
                for (int i = row; i < limit; i++) {
                    for (int j = 0; j < degree; j++) {
                        int value = 0;
                        final int height = j;

                        for (int k = 0; k < degree; k++) {
                            value += first.getElement(row, k) * second.getElement(k, height);
                        }
                        result.setElement(row, height, value);
                    }
                }
            }
        }
    }
}


