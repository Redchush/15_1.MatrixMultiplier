package root.view;


import root.view.impl.ConsoleView;

public class ViewFactory {

    public static final String CONSOLE = "CONSOLE";

    private static ViewFactory instance;
    private ViewFactory(){};

    public static ViewFactory getInstance() {
        if (instance == null)
        {
            instance = new ViewFactory();
        }
        return instance;
    }

    public View getView() {
       return new ConsoleView();
    }
}

