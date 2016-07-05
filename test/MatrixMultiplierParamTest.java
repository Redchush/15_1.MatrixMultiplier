import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import root.model.matrix.Matrix;
import root.model.matrix.Multiplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MatrixMultiplierParamTest {

    private Matrix matrixFist;
    private Matrix matrixSecond;
    private Matrix matrixExpected;
    private Multiplier multiplier = Multiplier.getInstance();

   public MatrixMultiplierParamTest(Matrix matrixFist, Matrix matrixSecond, Matrix matrixExpected) {
        this.matrixFist = matrixFist;
        this.matrixSecond = matrixSecond;
        this.matrixExpected = matrixExpected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> stepUpCoefficientTableValues() {
        List<Object[]> objects = new ArrayList<Object[]>();

        objects.add(new Object[]{
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {1, 1, 1, 1},
                        {2, 2, 2, 2},
                        {3, 3, 3, 3},
                        {4, 4, 4, 4}
                }),
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {9, 9, 9, 9},
                        {8, 8, 8, 8},
                        {7, 7, 7, 7},
                        {6, 6, 6, 6}
                }),

                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {30, 30, 30, 30},
                        {60, 60, 60, 60},
                        {90, 90, 90, 90},
                        {120, 120, 120, 120}
                })
        });

        objects.add(new Object[]{
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }),
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {1, 2, 3},
                        {1, 2, 3},
                        {1, 2, 3}
                }),

                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {6, 12, 18},
                        {15, 30, 45},
                        {24, 48, 72}
                })
        });

        objects.add(new Object[]{
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {2, 2},
                        {3, 3}
                }),
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {1, 1},
                        {4, 4}
                }),
                Matrix.MatrixBuilder.createMatrixWithBody(new int[][]{
                        {10, 10},
                        {15, 15}
                })
        });
        return objects;
    }

    @Test
    public void multiply() throws Exception {
        long maxValue = 10000;
        long start = System.currentTimeMillis();
        Matrix actual = multiplier.multiply(this.matrixFist, this.matrixSecond);
        long end = System.currentTimeMillis();
        assertEquals(this.matrixExpected, actual);
        assertTrue(end - start < maxValue);
    }
}

