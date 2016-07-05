package root.model;


import root.model.matrix.Matrix;
import root.model.matrix.Multiplier;
import root.model.matrix.exception.MultipleException;

public class ModelFacade {

    private static ModelFacade instance;

    private ModelFacade(){}

    public static ModelFacade getInstance(){

        if (instance == null)
            synchronized (ModelFacade.class){
                if (instance == null)
                    instance = new ModelFacade();
            }
        return instance;
    }

    public int[][] multiplyMatrixes(int[][] first, int[][] second) throws MultipleException {
        Matrix matrixFirst = Matrix.MatrixBuilder.createMatrixWithBody(first);
        Matrix matrixSecond = Matrix.MatrixBuilder.createMatrixWithBody(second);
        Matrix result = Multiplier.getInstance().multiply(matrixFirst, matrixSecond);
        return result.getBody();
    }
}
