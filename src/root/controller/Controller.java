package root.controller;

import root.view.View;
import root.model.ModelFacade;
import root.model.matrix.exception.MultipleException;

public class Controller {
    private final View view;
    private final ModelFacade modelFacade;

    public Controller(View view, ModelFacade modelFacade) {
        this.view = view;
        this.modelFacade = modelFacade;
    }

    public void doAction(){

        view.printGreeting();
        int degree = view.defineDegree();
        int[][] first = view.getMatrix(degree);
        int[][] second = view.getMatrix(degree);
        int[][] result = null;
        try {
            result = modelFacade.multiplyMatrixes(first, second);
        } catch (MultipleException e) {
           //nop, cause equavalence of degrees controlled by view
        }
        view.printResult(result);
    }
}
