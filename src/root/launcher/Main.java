package root.launcher;


import root.controller.Controller;
import root.model.ModelFacade;
import root.view.View;
import root.view.ViewFactory;
import root.view.impl.ConsoleView;

public class Main {

    public static void main(String[] args) {
        View view = ViewFactory.getInstance().getView();
        ModelFacade modelFacade = ModelFacade.getInstance();
        Controller controller = new Controller(view, modelFacade);
        while (true) {
           controller.doAction();
        }
    }
}
